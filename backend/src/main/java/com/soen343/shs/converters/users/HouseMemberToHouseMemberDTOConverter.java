package com.soen343.shs.converters.users;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dto.HouseMemberDTO;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;

public class HouseMemberToHouseMemberDTOConverter implements Converter<HouseMember, HouseMemberDTO> {

    @Override
    public HouseMemberDTO convert(final HouseMember houseMember) {
        return HouseMemberDTO.builder()
                .roomId(Collections.singletonMap(houseMember.getLocation().getId(),
                        houseMember.getLocation().getName()))
                .username(houseMember.getUsername())
                .id(houseMember.getId())
                .role(houseMember.getRole())
                .build();
    }
}
