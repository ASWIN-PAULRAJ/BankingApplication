package com.BankingApplication.Banking.Application.Service;

import com.BankingApplication.Banking.Application.DTO.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long userId);

    void deleteUser(Long userId);

    void deleteAllUsers();

    UserDTO updateUser(Long userId, UserDTO userDTO);

    UserDTO getUserByName(String firstName, String lastName);
}
