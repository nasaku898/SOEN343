package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.interfaces.observer.Subject;
import com.soen343.shs.interfaces.observer.Subscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService implements Subject {

    private final RoomRepository roomRepository;
    private final ConversionService mvcConversionService;
    private final List<Subscriber> subscribers;

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

    /**
     * @param roomId id of the room to be updated
     * @param userId id of the user to be added to the collection of user ids in the room
     * @return DTO object showing the updated state of the room
     */
    RoomDTO addUserToRoom(final long roomId, final long userId) {
        final Room room = fetchRoom(roomId);
        room.getUserIds().add(userId);
        notifySubscribers(room.getHouseId());
        return mvcConversionService.convert(roomRepository.save(room), RoomDTO.class);
    }

    public double getTemperatureOfRoom(final long roomId) {
        return fetchRoom(roomId).getTemperature();
    }

    Room fetchRoom(final long id) {
        return roomRepository.findById(id).orElseThrow(() -> new SHSNotFoundException(getRoomErrorMessage(id)));
    }

    @Override
    public void addObserver(final Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeObserver(final Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(final long houseId) {
        subscribers.forEach(s -> s.update(houseId));
    }
}
