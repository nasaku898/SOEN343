package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dto.HouseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureService {
    private final HouseRepository houseRepository;
    private final ConversionService mvcConversionService;

    public double getTemperatureInside(final long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)))
                .getRooms()
                .stream()
                .mapToDouble(Room::getTemperature)
                .average()
                .orElse(0);
    }

    public double getTemperatureOutside(final long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)))
                .getTemperatureOutside();
    }

    public HouseDTO setTemperatureOutside(final long houseId, final double temperature) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException(getHouseNotFoundErrorMessage(houseId)));
        house.setTemperatureOutside(temperature);
        return mvcConversionService.convert(houseRepository.save(house), HouseDTO.class);
    }

    private static String getHouseNotFoundErrorMessage(final long houseId) {
        return String.format("Cannot find house with houseId: %d", houseId);
    }
}
