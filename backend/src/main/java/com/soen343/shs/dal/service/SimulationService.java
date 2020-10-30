package com.soen343.shs.dal.service;

import com.soen343.shs.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final HouseService houseService;
    private final ConversionService mvcConversionService;
    private final RoomService roomService;
    private final UserService userService;
    private final HouseMemberService houseMemberService;
    
    /**
     * @param userId  id used to fetch user from db
     * @param houseId id used to fetch house from db
     * @param dto     Generic instance used to fetch user from db
     * @param <DTO>   Generic object used to fetch user from db
     * @return boolean if the update is successful we will return true, else we throw an error
     */
    public <DTO extends UserDTO> boolean addHouseIdToUser(final long userId, final long houseId, final Class<DTO> dto) {
        final DTO user = userService.getById(userId, dto);
        user.getHouseIds().add(houseId);

        if (user instanceof RealUserDTO) {
            userService.updateUser((RealUserDTO) user);
        } else {
            houseMemberService.updateHouseMember((HouseMemberDTO) user);
        }

        return addUserIdToHouse(user, houseId);
    }

    /**
     * @param username username of user
     * @param roomId   a room id to transfer to
     * @return UserDTO object reflecting the changes made to the object
     */

    public <DTO extends UserDTO> UserDTO moveUserToRoom(final String username,
                                                        final long roomId,
                                                        final Class<DTO> dto) {
        final DTO user = userService.getUserByUsername(username, dto);
        if (user.isOutside()) {
            user.setOutside(false);
        }
        user.setLocation(roomService.getRoom(roomId));
        if (user instanceof RealUserDTO) {
            userService.updateUser((RealUserDTO) user);
        } else {
            houseMemberService.updateHouseMember((HouseMemberDTO) user);
        }
        return user;
    }

    /**
     * @param houseId a house id
     * @return list of RoomDTO
     */
    public Set<RoomDTO> findAllRooms(final Long houseId) {
        return Objects.requireNonNull(mvcConversionService.convert(houseService.fetchHouse(houseId), HouseDTO.class)).getRooms();
    }

    private boolean addUserIdToHouse(final UserDTO dto, final long houseId) {
        final HouseDTO house = houseService.getHouse(houseId);

        switch (dto.getRole()) {
            case PARENT:
                house.getParents().add(dto.getId());
                break;
            case CHILD:
                house.getChildren().add(dto.getId());
                break;
            case GUEST:
                house.getGuests().add(dto.getId());
                break;
        }
        houseService.updateHouse(house);
        return true;
    }
}
