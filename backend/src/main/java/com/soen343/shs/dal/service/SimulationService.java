package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.HouseRepository;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.service.exceptions.house.HouseNotFoundException;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final HouseRepository houseRepository;
    private final ConversionService mvcConversionService;
    private final UserRepository userRepository;
    private final RoomService roomService;

    /**
     * @param houseId a house id
     * @return house object
     */
    public House findHouse(final long houseId) {
        return houseRepository.findById(houseId).orElseThrow(HouseNotFoundException::new);
    }


    /**
     * @param username username of user
     * @param roomId   a room id to transfer to
     * @return UserDTO object reflecting the changes made to the object
     */

    public <DTO, Entity extends User> DTO moveUserToRoom(final String username,
                                                         final long roomId,
                                                         final Class<Entity> entityClass,
                                                         final Class<DTO> classType) {
        final User user = userRepository.findByUsername(entityClass, username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s doesnt exist", username)));

        if (user.getIsOutside()) {
            user.setIsOutside(false);
        }

        user.setLocation(roomService.fetchRoom(roomId));


        return mvcConversionService.convert(userRepository.save(user), classType);
    }

    /**
     * @param houseId a house id
     * @return list of RoomDTO
     */
    public List<RoomDTO> findAllRooms(final Long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> getShsNotFoundException(houseId))
                .getRooms()
                .stream()
                .map(room -> mvcConversionService.convert(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    public Set<Room> fetchRoomsState(final long houseId) {
        return houseRepository.findById(houseId).orElseThrow(() -> getShsNotFoundException(houseId)).getRooms();
    }

    private static SHSNotFoundException getShsNotFoundException(final long houseId) {
        return new SHSNotFoundException(String.format("House with id: %d doesn't exist", houseId));
    }
}
