package com.soen343.shs.converters;

import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dal.model.User;
import org.springframework.core.convert.converter.Converter;

public class RegistrationDTOToUserConverter implements Converter<RegistrationDTO, User> {
    @Override
    public User convert(RegistrationDTO registrationDTO) {
        return User.builder()
                .username(registrationDTO.getUsername())
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .email(registrationDTO.getEmail())
                .build();
    }
}
