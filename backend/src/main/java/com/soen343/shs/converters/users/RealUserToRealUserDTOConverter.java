package com.soen343.shs.converters.users;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dto.RealUserDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Objects;

public class RealUserToRealUserDTOConverter implements Converter<RealUser, RealUserDTO> {
    @Override
    public RealUserDTO convert(final RealUser user) {
        return RealUserDTO.builder()
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .roomId(Objects.isNull(user.getLocation()) ? Collections.emptyMap() : Collections.singletonMap(user.getLocation().getId(), user.getLocation().getName()))
                .build();
    }
}
