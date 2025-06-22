package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateUserDTO;
import com.tan.java.hairhub.dto.request.UpdateUserDTO;
import com.tan.java.hairhub.dto.response.UserDTO;

public interface UserService {

    List<UserDTO> getAllUser(int pageIndex, int pageSize);

    UserDTO getUserById(int id);

    CreateUserDTO createUser(CreateUserDTO userDTO) throws Exception;

    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);

    boolean deleteUser(int userId);

    UserDTO getUserInfo() throws Exception;
}
