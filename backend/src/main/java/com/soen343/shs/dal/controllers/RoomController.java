package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.RoomService;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/room")
public class RoomController {

    private RoomService roomService;

    @GetMapping(value = "/{roomId}")
    @ResponseStatus(value = HttpStatus.OK)
    public RoomDTO getRoom(@RequestBody final long id) {
        return roomService.getRoom(id);
    }
}
