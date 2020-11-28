package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.SHHService;
import com.soen343.shs.dto.ZoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="api/shh")
public class SHHController {

    private final SHHService SHHService;

    @PostMapping(value = "/zone")
    public ZoneDTO createZone(@RequestParam final long houseId) {
        return SHHService.createZone(houseId);
    }

    @PutMapping(value = "/zone/{zoneId}")
    public ZoneDTO setZoneTemperature(@PathVariable final long zoneId, @RequestParam final double temperature){
        return SHHService.setZoneTemperature(zoneId,temperature);
    }

    @GetMapping(value = "/zone/{zoneId}")
    public Double getZoneTemperature(@PathVariable final long zoneId){
        return SHHService.getZoneTemperature(zoneId);
    }
}
