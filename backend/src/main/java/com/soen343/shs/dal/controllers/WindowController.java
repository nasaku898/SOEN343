package com.soen343.shs.dal.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.soen343.shs.dal.service.WindowService;
import com.soen343.shs.dto.WindowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/house/{houseId}/room/{roomId}/window")
public class WindowController {

    private final WindowService windowService;

    @PutMapping(value = "/{windowId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    WindowDTO modifyWindowState(@PathVariable final long windowId, @RequestBody final ObjectNode objectNode) {
        return windowService.modifyWindowState(windowId,
                objectNode.get("open").asBoolean(),
                objectNode.get("desiredState").asBoolean()
        );
    }
}
