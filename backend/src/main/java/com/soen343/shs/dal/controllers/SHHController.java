package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.SHHService;
import com.soen343.shs.dto.HouseTemperatureStatusDTO;
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

    @PutMapping(value = "/zone/{zoneId}/room/{roomId}")
    public ZoneDTO addRoomToZone(@PathVariable final long zoneId, @PathVariable final long roomId){
       return SHHService.addRoomToZone(zoneId, roomId);
    }

    @PutMapping(value = "/zone/{zoneId}")
    public ZoneDTO setZoneTemperature(@PathVariable final long zoneId, @RequestParam final double temperature){
        return SHHService.setZoneTemperature(zoneId,temperature);
    }

    @GetMapping(value = "/zone/{zoneId}")
    public Double getZoneTemperature(@PathVariable final long zoneId){
        return SHHService.getZoneTemperature(zoneId);
    }

    @GetMapping(value = "/monitor/house/{houseId}")
    public HouseTemperatureStatusDTO monitorTemperature(@PathVariable final long houseId){
        return SHHService.monitorTemperature(houseId);
    }

    @PutMapping(value = "/house/{houseId}/havc/on")
    public void turnOnHAVC(@RequestParam final String cityName, @PathVariable final long houseId){
        SHHService.turnOnHAVC(cityName,houseId);
    }

    @PutMapping(value = "/house/{houseId}/havc/off")
    public void turnOffHAVC(@RequestParam final String cityName, @PathVariable final long houseId){
        SHHService.turnOffHAVC(cityName,houseId);
    }

    @PutMapping(value = "/time")
    public boolean speedTime(@RequestParam final long timeMultiplier){
        return SHHService.speedTime(timeMultiplier);
    }
}