package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.ExteriorDoor;
import com.soen343.shs.dal.model.SecuritySystem;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.repository.SecuritySystemRepository;
import com.soen343.shs.dal.service.exceptions.IllegalStateException;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.SecuritySystemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SHSSecurityService {

    private final SecuritySystemRepository repository;
    private final ConversionService mvcConversionService;
    private final HouseService houseService;
    private final RoomRepository roomRepository;

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
                .orElseThrow(() -> new SHSNotFoundException(String.format("Security service with id: %d was not found ", id)));
    }

    /**
     * @param dto containing the security system we want to create
     * @return a DTO showing the properties of the newly created security system
     */
    public SecuritySystemDTO createSecurityService(final SecuritySystemDTO dto) {
        repository.save(SecuritySystem.builder()
                .auto(dto.isAuto())
                .houseId(dto.getHouseId())
                .away(dto.isAway())
                .rooms((houseService.fetchHouse(dto.getHouseId()).getRooms()))
                .build());

        return dto;
    }

    /**
     * @param desiredState the desired state of the away mode setting
     * @param id           id of the security system
     * @return SHSSecurityDTO showing the state of the security system after the new update
     */
    public SecuritySystemDTO toggleAway(final boolean desiredState, final long id) {

        final SecuritySystem security = getSecuritySystem(id);
        security.setAway(desiredState);

        if (desiredState) {
            security.getRooms().forEach(

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
                                }
                        );
                        room.getHouseWindows().forEach(houseWindow -> houseWindow.setOpen(false));
                        roomRepository.save(room);
                    }
            );
        }
        return mvcConversionService.convert(repository.save(security), SecuritySystemDTO.class);
    }
}
