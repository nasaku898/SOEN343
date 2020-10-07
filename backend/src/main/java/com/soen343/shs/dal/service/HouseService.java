package com.soen343.shs.dal.service;

import com.soen343.shs.DTO.HouseDTO;
import com.soen343.shs.converters.HouseToHouseDTOConverter;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class HouseService {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseToHouseDTOConverter houseToHouseDTOConverter;

    public double getTemperatureOutside(long houseId) {
        // Find house by id
        Optional<House> house = houseRepository.findById(houseId);

        // Return error code if house does not exist
        if (!house.isPresent()) {
            return -1;
        }

        // convert House to HouseDTO
        HouseDTO houseDTO = houseToHouseDTOConverter.convert(house.get());

        // Return only the temperatureOutside of the HouseDTO
        return houseDTO.getTemperatureOutside();
    }

    public String setTemperatureOutside(long houseId, double temperature) {
        // Find house by id
        Optional<House> optionalHouse = houseRepository.findById(houseId);

        // Return error code if house does not exist
        if (!optionalHouse.isPresent()) {
            return "Error: House does not exist";
        }

        try {
            // Edit temperatureOutside of house
            House house = optionalHouse.get();
            house.setTemperatureOutside(temperature);

            // Save the updated house
            houseRepository.save(house);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        return null;
    }
}
