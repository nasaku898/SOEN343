package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.Room;
import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dto.HouseMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class HouseMemberService {

    private final HouseMemberRepository houseMemberRepository;
    private final RoomRepository roomRepository;
    private final ConversionService mvcConversionService;
    private final ModelFetchHandler modelFetchHandler;

    @Autowired
    public HouseMemberService(final HouseMemberRepository houseMemberRepository, final RoomRepository roomRepository, final ConversionService mvcConversionService, final ModelFetchHandler modelFetchHandler) {
        this.houseMemberRepository = houseMemberRepository;
        this.roomRepository = roomRepository;
        this.mvcConversionService = mvcConversionService;
        this.modelFetchHandler = modelFetchHandler;
    }

    public HouseMemberDTO createNewHouseMember(final HouseMemberDTO houseMemberDTO) {
        final Room room = modelFetchHandler.findRoom(houseMemberDTO.getRoomId());
        final HouseMember houseMember = new HouseMember();

        houseMember.setName(houseMemberDTO.getName());
        houseMember.setLocation(room);
        houseMember.setRole(UserRole.valueOf(houseMemberDTO.getRole()));
        houseMemberRepository.save(houseMember);

        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    public HouseMemberDTO editName(final long houseMemberId, final String newName) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setName(newName);
        houseMemberRepository.save(houseMember);
        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    public HouseMemberDTO editRole(final long houseMemberId, final String newRole) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setRole(UserRole.valueOf(newRole));
        houseMemberRepository.save(houseMember);
        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    public HouseMemberDTO editLocation(final long houseMemberId, final long newRoomId) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        final Room room = modelFetchHandler.findRoom(newRoomId);
        houseMember.setLocation(room);
        houseMemberRepository.save(houseMember);
        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    public HouseMemberDTO removeHouseMember(final long houseMemberId) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMemberRepository.delete(houseMember);
        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    public List<HouseMemberDTO> findAllHouseMember() {
        List<HouseMember> houseMembers = houseMemberRepository.findAll();

        List<HouseMemberDTO> houseMemberDTOS = new LinkedList<HouseMemberDTO>();
        for (HouseMember houseMember : houseMembers) {
            houseMemberDTOS.add(mvcConversionService.convert(houseMember, HouseMemberDTO.class));
        }

        return houseMemberDTOS;
    }
}
