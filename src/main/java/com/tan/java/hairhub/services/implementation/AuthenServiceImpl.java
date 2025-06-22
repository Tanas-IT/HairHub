package com.tan.java.hairhub.services.implementation;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tan.java.hairhub.dto.request.AuthenticationRequest;
import com.tan.java.hairhub.dto.request.LogoutRequest;
import com.tan.java.hairhub.dto.request.RefreshTokenRequest;
import com.tan.java.hairhub.dto.response.TokenResponse;
import com.tan.java.hairhub.dto.response.ValidateTokenResponse;
import com.tan.java.hairhub.entities.InvalidatedToken;
import com.tan.java.hairhub.entities.RefreshToken;
import com.tan.java.hairhub.entities.User;
import com.tan.java.hairhub.repositories.InvalidatedTokenRepository;
import com.tan.java.hairhub.repositories.RefreshTokenRepository;
import com.tan.java.hairhub.repositories.UserRepository;
import com.tan.java.hairhub.services.interfaces.AuthenService;
import com.tan.java.hairhub.util.ConfigSystem;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenServiceImpl implements AuthenService {

    private UserRepository userRepository;

    private ConfigSystem configSystem;
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${spring.secret-key}")
    private String SECRET_KEY;

    @NonFinal
    @Value("${spring.valid-duration}")
    private long VALID_DURATION;

    @NonFinal
    @Value(("${spring.refreshable-duration}"))
    private long REFRESHABLE_DURATION;

    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthenServiceImpl(
            UserRepository userRepository,
            InvalidatedTokenRepository invalidatedTokenRepository,
            RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public AuthenServiceImpl(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    @Override
    public TokenResponse login(AuthenticationRequest authenticationRequest) throws RuntimeException {
        Optional<User> optionalUser = this.userRepository.findByEmail(authenticationRequest.getEmail());
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User does not exist");
        }
        User user = optionalUser.get();

        configSystem = new ConfigSystem();
        boolean checkValidPassword = this.configSystem
                .bCryptPasswordEncoder()
                .matches(authenticationRequest.getPassword(), user.getPassword());
        TokenResponse tokenResponse = new TokenResponse();
        if (checkValidPassword) {
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

                tokenResponse.setAccessToken(accessToken);
                tokenResponse.setAuthenticated(true);
                tokenResponse.setRefreshToken(refreshTokenGenerate);

            } catch (JOSEException e) {
                throw new RuntimeException(e);
            }
        } else {
            tokenResponse.setAccessToken(null);
            tokenResponse.setAuthenticated(false);
        }
        return tokenResponse;
    }

    @Override
    public ValidateTokenResponse validateToken(String accessToken) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        try {
            JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(accessToken);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            boolean isValid = true;
            try {
                verifyToken(accessToken);

            } catch (Exception e) {
                isValid = false;
            }
            ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse();
            validateTokenResponse.setValid(isValid && expiryTime.after(new Date()));
            return validateTokenResponse;

        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void logout(LogoutRequest logoutRequest) throws JOSEException, ParseException {
        try {
            SignedJWT signedAccessToken = verifyToken(logoutRequest.getAccessToken());
            String jitAccessToken = signedAccessToken.getJWTClaimsSet().getJWTID();
            Date expiryTimeAccessToken = signedAccessToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedAccessToken = new InvalidatedToken();
            invalidatedAccessToken.setId(jitAccessToken);
            invalidatedAccessToken.setExipiredDate(expiryTimeAccessToken);

            SignedJWT signedRefreshToken = verifyToken(logoutRequest.getRefreshToken());
            String jitRefreshToken = signedRefreshToken.getJWTClaimsSet().getJWTID();
            Date expiryTimeRefreshToken = signedRefreshToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedRefreshToken = new InvalidatedToken();
            invalidatedRefreshToken.setId(jitRefreshToken);
            invalidatedRefreshToken.setExipiredDate(expiryTimeRefreshToken);

            this.invalidatedTokenRepository.save(invalidatedAccessToken);
            this.invalidatedTokenRepository.save(invalidatedRefreshToken);
        } catch (Exception ex) {
            log.info("Token already expired");
        }
    }

    @Override
    @Transactional
    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        try {
            refreshTokenRepository.deleteByRefreshTokenValue(refreshTokenRequest.getRefreshToken());
            SignedJWT signedToken = verifyToken(refreshTokenRequest.getRefreshToken());

            String jit = signedToken.getJWTClaimsSet().getJWTID();
            Date exipryTime = signedToken.getJWTClaimsSet().getExpirationTime();
            InvalidatedToken invalidatedToken = new InvalidatedToken();
            invalidatedToken.setId(jit);
            invalidatedToken.setExipiredDate(exipryTime);
            this.invalidatedTokenRepository.save(invalidatedToken);

            String email = signedToken.getJWTClaimsSet().getStringClaim("email");
            Optional<User> user = this.userRepository.findByEmail(email);

            String accessToken = generateAccessToken(user.get());
            String refreshTokenGenerate = generateRefreshToken(user.get());

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setRefreshTokenValue(refreshTokenGenerate);
            refreshToken.setUser(user.get());
            refreshToken.setCreateDate(LocalDate.now());
            refreshToken.setExpiredDate(LocalDateTime.now().plusMonths(1));
            refreshToken.setUsed(false);
            refreshToken.setRevoked(true);
            this.refreshTokenRepository.save(refreshToken);

            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setAccessToken(accessToken);
            tokenResponse.setRefreshToken(refreshTokenGenerate);
            tokenResponse.setAuthenticated(true);

            return tokenResponse;
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date exipryDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(jwsVerifier);

        if (!verified && exipryDate.after(new Date())) {
            try {
                throw new Exception("UnAuthorization");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (this.invalidatedTokenRepository.existsById(
                signedJWT.getJWTClaimsSet().getJWTID())) {
            try {
                throw new Exception("Unauthorization");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return signedJWT;
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

    private String buildRoles(Set<String> roles) {
        StringJoiner stringJoiner = new StringJoiner("");
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> stringJoiner.add(role));
        }
        return stringJoiner.toString();
    }
}
