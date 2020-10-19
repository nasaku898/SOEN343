package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.HouseService;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/house")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PutMapping(value = "/light/{lightId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public LightDTO modifyLightState(@PathVariable final long lightId, @RequestParam final boolean desiredState) {
        return houseService.modifyLightState(lightId, desiredState);
    }

    @PutMapping(value = "/exteriorDoor/{doorId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public DoorDTO modifyExteriorDoorState(@PathVariable final long doorId, @RequestParam final boolean open, final boolean desiredState) {
        return houseService.modifyExteriorDoorState(doorId, open, desiredState);
    }

    @PutMapping(value = "/window/{windowId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public WindowDTO modifyWindowState(@PathVariable final long windowId, @RequestParam final boolean open, final boolean desiredState) {
        return houseService.modifyWindowState(windowId, open, desiredState);
    }
}
