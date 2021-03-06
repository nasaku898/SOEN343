package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.RoomService;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/room/{roomId}")
public class RoomController {

    private final RoomService roomService;

    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public RoomDTO getRoom(@PathVariable final long roomId) {
        return roomService.getRoom(roomId);
    }

    @PutMapping(value = "/temperature/zone/{zoneId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    RoomDTO modifyRoomTemperature(@PathVariable final long roomId, @RequestBody final double newTemperature, @PathVariable final long zoneId) {
        final RoomDTO room = roomService.setTemperatureOfRoom(roomId, newTemperature);
        roomService.notifySHH(zoneId);
        return room;
    }

    @GetMapping(value = "/temperature")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    double getRoomTemperature(@PathVariable final long roomId) {
        return roomService.getTemperatureOfRoom(roomId);
    }
}
