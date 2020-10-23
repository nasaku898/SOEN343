package com.soen343.shs.converters.users;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dto.RegistrationDTO;
import org.springframework.core.convert.converter.Converter;

public class RegistrationDTOToUserConverter implements Converter<RegistrationDTO, RealUser> {
    @Override
    public RealUser convert(final RegistrationDTO registrationDTO) {
        return RealUser.builder()
                .username(registrationDTO.getUsername())
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .email(registrationDTO.getEmail())
                .isOutside(true)
                .role(UserRole.valueOf(registrationDTO.getRole()))
                .build();
    }
}
