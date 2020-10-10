package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.*;
import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dal.service.exceptions.room.RoomNotFoundException;
import com.soen343.shs.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(path = "api/simulation")
public class SimulationController {

    private final SimulationService simulationService;
    private final HouseWindowRepository houseWindowRepository;
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final LightRepository lightRepository;
    private final ExteriorDoorRepository exteriorDoorRepository;
    private final HouseMemberRepository houseMemberRepository;

    @Autowired
    public SimulationController(SimulationService simulationService, HouseWindowRepository houseWindowRepository, RoomRepository roomRepository, HouseRepository houseRepository, LightRepository lightRepository, ExteriorDoorRepository exteriorDoorRepository, HouseMemberRepository houseMemberRepository) {
        this.simulationService = simulationService;
        this.houseWindowRepository = houseWindowRepository;
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.lightRepository = lightRepository;
        this.exteriorDoorRepository = exteriorDoorRepository;
        this.houseMemberRepository = houseMemberRepository;
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
    public void addObjectToWindow(@PathVariable final long windowId){
        simulationService.addObjectToWindow(windowId);
    }


    @PostMapping(value = "/houseMember")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createNewHouseMember(@RequestBody final HouseMemberDTO houseMemberDTO){
        simulationService.createNewHouseMember(houseMemberDTO);
    }

    @PostMapping(value = "/houseLayout")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loadHouse(@RequestBody final LoadHouseDTO loadHouseDTO){
        simulationService.loadHouse(loadHouseDTO);
    }

}
