package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    @PutMapping(value = "/house/{houseId}/temperatureOutside/{temperatureOutside}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    HouseDTO updateTemperatureOutside(@PathVariable final long houseId, @PathVariable final double temperatureOutside) {
        return simulationService.setTemperatureOutside(houseId, temperatureOutside);
    }

//    @PutMapping(value = "/user/{username}/room/{roomId}")
//    @ResponseStatus(value = HttpStatus.ACCEPTED)
//    @ResponseBody
//    public UserDTO moveUserToRoom(@NotNull @RequestParam final String username, @PathVariable final long roomId) {
//        return simulationService.moveUserToRoom(username, roomId, );
//    }

    @GetMapping(value = "/house/{houseId}/room/all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<RoomDTO> findAllRoom(@PathVariable final long houseId) {
        return simulationService.findAllRooms(houseId);
    }

    @GetMapping(value = "/house/{houseId}/roomState/all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Set<Room> fetchRoomsState(@PathVariable final long houseId) {
        return simulationService.fetchRoomsState(houseId);
    }
}
