package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.CityRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.ZoneRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.ZoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class SHHService {

    private final ZoneRepository zoneRepository;
    private final HouseRepository houseRepository;
    private final CityRepository cityRepository;
    private final TimeService timeService;
    private final ConversionService mvcConversionService;

    public ZoneDTO createZone(final long houseId) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException("House Not Found"));

        Set<Zone> zones = house.getZones();
        final Zone zone = Zone.builder()
                .temperature(getCity(house.getCity()).getTemperatureOutside())
                .zoneState(ZoneState.ZONE)
                .rooms(house.getRooms())
                .build();
        zones.add(zone);

        house.setZones(zones);
        houseRepository.save(house);

        return mvcConversionService.convert(zone, ZoneDTO.class);
    }

    public double getZoneTemperature(final long zoneId) {
        final Zone zone = getZone(zoneId);
        return zone.getTemperature();
    }

    public ZoneDTO setZoneTemperature(final long zoneId, final double temperature) {
        final Zone zone = getZone(zoneId);
        zone.setTemperature(temperature);
        zone.getRooms().forEach(room -> {
            room.setTemperature(temperature);
        });

        return mvcConversionService.convert(zoneRepository.save(zone), ZoneDTO.class);
    }

    private void updateZoneStatus(final long zoneId) {
        final Zone zone = getZone(zoneId);
        zone.getRooms().forEach(room -> {
            if (room.getTemperature() != zone.getTemperature()) {
                zone.setZoneState(ZoneState.OVERRIDDEN);
                return;
            }
        });
    }

    public void updateDefaultZoneTemperature(final long houseId) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException("House Not Found"));
        final Set<Zone> zones = house.getZones();

        final int currentMonth = LocalDateTime.now(ClockSingleton.getClock()).getMonthValue();

        double temperature = 0;

        if (currentMonth > 9 || (currentMonth >= 1 && currentMonth < 4)) {
            temperature = 30;
                    //house.getWINTER_TEMPERATURE();
        } else {
            temperature = 25;
                    //house.getSUMMER_TEMPERATURE();
        }

        double finalTemperature = temperature;
        zones.forEach(zone -> {
            setZoneTemperature(zone.getId(), finalTemperature);
        });
    }

    public void update(final long zoneId) {
        updateZoneStatus(zoneId);
    }

    private Zone getZone(final long zoneId) {
        return zoneRepository.findById(zoneId).orElseThrow(() -> new SHSNotFoundException("Zone not found"));
    }

    private City getCity(final String name) {
        Optional<City> cityOptional = cityRepository.findByName(name);
        if (cityOptional.isPresent()) {
            return cityOptional.get();
        } else {
            //Add Exception
            return null;
        }
    }
}
