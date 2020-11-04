package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.LightService;
import com.soen343.shs.dto.LightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/house/{houseId}/room/{roomId}/light")
public class LightController {

    private final LightService lightService;

    @PutMapping(value = "/{lightId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    LightDTO modifyLightState(@PathVariable final long lightId, @RequestBody final boolean desiredState) {
        return lightService.modifyLightState(lightId, desiredState);
    }
}
