package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreateStoreDTO;
import com.tan.java.hairhub.entities.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.tan.java.hairhub.dto.request.CreateUserDTO;
import com.tan.java.hairhub.dto.request.UpdateUserDTO;
import com.tan.java.hairhub.dto.response.UserDTO;
import com.tan.java.hairhub.entities.Profile;
import com.tan.java.hairhub.entities.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
// nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE: không mapping các trường null
public interface UserMapper {

    User toUser(CreateUserDTO userDTO);

    Profile toProfile(CreateUserDTO userDTO);

    @Mapping(source = "profile.birthDate", target = "birthDate")
    @Mapping(source = "profile.bio", target = "bio")
    @Mapping(source = "profile.yearOfExperience", target = "yearOfExperience")
    @Mapping(source = "profile.fullName", target = "fullName")
    @Mapping(source = "profile.address", target = "address")
    @Mapping(source = "profile.gender", target = "gender")
    UserDTO toUserDTO(User user, Profile profile);

    // Dùng MappingTarget dể cập nhật thẳng tử dto vào entity user luôn
    void updateUserFromDto(UpdateUserDTO dto, @MappingTarget User user);

    void updateProfileFromDRO(UpdateUserDTO userDTO, @MappingTarget Profile profile);


}
