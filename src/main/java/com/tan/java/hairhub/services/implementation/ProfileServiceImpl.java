package com.tan.java.hairhub.services.implementation;

import com.tan.java.hairhub.dto.request.CreateProfileDTO;
import com.tan.java.hairhub.dto.request.UpdateOrderDTO;
import com.tan.java.hairhub.dto.request.UpdateProfileDTO;
import com.tan.java.hairhub.dto.response.ProcessResponse;
import com.tan.java.hairhub.dto.response.ProfileResponse;
import com.tan.java.hairhub.entities.Profile;
import com.tan.java.hairhub.entities.User;
import com.tan.java.hairhub.mapper.ProfileMapper;
import com.tan.java.hairhub.repositories.ProfileRepository;
import com.tan.java.hairhub.repositories.UserRepository;
import com.tan.java.hairhub.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;
    private ProfileMapper profileMapper;
    private UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<ProfileResponse> getAllProfile(int pageIndex, int pageSize) {
        List<Profile> listProfile = this.profileRepository.getAllProfile((pageIndex - 1) * pageSize, pageSize);
        List<ProfileResponse> listProfileResponse = new ArrayList<>();
        if(!listProfile.isEmpty()) {
            listProfile.forEach(profile -> {
                ProfileResponse profileResponse = this.profileMapper.toProfileResponse(profile);
                listProfileResponse.add(profileResponse);
            });
        }
        return listProfileResponse;
    }

    @Override
    public ProfileResponse getProfileById(int profileId) {
        Optional<Profile> checkProfileExist = this.profileRepository.findById(profileId);
        if(checkProfileExist.isPresent()) {
            ProfileResponse profileResponse = this.profileMapper.toProfileResponse(checkProfileExist.get());
            return  profileResponse;
        }
        return null;
    }

    @Override
    public ProfileResponse createProfile(CreateProfileDTO createProfileDTO) {
        Profile createProfile = this.profileMapper.createProfile(createProfileDTO);
        Optional<User> checkUser = this.userRepository.findById(createProfileDTO.getUserId());
        if(createProfile != null && checkUser.isPresent()) {
            createProfile.setUser(checkUser.get());
            Profile result = this.profileRepository.save(createProfile);
            ProfileResponse profileResponse = this.profileMapper.toProfileResponse(result);
            return profileResponse;
        }
        return null;
    }

    @Override
    public Profile updateProfile(UpdateProfileDTO updateProfileDTO) throws Exception {
        Optional<Profile> checkProfileExist = this.profileRepository.findById(updateProfileDTO.getProfileId());
        if(!checkProfileExist.isPresent()) {
           throw new Exception("Profile does not exist");
        }
        Profile profile = this.profileMapper.updateProfile(updateProfileDTO, checkProfileExist.get());
        this.profileRepository.save(profile);
        return profile;
    }

    @Override
    public void deleteProfile(int orderId) throws Exception {
        Optional<Profile> checkProfileExist = this.profileRepository.findById(orderId);
        if(!checkProfileExist.isPresent()) {
            throw new Exception("Profile does not exist");
        }
        this.profileRepository.delete(checkProfileExist.get());
    }
}
