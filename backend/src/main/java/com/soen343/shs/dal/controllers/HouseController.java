package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.HouseService;
import com.soen343.shs.dto.DoorDTO;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.LightDTO;
import com.soen343.shs.dto.WindowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/house")
public class HouseController {
    private final HouseService houseService;

    @PutMapping(value = "/light/{lightId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    LightDTO modifyLightState(@PathVariable final long lightId, @RequestParam final boolean desiredState) {
        return houseService.modifyLightState(lightId, desiredState);
    }

    @PutMapping(value = "/exteriorDoor/{doorId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyExteriorDoorState(@PathVariable final long doorId, @RequestParam final boolean open, final boolean desiredState) {
        return houseService.modifyExteriorDoorState(doorId, open, desiredState);
    }

    @PutMapping(value = "/window/{windowId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    WindowDTO modifyWindowState(@PathVariable final long windowId, @RequestParam final boolean open, final boolean desiredState) {
        return houseService.modifyWindowState(windowId, open, desiredState);
    }

    @GetMapping(value = "/{houseId}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    HouseDTO getHouse(@PathVariable final Long id) {
        return houseService.getHouse(id);
    }

}
