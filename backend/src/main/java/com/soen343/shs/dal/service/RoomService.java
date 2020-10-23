package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final ConversionService mvcConversionService;

    public RoomDTO getRoom(final long id) {
        return mvcConversionService.convert(fetchRoom(id), RoomDTO.class);
    }

    private static String getRoomErrorMessage(final long id) {
        return String.format("Room with id: %d was not found", id);
    }

    /**
     * @param id             a room id
     * @param newTemperature new temperature
     * @return RoomDTO object reflecting the changes made to the object
     */
    public RoomDTO setTemperatureOfRoom(final long id, final double newTemperature) {
        final Room room = fetchRoom(id);
        room.setTemperature(newTemperature);
        return mvcConversionService.convert(roomRepository.save(room), RoomDTO.class);
    }

    Room addUserToRoom(final Room room, final long userId) {
        room.getUserIds().add(userId);
        return roomRepository.save(room);
    }

    public double getTemperatureOfRoom(final long roomId) {
        return fetchRoom(roomId).getTemperature();
    }

    Room fetchRoom(final long id) {
        return roomRepository.findById(id).orElseThrow(() -> new SHSNotFoundException(getRoomErrorMessage(id)));
    }
}
