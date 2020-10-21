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


    /**
     * @param houseMemberDTO data transfer object that reflects the changes made to the object
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO createNewHouseMember(final HouseMemberDTO houseMemberDTO) {
        houseMemberRepository.save(HouseMember.builder()
                .name(houseMemberDTO.getName())
                .location(modelFetchHandler.findRoom(houseMemberDTO.getRoomId()))
                .role(UserRole.valueOf(houseMemberDTO.getRole()))
                .build());
        return houseMemberDTO;
    }

    /**
     * @param houseMemberId id of house member
     * @param newName       new name for house member
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO editName(final long houseMemberId, final String newName) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setName(newName);

        return mvcConversionService.convert(houseMemberRepository.save(houseMember), HouseMemberDTO.class);
    }

    /**
     * @param houseMemberId id of house member
     * @param newRole       new role for house member
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO editRole(final long houseMemberId, final String newRole) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setRole(UserRole.valueOf(newRole));

        return mvcConversionService.convert(houseMemberRepository.save(houseMember), HouseMemberDTO.class);
    }

    /**
     * @param houseMemberId id of house member
     * @param newRoomId     new room id
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO editLocation(final long houseMemberId, final long newRoomId) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMember.setLocation(modelFetchHandler.findRoom(newRoomId));

        return mvcConversionService.convert(houseMemberRepository.save(houseMember), HouseMemberDTO.class);
    }

    /**
     * @param houseMemberId id of house member
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO removeHouseMember(final long houseMemberId) {
        final HouseMember houseMember = modelFetchHandler.findHouseMember(houseMemberId);
        houseMemberRepository.delete(houseMember);
        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    /**
     * @return List of house member dto
     */
    public List<HouseMemberDTO> findAllHouseMembers() {
        return houseMemberRepository.findAll()
                .stream().map(houseMember -> mvcConversionService.convert(houseMember, HouseMemberDTO.class))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
