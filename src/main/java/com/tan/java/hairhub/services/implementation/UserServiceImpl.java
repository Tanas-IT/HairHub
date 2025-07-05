package com.tan.java.hairhub.services.implementation;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.tan.java.hairhub.dto.request.CreateUserDTO;
import com.tan.java.hairhub.dto.request.UpdateUserDTO;
import com.tan.java.hairhub.dto.response.CreateUserResponse;
import com.tan.java.hairhub.dto.response.UserDTO;
import com.tan.java.hairhub.entities.Profile;
import com.tan.java.hairhub.entities.RefreshToken;
import com.tan.java.hairhub.entities.Role;
import com.tan.java.hairhub.entities.User;
import com.tan.java.hairhub.mapper.UserMapper;
import com.tan.java.hairhub.repositories.RefreshTokenRepository;
import com.tan.java.hairhub.repositories.RoleRepository;
import com.tan.java.hairhub.repositories.UserRepository;
import com.tan.java.hairhub.services.interfaces.UserService;
import com.tan.java.hairhub.util.ConfigSystem;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ConfigSystem bCryptEncode;
    private RoleRepository roleRepository;
    private ConfigSystem configSystem;
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserMapper userMapper;

    @NonFinal
    @Value("${spring.valid-duration}")
    private long VALID_DURATION;

    @NonFinal
    @Value("${spring.secret-key}")
    private String SECRET_KEY;

    @NonFinal
    @Value(("${spring.refreshable-duration}"))
    private long REFRESHABLE_DURATION;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ConfigSystem bCryptEncode,
            RoleRepository roleRepository,
            RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.bCryptEncode = bCryptEncode;
        this.roleRepository = roleRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserDTO> getAllUser(int pageIndex, int pageSize) {
        log.info("In method getAllUser");
        List<UserDTO> listUser = new ArrayList<>();
        this.userRepository.getAllUser((pageIndex - 1) * pageSize, pageSize).forEach(user -> {
            UserDTO userDAO = new UserDTO();
            userDAO.setUserId(user.getUserId());
            userDAO.setActive(user.isActive());
            userDAO.setBio(user.getProfile().getBio());
            userDAO.setAddress(user.getProfile().getAddress());
            userDAO.setEmail(user.getEmail());
            userDAO.setPassword(user.getPassword());
            userDAO.setPhoneNumber(user.getPhoneNumber());
            userDAO.setGender(user.getProfile().getGender());
            userDAO.setBirthDate(user.getProfile().getBirthDate());
            userDAO.setAvatarURL(user.getAvatarURL());
            userDAO.setFullName(user.getProfile().getFullName());
            userDAO.setUserId(user.getUserId());
            userDAO.setRoleName(user.getRole().getRoleName());
            userDAO.setYearOfExperience(user.getProfile().getYearOfExperience());
            listUser.add(userDAO);
        });
        return listUser;
    }

    @Override
    //    @PostAuthorize("returnObject.email == authentication.name")
    // returnObject là kết quả của hàm trả về, authentication là object lưu thông tin của token đã decode. Khi token đã
    // decode và validate hợp lệ
    // thì sẽ lưu trong SecurityContextHolder và authentication là đối tượng được khởi tạo từ SecurityContextHolder,
    // trong đó name là sub của token khi decode
    public UserDTO getUserById(int id) {

        Optional<User> userOpt = this.userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = userMapper.toUserDTO(user, user.getProfile());
            userDTO.setRoleName(user.getRole().getRoleName());
            log.info("In method getUserById");
            log.info(userDTO.getEmail());
            return userDTO;
        }
        return new UserDTO();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = userMapper.toUserDTO(user, user.getProfile());
            userDTO.setRoleName(user.getRole().getRoleName());
            return userDTO;
        }
        return new UserDTO();
    }

    @Override
    //    @PostAuthorize("hasRole('ADMIN')")
    public CreateUserResponse createUser(CreateUserDTO userDTO) throws Exception {
        log.info("Service: In method createUser");
        User user = userMapper.toUser(userDTO);
        Optional<Role> role = this.roleRepository.findById(userDTO.getRoleId());
        user.setRole(role.isPresent() ? role.get() : null);
        user.setPassword(this.bCryptEncode.bCryptPasswordEncoder().encode(user.getPassword()));
        Profile profile = userMapper.toProfile(userDTO);
        user.addProfile(profile);
        CreateUserResponse createUserResponse = this.userMapper.createUserResponse(userDTO);
        try {
            this.userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new Exception("User existed");
        }
        try {
            String accessToken = generateAccessToken(user);
            String refreshTokenGenerate = generateRefreshToken(user);

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setRefreshTokenValue(refreshTokenGenerate);
            refreshToken.setUser(user);
            refreshToken.setCreateDate(LocalDate.now());
            refreshToken.setExpiredDate(LocalDateTime.now().plusMonths(1));
            refreshToken.setUsed(false);
            refreshToken.setRevoked(true);
            this.refreshTokenRepository.save(refreshToken);
            createUserResponse.setAccessToken(accessToken);
            createUserResponse.setRefreshToken(refreshTokenGenerate);
            createUserResponse.setActive(true);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return createUserResponse;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        Optional<User> checkUser = this.userRepository.findById(updateUserDTO.getUserId());
        if (checkUser.isPresent()) {
            User user = checkUser.get();
            userMapper.updateUserFromDto(updateUserDTO, user);
            userMapper.updateProfileFromDRO(updateUserDTO, user.getProfile());
            this.userRepository.save(user);
            return updateUserDTO;
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        Optional<User> checkUser = this.userRepository.findById(userId);
        if (checkUser.isPresent()) {
            User user = checkUser.get();
            this.userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDTO getUserInfo() throws Exception {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userResponse = this.userMapper.toUserDTO(user, user.getProfile());
            userResponse.setRoleName(user.getRole().getRoleName());
            return userResponse;
        }
        throw new Exception("User does not exist");
    }

    private String generateAccessToken(User user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("com.hairhub")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("userId", user.getUserId())
                .claim("role", user.getRole().getRoleName())
                .claim("email", user.getEmail())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
        return jwsObject.serialize();
    }

    private String generateRefreshToken(User user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("com.hairhub")
                .expirationTime(new Date(Instant.now()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("userId", user.getUserId())
                .claim("role", user.getRole().getRoleName())
                .claim("email", user.getEmail())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        JWSSigner jwsSigner = new MACSigner(SECRET_KEY.getBytes());

        jwsObject.sign(jwsSigner);

        return jwsObject.serialize();
    }
}
