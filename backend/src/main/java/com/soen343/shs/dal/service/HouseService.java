package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.HouseWindow;
import com.soen343.shs.dal.model.Light;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.ExteriorDoorRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.HouseWindowRepository;
import com.soen343.shs.dal.repository.LightRepository;
import com.soen343.shs.dal.service.exceptions.IllegalStateException;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dal.service.exceptions.state.SHSSameStateException;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.HouseDTO;
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
    private final HouseRepository houseRepository;
    private final ExteriorDoorRepository exteriorDoorRepository;
    private final ConversionService mvcConversionService;

    public HouseDTO getHouse(final long id) {
        return mvcConversionService.convert(houseRepository.findById(id)
                .orElseThrow(() -> new HouseNotFoundException("House Not Found")), HouseDTO.class);
    }

    /**
     * @param id           id of light object to modify
     * @param desiredState boolean referring to the desired state of the object
     * @return LightDTO object reflecting the changes made to the object
     */
    public LightDTO modifyLightState(final long id, final boolean desiredState) {
        return changeStateOfHouseObject(id, Light.class, LightDTO.class, lightRepository,
                light -> {
                    checkForSameStateException(desiredState, light.getIsLightOn(), getSameStateExceptionErrorMessage(light.getClass(), id));
                    light.setIsLightOn(desiredState);
                }
        );
    }

    /**
     * @param id           id of door to modify
     * @param open         boolean referring to if we should try to open the door or not
     * @param desiredState boolean referring to the desired state of the object
     * @return DoorDTO reflecting to the changes made to the object
     */
    public DoorDTO modifyExteriorDoorState(final long id, final boolean open, final boolean desiredState) {
        return changeStateOfHouseObject(id, ExteriorDoor.class, DoorDTO.class, exteriorDoorRepository,
                door -> {
                    final String sameStateExceptionErrorMessage = getSameStateExceptionErrorMessage(door.getClass(), id);
                    if (open) {
                        checkForIllegalStateException(ExteriorDoor.class, id, door.getLocked());
                        checkForSameStateException(desiredState, door.getOpen(), sameStateExceptionErrorMessage);
                        door.setOpen(desiredState);
                    } else {
                        checkForIllegalStateException(ExteriorDoor.class, id, door.getOpen());
                        checkForSameStateException(desiredState, door.getLocked(), sameStateExceptionErrorMessage);
                        door.setLocked(desiredState);
                    }
                }
        );
    }

    /**
     * @param id           id of window  object to modify
     * @param open         boolean referring to if we should try to open the window or not
     * @param desiredState boolean referring to the desired state of the object
     * @return WindowDTO object reflecting the changes made to the object
     */
    public WindowDTO modifyWindowState(final long id, final boolean open, final boolean desiredState) {
        return changeStateOfHouseObject(id, HouseWindow.class, WindowDTO.class, houseWindowRepository,
                window -> {
                    final String sameStateExceptionErrorMessage = getSameStateExceptionErrorMessage(window.getClass(), id);
                    if (open) {
                        checkForSameStateException(desiredState, window.getOpen(), sameStateExceptionErrorMessage);
                        checkForIllegalStateException(HouseWindow.class, id, window.getBlocked());
                        window.setOpen(desiredState);
                    } else {
                        checkForSameStateException(desiredState, window.getBlocked(), sameStateExceptionErrorMessage);
                        checkForIllegalStateException(HouseWindow.class, id, window.getOpen());
                        window.setBlocked(desiredState);
                    }
                }
        );
    }

    private static void checkForSameStateException(final boolean desiredState, final boolean currentState, final String sameStateExceptionErrorMessage) {
        if (desiredState && currentState || (!desiredState && !currentState)) {
            throw new SHSSameStateException(sameStateExceptionErrorMessage);
        }
    }

    /**
     * @param id        id of the object
     * @param classType class type of object
     * @param <Entity>  class of object
     * @return new string formatted to return our error message
     */
    private static <Entity> String getSHSNotFoundErrorMessage(final long id, final Class<Entity> classType) {
        return String.format("Error: %s with ID: %d does not exist. Please enter a valid %s id.", classType.getName(), id, classType.getName());
    }

    /**
     * @param id        id of the object
     * @param classType class type of object
     * @param <Entity>  class of object
     * @return new string formatted to return our error message
     */
    private static <Entity> String getSameStateExceptionErrorMessage(final Class<Entity> classType, final long id) {
        return String.format("Error: object: %s with id: %d is already in the expected state", classType.getName(), id);
    }

    /**
     * @param classType
     * @param id
     * @param stateToCheck
     * @param <Entity>
     */
    private static <Entity> void checkForIllegalStateException(final Class<Entity> classType, final long id, final boolean stateToCheck) {
        final String ERROR_MSG = String.format("Error: object: %s with id: %d cannot be opened since it is blocked.", classType.getName(), id);
        if (stateToCheck) {
            throw new IllegalStateException(String.format(ERROR_MSG, id));
        }
    }


    /**
     * @param houseId a house id
     * @return error message
     */
    private static String getHouseNotFoundErrorMessage(final long houseId) {
        return String.format("Cannot find house with houseId: %d", houseId);
    }

    /**
     * @param houseId a house id
     * @return inside temperature
     */
    public double getTemperatureInside(final long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)))
                .getRooms()
                .stream()
                .mapToDouble(Room::getTemperature)
                .average()
                .orElse(0);
    }

    /**
     * @param houseId     a house id
     * @param temperature new outside temperature
     * @return HouseDTO object reflecting the changes made to the object
     */
    public HouseDTO setTemperatureOutside(final long houseId, final double temperature) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)));
        house.getExteriorSurrounding().setTemperatureOutside(temperature);
        return mvcConversionService.convert(houseRepository.save(house), HouseDTO.class);
    }

    /**
     * @param houseId a house id
     * @return outside temperature
     */
    public double getTemperatureOutside(final long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)))
                .getExteriorSurrounding().getTemperatureOutside();
    }

    /**
     * @param id              id of object
     * @param entityClassType entity object
     * @param dtoClassType    dto object
     * @param repository      repository object
     * @param consumer        consumer function to accept
     * @param <DTO>           generic object to be passed as a param
     * @param <Entity>        generic object to be passed as a param
     * @return DTO object reflecting changes made to the object
     */
    private <DTO, Entity> DTO changeStateOfHouseObject(final long id,
                                                       final Class<Entity> entityClassType,
                                                       final Class<DTO> dtoClassType,
                                                       final CrudRepository<Entity, Long> repository,
                                                       final Consumer<Entity> consumer) {

        final Entity entity = repository.findById(id).orElseThrow(() -> new SHSNotFoundException(getSHSNotFoundErrorMessage(id, entityClassType)));
        consumer.accept(entity);
        return mvcConversionService.convert(repository.save(entity), dtoClassType);
    }
}
