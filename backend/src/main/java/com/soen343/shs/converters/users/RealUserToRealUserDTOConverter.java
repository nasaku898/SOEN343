package com.soen343.shs.converters.users;

import com.soen343.shs.converters.rooms.RoomToRoomDTOConverter;
import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealUserToRealUserDTOConverter implements Converter<RealUser, RealUserDTO> {
    private final RoomToRoomDTOConverter converter = new RoomToRoomDTOConverter();

    @Override
    public RealUserDTO convert(final RealUser user) {
        return RealUserDTO.builder()
                .email(user.getEmail())
                .id(user.getId())
                .houseIds(user.getHouseIds())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isOutside(user.getIsOutside())
                .role(user.getRole())
                .location(convertLocation(user))
                .build();
    }

    private RoomDTO convertLocation(final RealUser user) {
        if (user.getLocation() != null) {
            return converter.convert(user.getLocation());
        }
        return null;
    }
}
