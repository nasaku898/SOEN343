package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.repository.LightRepository;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dal.service.validators.PermissionValidator;
import com.soen343.shs.dal.service.validators.StateValidator;
import com.soen343.shs.dal.service.validators.helper.ErrorMessageGenerator;
import com.soen343.shs.dto.LightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class LightService {

    private final LightRepository lightRepository;
    private final RoomService roomService;
    private final PermissionValidator validator;
    private final ConversionService mvcConversionService;

    /**
     * @param id           id of light object to modify
     * @param desiredState boolean referring to the desired state of the object
     * @return LightDTO object reflecting the changes made to the object
     */
    public LightDTO modifyLightState(final String username, final long roomId, final long id, final boolean desiredState) {
        validator.validatePermissions(username, roomId);
        return roomService.changeStateOfRoomObject(id, Light.class, LightDTO.class, lightRepository,
                light -> {
                    StateValidator.validateState(desiredState, light.getIsLightOn(), id, Light.class);
                    light.setIsLightOn(desiredState);
                });
    }

    public LightDTO updateStartTime(final long lightId, final LocalTime startTime) {
        final Light light = fetchLight(lightId);
        light.setStart(startTime);
        return saveLight(light);
    }

    public LightDTO updateEndTime(final long lightId, final LocalTime endTime) {
        final Light light = fetchLight(lightId);
        light.setEnd(endTime);
        return saveLight(light);
    }

    public LightDTO toggleAwayMode(final long lightId, final boolean desiredState) {
        final Light light = fetchLight(lightId);
        StateValidator.validateState(desiredState, light.getAwayMode(), lightId, Light.class);
        light.setAwayMode(desiredState);
        return saveLight(light);
    }

    private Light fetchLight(final long lightId) {
        return lightRepository.findById(lightId).orElseThrow(() -> new SHSNotFoundException(ErrorMessageGenerator.getSHSNotFoundErrorMessage(lightId, Light.class)));
    }

    private LightDTO saveLight(final Light light) {
        return mvcConversionService.convert(lightRepository.save(light), LightDTO.class);
    }


}
