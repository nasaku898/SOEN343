package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dto.HouseMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseMemberService {

    private final HouseMemberRepository houseMemberRepository;
    private final RoomRepository roomRepository;
    private final ConversionService mvcConversionService;
    private final ModelFetchHandler modelFetchHandler;


    public HouseMemberDTO createNewHouseMember(final HouseMemberDTO houseMemberDTO) {
        return mvcConversionService.convert(houseMemberRepository.save(
                HouseMember.builder()
                        .name(houseMemberDTO.getName())
                        .location(modelFetchHandler.findRoom(houseMemberDTO.getRoomId()))
                        .build())
                , HouseMemberDTO.class);
    }

    public HouseMemberDTO editName(final long houseMemberId, final String newName) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setName(newName);

        return mvcConversionService.convert(houseMemberRepository.save(houseMember), HouseMemberDTO.class);
    }

    public HouseMemberDTO editRole(final long houseMemberId, final String newRole) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setRole(UserRole.valueOf(newRole));

        return mvcConversionService.convert(houseMemberRepository.save(houseMember), HouseMemberDTO.class);
    }

    public HouseMemberDTO editLocation(final long houseMemberId, final long newRoomId) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setLocation(modelFetchHandler.findRoom(newRoomId));

        return mvcConversionService.convert(houseMemberRepository.save(houseMember), HouseMemberDTO.class);
    }

    public HouseMemberDTO removeHouseMember(final long houseMemberId) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMemberRepository.delete(houseMember);
        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    public List<HouseMemberDTO> findAllHouseMembers() {
        return houseMemberRepository.findAll()
                .stream().map(houseMember -> mvcConversionService.convert(houseMember, HouseMemberDTO.class))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
