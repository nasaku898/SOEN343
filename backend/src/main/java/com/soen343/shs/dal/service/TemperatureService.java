package com.soen343.shs.dal.service;

import com.soen343.shs.DTO.HouseDTO;
import com.soen343.shs.converters.HouseToHouseDTOConverter;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class TemperatureService {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseToHouseDTOConverter houseToHouseDTOConverter;

    public double getTemperatureInside(long houseId) {
        // Find house by id
        Optional<House> house = houseRepository.findById(houseId);

        // Return error code if house does not exist
        if (!house.isPresent()) {
            return -1;
        }

        // Convert House to HouseDTO
        HouseDTO houseDTO = houseToHouseDTOConverter.convert(house.get());

        // Get the average temperature of all the House's Rooms
        double averageTemperatureInside = 0;
        Set<Room> rooms = houseDTO.getRooms();
        for (Room room : rooms) {
            averageTemperatureInside += room.getTemperature();
        }
        averageTemperatureInside /= rooms.size();

        return averageTemperatureInside;
    }

    public double getTemperatureOutside(long houseId) {
        // Find house by id
        Optional<House> house = houseRepository.findById(houseId);

        // Return error code if house does not exist
        if (!house.isPresent()) {
            return -1;
        }

        // Convert House to HouseDTO
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
