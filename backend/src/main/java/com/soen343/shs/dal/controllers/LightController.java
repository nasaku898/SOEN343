package com.soen343.shs.dal.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.soen343.shs.dal.service.LightService;
import com.soen343.shs.dto.LightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/house/{houseId}/room/{roomId}/light")
public class LightController {

    private final LightService lightService;

    @PutMapping(value = "/{lightId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    LightDTO modifyLightState(@PathVariable final long houseId,
                              @PathVariable final long roomId,
                              @PathVariable final long lightId,
                              @AuthenticationPrincipal final Authentication auth,
                              @RequestBody final ObjectNode objectNode) {
        return lightService
                .modifyLightState(
                        auth.getName(),
                        roomId,
                        lightId,
                        objectNode.get("desiredState").asBoolean()
                );
    }

    @PutMapping(value = "/{lightId}/test")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    LightDTO modifyLightState(@PathVariable final long houseId,
                              @PathVariable final long roomId,
                              @PathVariable final long lightId,
                              @RequestBody final ObjectNode objectNode) {
        return lightService
                .modifyLightState(
                        objectNode.get("username").asText(),
                        roomId,
                        lightId,
                        objectNode.get("desiredState").asBoolean()
                );
    }
}
