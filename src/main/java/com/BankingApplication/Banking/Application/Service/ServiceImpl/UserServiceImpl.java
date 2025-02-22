package com.BankingApplication.Banking.Application.Service.ServiceImpl;

import com.BankingApplication.Banking.Application.DTO.UserDTO;
import com.BankingApplication.Banking.Application.Entity.User;
import com.BankingApplication.Banking.Application.Exception.UserNotFoundException;
import com.BankingApplication.Banking.Application.Mapper.UserMapper;
import com.BankingApplication.Banking.Application.Repositary.UserRepository;
import com.BankingApplication.Banking.Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.mapToEntity(userDTO);
        user = userRepository.save(user);
        return UserMapper.mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToDTO) // Using the mapper function
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.mapToDTO(user); // Using the mapper function
    }


    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user = userRepository.save(user);
        return UserMapper.mapToDTO(user); // Using the mapper function
    }

    @Override
    public UserDTO getUserByName(String firstName, String lastName) {
        User user = userRepository.findByName(firstName, lastName)
                .orElseThrow(() -> new UserNotFoundException(firstName, lastName));
        return UserMapper.mapToDTO(user); // Using the mapper function
    }
}
