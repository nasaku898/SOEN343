package com.soen343.shs.converters.users;

import com.soen343.shs.converters.rooms.RoomToRoomDTOConverter;
import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dto.HouseMemberDTO;
import org.springframework.core.convert.converter.Converter;

public class HouseMemberToHouseMemberDTOConverter implements Converter<HouseMember, HouseMemberDTO> {
    private final RoomToRoomDTOConverter converter = new RoomToRoomDTOConverter();

    @Override
    public HouseMemberDTO convert(final HouseMember houseMember) {
        return HouseMemberDTO.builder()
                .location(converter.convert(houseMember.getLocation()))
                .username(houseMember.getUsername())
                .isOutside(houseMember.getIsOutside())
                .houseIds(houseMember.getHouseIds())
                .id(houseMember.getId())
                .role(houseMember.getRole())
                .build();
    }
}
