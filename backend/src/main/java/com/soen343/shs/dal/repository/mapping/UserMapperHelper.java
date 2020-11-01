package com.soen343.shs.dal.repository.mapping;

import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.service.exceptions.IllegalStateException;
import com.soen343.shs.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UserMapperHelper {
    private final ConversionService mvcConversionService;

    public void mapRoleLocationAndHouseIds(final UserDTO dto, final User user) {
        mapLocation(dto, user);
        mapRole(dto, user);
        mapHouseIds(dto, user);
    }

    private static void mapHouseIds(final UserDTO dto, final User user) {
        if (!dto.getHouseIds().isEmpty()) {
            user.setHouseIds(dto.getHouseIds());
        }
    }

    private void mapLocation(final UserDTO dto, final User user) {
        if (!dto.isOutside() && Objects.isNull(dto.getLocation())) {
            throw new IllegalStateException("A user must either have a location, or be outside!");
        }
        if (dto.isOutside()) {
            user.setIsOutside(true);
            user.setLocation(null);
        } else {
            user.setIsOutside(false);
            user.setLocation(mvcConversionService.convert(dto.getLocation(), Room.class));
        }
    }

    private static void mapRole(final UserDTO dto, final User user) {
        if (dto.getRole() != user.getRole()) {
            user.setRole(dto.getRole());
        }
    }

    public static String mapStringField(final String fieldName, final String dtoString, final String userString) {
        if (!dtoString.equals(userString)) {
            validateStringField(fieldName, dtoString);
            return dtoString;
        }
        return userString;
    }

    private static void validateStringField(final String fieldName, final String dtoString) {
        if (dtoString.isEmpty()) {
            throw new IllegalStateException(String.format("Field: %s cannot be empty", fieldName));
        }
    }
}
