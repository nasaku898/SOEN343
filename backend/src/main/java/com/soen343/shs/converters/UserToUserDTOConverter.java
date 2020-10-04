package com.soen343.shs.converters;

import com.soen343.shs.DTO.UserDTO;
import com.soen343.shs.dal.model.User;
import org.springframework.core.convert.converter.Converter;


public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roomId(user.getLocation().getId())
                .build();
    }
}
