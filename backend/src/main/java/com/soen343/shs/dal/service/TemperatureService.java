package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final HouseRepository houseRepository;
    private final ConversionService mvcConversionService;
    private final RoomRepository roomRepository;
    private final ModelFetchHandler modelFetchHandler;

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
     * @param houseId a house id
     * @return outside temperature
     */
    public double getTemperatureOutside(final long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)))
                .getTemperatureOutside();
    }

    /**
     * @param houseId     a house id
     * @param temperature new outside temperature
     * @return HouseDTO object reflecting the changes made to the object
     */
    public HouseDTO setTemperatureOutside(final long houseId, final double temperature) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)));
        house.setTemperatureOutside(temperature);
        return mvcConversionService.convert(houseRepository.save(house), HouseDTO.class);
    }

    /**
     *
     * @param roomId a room id
     * @param newTemperature new temperature
     * @return RoomDTO object reflecting the changes made to the object
     */
    public RoomDTO setTemperatureOfRoom(final long roomId, final double newTemperature){
        Room room = modelFetchHandler.findRoom(roomId);
        room.setTemperature(newTemperature);
        return mvcConversionService.convert(roomRepository.save(room), RoomDTO.class);
    }
}
