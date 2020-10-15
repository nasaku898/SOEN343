package com.soen343.shs.converters.users;

import com.soen343.shs.dal.model.User;
import com.soen343.shs.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class UserDTOtoUserConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(final UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .build();
    }
}