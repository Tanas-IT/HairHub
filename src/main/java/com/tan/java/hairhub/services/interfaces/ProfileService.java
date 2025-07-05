package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateProfileDTO;
import com.tan.java.hairhub.dto.request.UpdateProfileDTO;
import com.tan.java.hairhub.dto.response.ProfileResponse;
import com.tan.java.hairhub.entities.Profile;

public interface ProfileService {
    List<ProfileResponse> getAllProfile(int pageIndex, int pageSize);

    ProfileResponse getProfileById(int profileId);

    ProfileResponse createProfile(CreateProfileDTO createProfileDTO);

    Profile updateProfile(UpdateProfileDTO updateProfileDTO) throws Exception;

    void deleteProfile(int orderId) throws Exception;
}
