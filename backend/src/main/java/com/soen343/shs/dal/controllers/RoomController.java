package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.RoomService;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/room")
public class RoomController {

    private final RoomService roomService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public RoomDTO getRoom(@PathVariable final long id) {
        return roomService.getRoom(id);
    }
}
