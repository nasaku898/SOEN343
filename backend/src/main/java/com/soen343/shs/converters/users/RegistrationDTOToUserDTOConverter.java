package com.soen343.shs.converters.users;

import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;

public class RegistrationDTOToUserDTOConverter implements Converter<RegistrationDTO, UserDTO> {
    @Override
    public UserDTO convert(final RegistrationDTO registrationDTO) {
        return UserDTO.builder()
                .username(registrationDTO.getUsername())
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .email(registrationDTO.getEmail())
                .role(UserRole.valueOf(registrationDTO.getRole()))
                .build();
    }
}
