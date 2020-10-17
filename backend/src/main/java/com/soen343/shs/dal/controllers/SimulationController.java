package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.service.HouseMemberService;
import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.LoadHouseDTO;
import com.soen343.shs.dto.RoomDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "api/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(final SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping(value = "/house/houseLayout/{houseId}")
    public House getHouseLayout(@PathVariable final long houseId) {
        return simulationService.findHouse(houseId);
    }

    @PutMapping(value = "/room/newRoom/{roomId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public HouseMemberDTO moveUserToRoom(@NotNull @RequestParam final String name, @PathVariable final long roomId) {
        return simulationService.moveUserToRoom(name, roomId);
    }

    @PutMapping(value = "/window/windowObject/{windowId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void addObjectToWindow(@PathVariable final long windowId) {
        simulationService.addObjectToWindow(windowId);
    }

    @PostMapping(value = "/houseLayout")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loadHouse(@RequestBody final LoadHouseDTO loadHouseDTO) {
        simulationService.loadHouse(loadHouseDTO);
    }

    @GetMapping(value = "/room/all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<RoomDTO> findAllRoom() {
        return simulationService.findAllRoom();
    }
}
