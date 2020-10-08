package com.soen343.shs.dal.service;

import com.soen343.shs.DTO.HouseDTO;
import com.soen343.shs.converters.HouseToHouseDTOConverter;
import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.service.exceptions.HouseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TemperatureService {
    @Autowired
    private final HouseRepository houseRepository;
    @Autowired
    private final HouseToHouseDTOConverter houseToHouseDTOConverter;

    public double getTemperatureInside(long houseId) {
        // Find house by id
        Optional<House> house = houseRepository.findById(houseId);

        // Return error code if house does not exist
        if (!house.isPresent()) {
            throw new HouseNotFoundException("Error: House with ID " + houseId + " does not exist. Please enter a valid house ID.");
        }

        // Get the average temperature of all the House's Rooms
        double averageTemperatureInside = 0;
        Set<Room> rooms = house.get().getRooms();
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
            throw new HouseNotFoundException("Error: House with ID " + houseId + " does not exist. Please enter a valid house ID.");
        }

        // Convert House to HouseDTO
        HouseDTO houseDTO = houseToHouseDTOConverter.convert(house.get());

        // Return only the temperatureOutside of the HouseDTO
        return houseDTO.getTemperatureOutside();
    }

    public void setTemperatureOutside(long houseId, double temperature) {
        // Find house by id
        Optional<House> optionalHouse = houseRepository.findById(houseId);

        // Return error code if house does not exist
        if (!optionalHouse.isPresent()) {
            throw new HouseNotFoundException("Error: House with ID " + houseId + " does not exist. Please enter a valid house ID.");
        }

        // Edit temperatureOutside of house
        House house = optionalHouse.get();
        house.setTemperatureOutside(temperature);

        // Save the updated house
        houseRepository.save(house);
    }
}
