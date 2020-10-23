package com.soen343.shs.converters.users;

import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RegistrationDTO;
import org.springframework.core.convert.converter.Converter;

public class RegistrationDTOToUserDTOConverter implements Converter<RegistrationDTO, RealUserDTO> {
    @Override
    public RealUserDTO convert(final RegistrationDTO registrationDTO) {
        return RealUserDTO.builder()
                .username(registrationDTO.getUsername())
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .email(registrationDTO.getEmail())
                .role(UserRole.valueOf(registrationDTO.getRole()))
                .build();
    }
}
