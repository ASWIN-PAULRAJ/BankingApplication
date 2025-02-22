package com.BankingApplication.Banking.Application.Mapper;

import com.BankingApplication.Banking.Application.DTO.UserDTO;
import com.BankingApplication.Banking.Application.Entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private static ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        UserMapper.modelMapper = modelMapper;
    }

    public static UserDTO mapToDTO(User user) {

        return modelMapper.map(user, UserDTO.class);
    }

    public User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
