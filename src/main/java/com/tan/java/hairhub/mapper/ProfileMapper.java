package com.tan.java.hairhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.tan.java.hairhub.dto.request.CreateProfileDTO;
import com.tan.java.hairhub.dto.request.UpdateProfileDTO;
import com.tan.java.hairhub.dto.response.ProfileResponse;
import com.tan.java.hairhub.entities.Profile;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProfileMapper {
    ProfileResponse toProfileResponse(Profile profile);

    Profile createProfile(CreateProfileDTO createProfileDTO);

    Profile updateProfile(UpdateProfileDTO updateProfileDTO, @MappingTarget Profile profile);
}
