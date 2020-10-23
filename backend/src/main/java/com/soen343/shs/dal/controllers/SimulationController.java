package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    @GetMapping(value = "/house/houseLayout/{houseId}")
    public House getHouseLayout(@PathVariable final long houseId) {
        return simulationService.findHouse(houseId);
    }

    @PutMapping(value = "/room/newRoom/{roomId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public HouseMemberDTO moveUserToRoom(@NotNull @RequestParam final String name, @PathVariable final long roomId) {
        return simulationService.moveUserToRoom(name, roomId, HouseMember.class, HouseMemberDTO.class);
    }

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
