package com.soen343.shs.dal.repository.mapping;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dto.HouseMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HouseMemberMapper {
    private final UserMapperHelper mapperHelper;

    public HouseMember mapUserDTOToUser(final HouseMemberDTO dto, final HouseMember houseMember) {
        mapperHelper.mapRoleLocationAndHouseIds(dto, houseMember);
        houseMember.setUsername(UserMapperHelper.mapStringField("username", dto.getUsername(), houseMember.getUsername()));
        return houseMember;
    }
}
