package com.soen343.shs.dal.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.soen343.shs.dal.service.DoorService;
import com.soen343.shs.dto.DoorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/house/{houseId}/room/{roomId}")
public class DoorController {

    private final DoorService doorService;

    @PutMapping(value = "/exteriorDoor/{doorId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyExteriorDoorState(@PathVariable final long doorId, @RequestBody final ObjectNode objectNode) {

        return doorService
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
        return doorService.modifyInteriorDoorState(doorId, desiredState);
    }
}
