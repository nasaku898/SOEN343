package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.TemperatureService;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/simulation")
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureService temperatureService;

    @PutMapping(value = "/room/{roomId}/temperature/{newTemperature}")
    public RoomDTO modifyRoomTemperature(@PathVariable final long roomId, @PathVariable final double newTemperature) {
        return temperatureService.setTemperatureOfRoom(roomId, newTemperature);
    }
}
