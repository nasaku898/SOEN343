package com.soen343.shs.dal.repository.mapping;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dto.RealUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RealUserMapper {
    private final UserMapperHelper mapper;

    public RealUser mapRealUserDTOToRealUser(final RealUserDTO dto, final RealUser user) {
        mapper.mapRoleLocationAndHouseIds(dto, user);

        if (!dto.getEmail().equals(user.getEmail())) {
            user.setEmail(UserMapperHelper.mapStringField("email", dto.getEmail(), user.getEmail()));
        }

        if (!dto.getFirstName().equals(user.getFirstName())) {
            user.setFirstName(UserMapperHelper.mapStringField("first name", dto.getFirstName(), user.getFirstName()));
        }

        if (!dto.getLastName().equals(user.getLastName())) {
            user.setLastName(UserMapperHelper.mapStringField("last name", dto.getLastName(), user.getLastName()));
        }

        return user;
    }

}
