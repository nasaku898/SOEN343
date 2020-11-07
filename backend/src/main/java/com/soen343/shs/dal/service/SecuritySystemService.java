package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.repository.SecuritySystemRepository;
import com.soen343.shs.dal.service.events.UserEntersRoomEvent;
import com.soen343.shs.dal.service.exceptions.IllegalStateException;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dal.service.validators.helper.ErrorMessageGenerator;
import com.soen343.shs.dto.SecuritySystemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
@RequiredArgsConstructor
public class SecuritySystemService {

    private final SecuritySystemRepository repository;
    private final ConversionService mvcConversionService;
    private final HouseService houseService;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final TimeService timeService;
    private final Timer timer;

    /**
     * @param id of the security system
     * @return dto object containing the current state of the system
     */
    public SecuritySystemDTO getSHSSecurity(final long id) {
        return mvcConversionService.convert(getSecuritySystem(id), SecuritySystemDTO.class);
    }

    private SecuritySystem getSecuritySystem(final long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new SHSNotFoundException(ErrorMessageGenerator.getSHSNotFoundErrorMessage(id, SecuritySystem.class)));
    }

    /**
     * @param houseId the id of the house the security system belongs to
     * @return a DTO showing the properties of the newly created security system
     */
    public SecuritySystemDTO createSecuritySystem(final long houseId, final long delay) {
        final SecuritySystem system = repository.save(SecuritySystem.builder()
                .auto(false)
                .houseId(houseId)
                .awayMode(AwayMode.builder()
                        .active(false)
                        .build())
                .build());

        return mvcConversionService.convert(system, SecuritySystemDTO.class);
    }

    /**
     * @param desiredState the desired state of the away mode setting
     * @param id           id of the security system
     * @return SHSSecurityDTO showing the state of the security system after the new update
     */
    public SecuritySystemDTO toggleAway(final boolean desiredState, final long id) {
        final SecuritySystem securitySystem = getSecuritySystem(id);
        securitySystem.getAwayMode().setActive(desiredState);

        if (desiredState) {
            houseService.fetchHouse(securitySystem.getHouseId()).getRooms()
                    .forEach(
                            room -> {
                                if (!room.getUserIds().isEmpty()) {
                                    throw new IllegalStateException("Away mode can only be set when the house is unoccupied!");
                                }

                                room.getDoors().forEach(
                                        door -> {
                                            door.setOpen(false);
                                            if (door instanceof ExteriorDoor) {
                                                ((ExteriorDoor) door).setLocked(true);
                                            }
                                        });

                                room.getLights()
                                        .stream()
                                        .filter(Light::getAwayMode)
                                        .filter(light -> compareTime(light.getStart(), light.getEnd()))
                                        .forEach(light -> light.setIsLightOn(true));

                                room.getHouseWindows().forEach(houseWindow -> houseWindow.setOpen(false));
                                roomRepository.save(room);
                            });
        }
        return saveSecuritySystemDTO(securitySystem);
    }


    public SecuritySystemDTO toggleAutoMode(final long id, final boolean desiredState) {
        final SecuritySystem securitySystem = getSecuritySystem(id);
        securitySystem.setAuto(desiredState);
        return saveSecuritySystemDTO(securitySystem);
    }


    /**
     * This method listens for an event to occur and then fires off the following code
     *
     * @param event event that will trigger our listener
     */
    @EventListener
    public String userEntersRoomListener(final UserEntersRoomEvent event) {
        final SecuritySystem system = getSecuritySystem(event.getHouseId());

        if (system.getAwayMode().getActive()) {
            final TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    alertAuthorities();
                }
            };
            timer.schedule(timerTask, system.getAwayMode().getIntruderDetectionDelay());
        }

        if (system.getAuto()) {
            final Room room = roomService.fetchRoom(event.getRoomId());
            room.getLights().forEach(light -> light.setIsLightOn(true));
            roomRepository.save(room);
        }
        return "Presence of user has been detected";
    }

    private static void alertAuthorities() {
        System.out.printf("AUTHORITIES HAVE BEEN NOTIFIED on %s%n", new Date());
    }

    private boolean compareTime(final LocalTime start, final LocalTime end) {
        final LocalTime current = timeService.getLocalTime();
        return start.isBefore(current) && end.isAfter(current);
    }

    private SecuritySystemDTO saveSecuritySystemDTO(final SecuritySystem securitySystem) {
        return mvcConversionService.convert(repository.save(securitySystem), SecuritySystemDTO.class);
    }
}

