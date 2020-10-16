package com.soen343.shs.converters.users;

import com.soen343.shs.dal.model.User;
import com.soen343.shs.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(final User user) {
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roomId(user.getLocation() == null ? 0 : user.getLocation().getId()) // definitely dont wanna keep it like this but this is good for right now..
                .build();
    }
}
