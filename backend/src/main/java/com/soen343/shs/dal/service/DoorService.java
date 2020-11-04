package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.InteriorDoor;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import com.soen343.shs.dal.repository.InteriorDoorRepository;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.ExteriorDoorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoorService {
    private final InteriorDoorRepository interiorDoorRepository;
    private final ExteriorDoorRepository exteriorDoorRepository;
    private final RoomService roomService;

    /**
     * @param id           id of door to modify
     * @param open         boolean referring to if we should try to open the door or not
     * @param desiredState boolean referring to the desired state of the object
     * @return DoorDTO reflecting to the changes made to the object
     */
    public ExteriorDoorDTO modifyExteriorDoorState(final long id, final boolean open, final boolean desiredState) {
        return roomService.changeStateOfRoomObject(id, ExteriorDoor.class, ExteriorDoorDTO.class, exteriorDoorRepository,
                door -> {
                    final String sameStateExceptionErrorMessage = ErrorHelper.getSameStateExceptionErrorMessage(door.getClass(), id);
                    if (open) {
                        ErrorHelper.checkForIllegalStateException(ExteriorDoor.class, id, door.getLocked());
                        ErrorHelper.checkForSameStateException(desiredState, door.getOpen(), sameStateExceptionErrorMessage);
                        door.setOpen(desiredState);
                    } else {
                        ErrorHelper.checkForIllegalStateException(ExteriorDoor.class, id, door.getOpen());
                        ErrorHelper.checkForSameStateException(desiredState, door.getLocked(), sameStateExceptionErrorMessage);
                        door.setLocked(desiredState);
                    }
                });
    }

    public DoorDTO modifyInteriorDoorState(final long id, final boolean desiredState) {
        return roomService.changeStateOfRoomObject(id, InteriorDoor.class, DoorDTO.class, interiorDoorRepository,
                door -> {
                    final String sameStateExceptionErrorMessage = ErrorHelper.getSameStateExceptionErrorMessage(door.getClass(), id);
                    ErrorHelper.checkForSameStateException(desiredState, door.getOpen(), sameStateExceptionErrorMessage);
                    door.setOpen(desiredState);
                });
    }

}
