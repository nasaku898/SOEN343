package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.LoadSimulationService;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.LoadHouseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/simulation")
public class LoadSimulationController {
    private final LoadSimulationService loadSimulationService;

    @PostMapping(value = "/houseLayout")
    @ResponseStatus(value = HttpStatus.CREATED)
    public HouseDTO loadHouse(@RequestBody final LoadHouseDTO loadHouseDTO, @AuthenticationPrincipal final Authentication authentication) {
        return loadSimulationService.loadHouse(loadHouseDTO, authentication.getName());
    }
}