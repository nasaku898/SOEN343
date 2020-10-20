package com.soen343.shs.dal.service;

import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService {
    private RoomRepository roomRepository;
    private ConversionService mvcConverionService;

    public RoomDTO getRoom(final long id) {
        return mvcConverionService.convert(roomRepository.findById(id).orElseThrow(() -> new SHSNotFoundException(String.format("Room with id: %d was not found", id))), RoomDTO.class);
    }
}
