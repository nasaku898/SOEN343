package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.InteriorDoorRepository;
import com.soen343.shs.dal.repository.LightRepository;
import com.soen343.shs.dal.service.exceptions.houseWindow.HouseWindowBlockedException;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dal.service.exceptions.state.SHSSameStateException;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final LightRepository lightRepository;
    private final HouseWindowRepository houseWindowRepository;
    private final InteriorDoorRepository interiorDoorRepository;
    private final ExteriorDoorRepository exteriorDoorRepository;
    private final ConversionService mvcConversionService;

    public LightDTO modifyLightState(final long id, final boolean isOn) {
        return changeStateOfHouseObject(id, Light.class, LightDTO.class, lightRepository,
                light -> {
                    if ((isOn && light.isLightOn()) || (!isOn && !light.isLightOn())) {
                        throw new SHSSameStateException(getSameStateExceptionErrorMessage(light.getClass(), id));
                    }
                    light.setLightOn(isOn);
                });
    }

    public DoorDTO modifyExteriorDoorState(final long id, final boolean isLocked) {
        return changeStateOfHouseObject(id, ExteriorDoor.class, DoorDTO.class, exteriorDoorRepository,
                door -> {
                    if ((isLocked && door.isLocked()) || (!isLocked && !door.isLocked())) {
                        throw new SHSSameStateException(getSameStateExceptionErrorMessage(door.getClass(), id));
                    }
                    door.setLocked(isLocked);
                });
    }

    public WindowDTO modifyWindowState(final long id, final boolean isOpen) {
        return changeStateOfHouseObject(id, HouseWindow.class, WindowDTO.class, houseWindowRepository,
                window -> {
                    if ((isOpen && window.isOpen()) || (!isOpen && !window.isOpen())) {
                        throw new SHSSameStateException(getSameStateExceptionErrorMessage(window.getClass(), id));
                    }
                    checkIfWindowIsBlocked(window);
                    window.setOpen(isOpen);
                });
    }

    private static <Entity> String getErrorMessageForHouseNotFoundObject(final long id, final Class<Entity> classType) {
        return String.format("Error: %s with ID %d does not exist. Please enter a valid %s ID.", classType.getName(), id, classType.getName());
    }

    private static <E> String getSameStateExceptionErrorMessage(final Class<E> classType, final long id) {
        return String.format("Error: object %s with id %d is already in the expected state", classType.getName(), id);
    }

    private static void checkIfWindowIsBlocked(final HouseWindow window) {
        final String WINDOW_BLOCKED_ERROR = "Error: Window with ID %d cannot be opened since it is blocked.";
        if (window.isBlocked()) {
            throw new HouseWindowBlockedException(String.format(WINDOW_BLOCKED_ERROR, window.getId()));
        }
    }

    private <DTO, Entity> DTO changeStateOfHouseObject(final long id,
                                                       final Class<Entity> entityClassType,
                                                       final Class<DTO> dtoClassType,
                                                       final CrudRepository<Entity, Long> repository,
                                                       final Consumer<Entity> consumer) {

        final Entity entity = repository.findById(id).orElseThrow(() -> new SHSNotFoundException(getErrorMessageForHouseNotFoundObject(id, entityClassType)));
        consumer.accept(entity);
        return mvcConversionService.convert(repository.save(entity), dtoClassType);
    }
}
