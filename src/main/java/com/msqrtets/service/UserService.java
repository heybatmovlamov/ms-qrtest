package com.msqrtets.service;


import com.msqrtets.model.dto.UserRequest;
import com.msqrtets.model.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest requestDto);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserRequest requestDto);

    void deleteUser(Long id);
}

