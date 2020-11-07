package com.soen343.shs.dal.service.validators;

import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dal.service.RoomService;
import com.soen343.shs.dal.service.UserService;
import com.soen343.shs.dal.service.exceptions.IllegalRequestException;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PermissionValidator {
    private final UserService userService;
    private final RoomService roomService;

    public void validatePermissions(final String username, final long roomId) {
        final HouseMemberDTO user = userService.getUserByUsername(username, HouseMemberDTO.class);
        final RoomDTO room = roomService.getRoom(roomId);

        if (user.getRole() != UserRole.valueOf("PARENT") && !user.getLocation().equals(room)) {
            throw new IllegalRequestException(String.format("User : %s cannot change the state of room %s's entities, because they are not in that room!", username, room.getName()));
        }

    }
}
