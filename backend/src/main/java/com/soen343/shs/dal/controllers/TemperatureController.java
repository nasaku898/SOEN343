package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.TemperatureService;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/simulation")
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureService temperatureService;

    @PutMapping(value = "/room/{roomId}/temperature/{newTemperature}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    RoomDTO modifyRoomTemperature(@PathVariable final long roomId, @PathVariable final double newTemperature) {
        return temperatureService.setTemperatureOfRoom(roomId, newTemperature);
    }

    @PutMapping(value = "/house/{houseId}/temperature/{newTemperature}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    HouseDTO updateTemperatureOutside(@PathVariable final long houseId, @PathVariable final double newTemperature) {
        return temperatureService.setTemperatureOutside(houseId, newTemperature);
    }

    @PutMapping(value = "/house/{houseId}/temperatureOutside")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    HouseDTO setTemperatureOutside(@PathVariable final long houseId, final double temperatureOutside) {
        return temperatureService.setTemperatureOutside(houseId, temperatureOutside);
    }
}
