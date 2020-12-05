package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.CityRepository;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.repository.ZoneRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.HouseTemperatureStatusDTO;
import com.soen343.shs.dto.ZoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class SHHService {

    private static boolean isHAVCOn = false;
    private static long timeMultiplier = 1;
    private final ZoneRepository zoneRepository;
    private final HouseRepository houseRepository;
    private final CityRepository cityRepository;
    private final ConversionService mvcConversionService;
    private final RoomRepository roomRepository;
    private final HouseService houseService;
    private final TimeService timeService;

    /**
     *
     * @param houseId A house id number
     * @return ZoneDTO reflecting a Zone model
     */
    public ZoneDTO createZone(final long houseId) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException("House Not Found"));

        Set<Zone> zones = house.getZones();
        final Zone zone = Zone.builder()
                .temperature(getOutsideTemperature(house.getCity()))
                .zoneState(ZoneState.ZONE)
                .rooms(new HashSet<Room>())
                .build();
        zones.add(zone);

        house.setZones(zones);
        houseRepository.save(house);

        return mvcConversionService.convert(zone, ZoneDTO.class);
    }

    /**
     *
     * @param houseId A house id number
     * @return Set of ZoneDTO reflecting the zone fetched
     */
    public Set<Zone> fetchZones(final long houseId) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException("House Not Found"));
        return house.getZones();
    }

    /**
     *
     * @param zoneId A zone id number
     * @param roomId A room id number
     * @return ZoneDTO reflecting the zone
     */
    public ZoneDTO addRoomToZone(final long zoneId, final long roomId) {
        final Zone zone = getZone(zoneId);
        final Room room = roomRepository.findById(roomId).orElseThrow(() -> new SHSNotFoundException("Room Not Found"));
        zone.getRooms().add(room);
        return mvcConversionService.convert(zoneRepository.save(zone), ZoneDTO.class);
    }


    /**
     *
     * @param zoneId A zone id number
     * @return the temperature of the zone
     */
    public double getZoneTemperature(final long zoneId) {
        final Zone zone = getZone(zoneId);
        return zone.getTemperature();
    }

    /**
     *
     * @param zoneId A zone id number
     * @param temperature new temperature for zone
     * @return ZoneDTO reflecting the zone
     */
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

    /**
     *
     * @param houseId A house id number
     */
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

    /**
     *
     * @param houseId A house id number
     * @return HouseTemperatureStatusDTO reflecting the status of the monitoring
     */
    public HouseTemperatureStatusDTO monitorTemperature(final long houseId) {
        final House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseNotFoundException("House Not Found"));
        double insideTemperature = getInsideTemperature(houseId);
        double outsideTemperature = getOutsideTemperature(house.getCity());

        final HouseTemperatureStatusDTO houseTemperatureStatusDTO = new HouseTemperatureStatusDTO();

        if (insideTemperature <= 0) {
            houseTemperatureStatusDTO.setAlertMessage("Inside temperature is below 0, pipes might have burst");
            houseTemperatureStatusDTO.setSuccess(false);
        }

        if (outsideTemperature < insideTemperature && !house.getSecuritySystem().getAway()) {
            List<Room> rooms = roomRepository.findAll();
            rooms.forEach(room -> {
                room.getHouseWindows().forEach(houseWindow -> {
                    if (houseWindow.getBlocked() == false) {
                        houseWindow.setOpen(false);
                        houseTemperatureStatusDTO.setSuccess(false);
                    } else {
                        houseTemperatureStatusDTO.setAlertMessage(houseTemperatureStatusDTO.getAlertMessage() + ", Windows ID:" + houseWindow.getId() + " is blocked");
                        houseTemperatureStatusDTO.setSuccess(false);
                    }
                });
            });

            roomRepository.saveAll(rooms);
        } else if (house.getSecuritySystem().getAway()) {
            houseTemperatureStatusDTO.setSuccess(false);
            houseTemperatureStatusDTO.setAlertMessage("House is in away mode, can't open window");
        }

        return houseTemperatureStatusDTO;
    }

    /**
     *
     * @param cityName String representing the cityName
     * @param houseId A house id number
     */
    @Async
    public void turnOnHAVC(final String cityName, final long houseId) {
        try {
            isHAVCOn = true;
            double insideTemperature = getInsideTemperature(houseId);
            double outsideTemperature = getOutsideTemperature(cityName);
            final List<Room> rooms = roomRepository.findAll();

            while (isHAVCOn && insideTemperature > outsideTemperature) {
                rooms.forEach(room -> {
                    room.setTemperature(room.getTemperature() - 0.1);
                });

                roomRepository.saveAll(rooms);

                Thread.sleep(1000 / timeMultiplier);

                insideTemperature = getInsideTemperature(houseId);
                outsideTemperature = getOutsideTemperature(cityName);
            }
        } catch (InterruptedException e) {
            //
        }
    }

    /**
     *
     * @param cityName String representing the cityName
     * @param houseId A house id number
     */
    @Async
    public void turnOffHAVC(final String cityName, final long houseId) {
        isHAVCOn = false;
        try {
            double insideTemperature = getInsideTemperature(houseId);
            double outsideTemperature = getOutsideTemperature(cityName);

            final List<Room> rooms = roomRepository.findAll();

            while (!isHAVCOn) {
                if (insideTemperature > outsideTemperature) {
                    while (!isHAVCOn && insideTemperature > outsideTemperature) {
                        rooms.forEach(room -> {
                            room.setTemperature(room.getTemperature() - 0.05);
                        });

                        roomRepository.saveAll(rooms);

                        Thread.sleep(1 / timeMultiplier);

                        insideTemperature = getInsideTemperature(houseId);
                        outsideTemperature = getOutsideTemperature(cityName);
                    }
                } else {
                    while (!isHAVCOn && insideTemperature < outsideTemperature) {
                        System.out.println("Changing temp");
                        rooms.forEach(room -> {
                            room.setTemperature(room.getTemperature() + 0.05);
                        });

                        roomRepository.saveAll(rooms);

                        Thread.sleep(1 / timeMultiplier);

                        insideTemperature = getInsideTemperature(houseId);
                        outsideTemperature = getOutsideTemperature(cityName);
                    }
                }
            }
        } catch (InterruptedException e) {
            //
        }
    }

    private double getInsideTemperature(final long houseId){
        return houseService.getTemperatureInside(houseId);
    }

    private double getOutsideTemperature(final String cityName){
        return getCity(cityName).getTemperatureOutside();
    }

    /**
     *
     * @param period String as key representing the period
     */
    public void monitorPeriod(String period) {
        zoneRepository.findAll().forEach(zone -> {
            if (period.equalsIgnoreCase("Morning")) {
                double temperature = zone.getPeriods().get("Morning");
                setZoneTemperature(zone.getId(), temperature);
            } else if (period.equalsIgnoreCase("Afternoon")) {
                double temperature = zone.getPeriods().get("Afternoon");
                setZoneTemperature(zone.getId(), temperature);
            } else if (period.equalsIgnoreCase("Night")) {
                double temperature = zone.getPeriods().get("Night");
                setZoneTemperature(zone.getId(), temperature);
            }
        });
    }

    /**
     *
     * @param multiplier timeMultiplier value
     * @return boolean representing the success
     */
    public boolean speedTime(final long multiplier) {
        timeMultiplier = multiplier;
        return true;
    }

    /**
     *
     * @param zoneId A zone id number
     */
    public void update(final long zoneId) {
        updateZoneStatus(zoneId);
    }

    /**
     *
     * @param zoneId A zone id number
     */
    public Zone getZone(final long zoneId) {
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