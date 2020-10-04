package com.soen343.shs.converters;

import com.soen343.shs.DTO.RegistrationDTO;
import com.soen343.shs.DTO.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class RegistrationDTOToUserDTOConverter implements Converter<RegistrationDTO, UserDTO> {
    @Override
    public UserDTO convert(RegistrationDTO registrationDTO) {
        return UserDTO.builder()
                .username(registrationDTO.getUsername())
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .email(registrationDTO.getEmail())
                .build();
    }
}
