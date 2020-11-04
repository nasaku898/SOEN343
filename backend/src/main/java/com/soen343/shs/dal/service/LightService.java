package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.repository.LightRepository;
import com.soen343.shs.dto.LightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LightService {

    private final LightRepository lightRepository;
    private final RoomService roomService;

    /**
     * @param id           id of light object to modify
     * @param desiredState boolean referring to the desired state of the object
     * @return LightDTO object reflecting the changes made to the object
     */
    public LightDTO modifyLightState(final long id, final boolean desiredState) {
        return roomService.changeStateOfRoomObject(id, Light.class, LightDTO.class, lightRepository,
                light -> {
                    ErrorHelper.checkForSameStateException(desiredState, light.getIsLightOn(), ErrorHelper.getSameStateExceptionErrorMessage(light.getClass(), id));
                    light.setIsLightOn(desiredState);
                });
    }
}
