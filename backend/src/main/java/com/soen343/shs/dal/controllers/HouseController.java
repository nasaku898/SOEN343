package com.soen343.shs.dal.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.soen343.shs.dal.service.HouseService;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/house")
public class HouseController {
    private final HouseService houseService;

    @PutMapping(value = "/light/{lightId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    LightDTO modifyLightState(@PathVariable final long lightId, @RequestBody final boolean desiredState) {
        return houseService.modifyLightState(lightId, desiredState);
    }

    @PutMapping(value = "/exteriorDoor/{doorId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyExteriorDoorState(@PathVariable final long doorId, @RequestBody final ObjectNode objectNode) {
        return houseService
                .modifyExteriorDoorState(
                        doorId,
                        objectNode.get("open").asBoolean(),
                        objectNode.get("desiredState").asBoolean()
                );
    }

    @PutMapping(value = "/interiorDoor/{doorId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyInteriorDoorState(@PathVariable final long doorId, @RequestBody final boolean desiredState) {
        return houseService.modifyInteriorDoorState(doorId, desiredState);
    }

    @PutMapping(value = "/window/{windowId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    WindowDTO modifyWindowState(@PathVariable final long windowId, @RequestBody final ObjectNode objectNode) {
        return houseService.modifyWindowState(windowId,
                objectNode.get("open").asBoolean(),
                objectNode.get("desiredState").asBoolean()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    HouseDTO getHouse(@PathVariable final long id) {
        return houseService.getHouse(id);
    }

    @GetMapping(value = "/houses")
    @ResponseStatus(value = HttpStatus.OK)
    public Set<HouseDTO> getAllHouses() {
        return houseService.getAllHouses();
    }
}
