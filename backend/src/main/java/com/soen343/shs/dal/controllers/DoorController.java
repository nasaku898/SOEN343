package com.soen343.shs.dal.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.soen343.shs.dal.service.DoorService;
import com.soen343.shs.dto.DoorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/house/{houseId}/room/{roomId}")
public class DoorController {

    private final DoorService doorService;

    @PutMapping(value = "/exteriorDoor/{doorId}/test")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyExteriorDoorState(@PathVariable final long houseId,
                                    @PathVariable final long roomId,
                                    @PathVariable final long doorId,
                                    @RequestBody final ObjectNode objectNode) {

        return doorService
                .modifyExteriorDoorState(
                        objectNode.get("username").asText(),
                        roomId,
                        doorId,
                        objectNode.get("open").asBoolean(),
                        objectNode.get("desiredState").asBoolean()
                );
    }

    @PutMapping(value = "/exteriorDoor/{doorId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyExteriorDoorState(@PathVariable final long houseId,
                                    @PathVariable final long roomId,
                                    @PathVariable final long doorId,
                                    @AuthenticationPrincipal final Authentication auth,
                                    @RequestBody final ObjectNode objectNode) {

        return doorService
                .modifyExteriorDoorState(
                        auth.getName(),
                        roomId,
                        doorId,
                        objectNode.get("open").asBoolean(),
                        objectNode.get("desiredState").asBoolean()
                );
    }

    @PutMapping(value = "/interiorDoor/{doorId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyInteriorDoorState(@PathVariable final long houseId,
                                    @PathVariable final long roomId,
                                    @PathVariable final long doorId,
                                    @RequestBody final ObjectNode objectNode) {
        return doorService
                .modifyInteriorDoorState(
                        objectNode.get("username").asText(),
                        roomId,
                        doorId,
                        objectNode.get("desiredState").asBoolean()
                );
    }

    @PutMapping(value = "/interiorDoor/{doorId}/test")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    DoorDTO modifyInteriorDoorState(@PathVariable final long houseId,
                                    @PathVariable final long roomId,
                                    @PathVariable final long doorId,
                                    @AuthenticationPrincipal final Authentication auth,
                                    @RequestBody final ObjectNode objectNode) {
        return doorService
                .modifyInteriorDoorState(
                        auth.getName(),
                        roomId,
                        doorId,
                        objectNode.get("desiredState").asBoolean()
                );
    }
}
