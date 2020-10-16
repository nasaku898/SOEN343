package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.LoadHouseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(path = "api/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(final SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping(value = "/house/houseLayout/{houseId}")
    @ResponseBody
    public House getHouseLayout(@PathVariable final long houseId) {
        return simulationService.findHouse(houseId);
    }

    @PutMapping(value = "/room/newRoom/{roomId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void moveUserToRoom(@NotNull @RequestParam final String name, @PathVariable final long roomId) {
        simulationService.moveUserToRoom(name, roomId);
    }

    @PutMapping(value = "/window/windowObject/{windowId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void addObjectToWindow(@PathVariable final long windowId) {
        simulationService.addObjectToWindow(windowId);
    }

    @PostMapping(value = "/houseMember")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public HouseMemberDTO createNewHouseMember(@RequestBody final HouseMemberDTO houseMemberDTO) {
        return simulationService.createNewHouseMember(houseMemberDTO);
    }

    @PostMapping(value = "/houseLayout")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loadHouse(@RequestBody final LoadHouseDTO loadHouseDTO) {
        simulationService.loadHouse(loadHouseDTO);
    }
}
