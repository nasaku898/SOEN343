package com.soen343.shs.converters;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dto.HouseMemberDTO;
import org.springframework.core.convert.converter.Converter;

public class HouseMemberToHouseMemberDTOConverter implements Converter<HouseMember, HouseMemberDTO> {

    @Override
    public HouseMemberDTO convert(HouseMember houseMember) {
        return HouseMemberDTO.builder()
                .roomId(houseMember.getLocation().getId())
                .name(houseMember.getName())
                .id(houseMember.getId())
                .build();
    }
}
