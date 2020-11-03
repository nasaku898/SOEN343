package com.soen343.shs.dal.service;

import com.google.common.collect.Sets;
import com.soen343.shs.dal.model.*;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dto.HouseDTO;
import com.soen343.shs.dto.LoadHouseDTO;
import com.soen343.shs.dto.LoadRoomDTO;
import com.soen343.shs.dto.RealUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoadSimulationService {

    private final HouseRepository houseRepository;
    private final ConversionService mvcConversionService;
    private final UserService userService;
    private final CityService cityService;

    /**
     * @param loadHouseDTO data transfer object representing the house layout
     * @return HouseDTO object reflecting the changes made to the object
     */
    public HouseDTO loadHouse(final LoadHouseDTO loadHouseDTO, final String username) {
        final RealUserDTO owner = userService.getUserByUsername(username, RealUserDTO.class);

        final House house = House.builder()
                .rooms(loadRooms(loadHouseDTO.getRooms()))
                .city(cityService.getCity(loadHouseDTO.getCity()).getName())
                .parents(Sets.newHashSet(owner.getId()))
                .children(Collections.emptySet())
                .guests(Collections.emptySet())
                .build();

        final Long HOUSE_ID = house.getId();
        house.getRooms().forEach(room -> room.setHouseId(HOUSE_ID));
        owner.getHouseIds().add(HOUSE_ID);
        
        userService.updateUser(owner);
        return mvcConversionService.convert(houseRepository.save(house), HouseDTO.class);
    }

    /**
     * @param rooms list of DTO reflecting rooms
     * @return list of rooms
     */
    private Set<Room> loadRooms(final Set<LoadRoomDTO> rooms) {
        return rooms.stream()
                .map(room -> Room.builder()
                        .doors(loadData(room.getDoors(), Door.class))
                        .houseWindows(loadData(room.getHouseWindows(), HouseWindow.class))
                        .lights(loadData(room.getLights(), Light.class))
                        .name(room.getName())
                        .build())
                .collect(Collectors.toSet());
    }

    /**
     * @param dtoSet    set of dto
     * @param classType the class type
     * @param <DTO>     generic of dto
     * @param <Entity>  the entity
     * @return set of entity
     */
    private <DTO, Entity> Set<Entity> loadData(final Set<DTO> dtoSet, final Class<Entity> classType) {
        return dtoSet.stream()
                .map(dto -> mvcConversionService.convert(dto, classType))
                .collect(Collectors.toSet());
    }
}
