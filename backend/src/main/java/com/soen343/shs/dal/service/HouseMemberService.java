package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.model.UserRole;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.repository.mapping.HouseMemberMapper;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
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

    private final UserRepository userRepository;
    private final ConversionService mvcConversionService;
    private final RoomService roomService;
    private final HouseMemberMapper mapper;

    /**
     * @param houseMemberDTO data transfer object that reflects the changes made to the object
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO createNewHouseMember(final HouseMemberDTO houseMemberDTO) {
        final HouseMember houseMember = userRepository.save(HouseMember.builder()
                .username(houseMemberDTO.getUsername())
                .location((roomService.fetchRoom((houseMemberDTO.getLocation().getRoomId()))))
                .role(UserRole.valueOf(houseMemberDTO.getRole().name()))
                .isOutside(houseMemberDTO.isOutside())
                .houseIds(houseMemberDTO.getHouseIds())
                .build());

        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    public HouseMemberDTO updateHouseMember(final HouseMemberDTO houseMemberDTO) {
        final HouseMember member = userRepository.save(mapper.mapUserDTOToUser(houseMemberDTO, findHouseMember(houseMemberDTO.getId())));
        return houseMemberDTO;
    }

    /**
     * @param houseMemberId id of house member
     * @return HouseMemberDTO object reflecting the changes made to the object
     */
    public HouseMemberDTO removeHouseMember(final long houseMemberId) {
        final HouseMember houseMember = findHouseMember(houseMemberId);
        userRepository.delete(houseMember);
        return mvcConversionService.convert(houseMember, HouseMemberDTO.class);
    }

    /**
     * @return List of house member dto
     */
    public List<HouseMemberDTO> findAllHouseMembers(final Long houseId) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user instanceof HouseMember)
                .filter(houseMember -> houseMember.getHouseIds().contains(houseId))
                .map(houseMember -> mvcConversionService.convert(houseMember, HouseMemberDTO.class))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * @param houseMemberId house member id
     * @return a room House member
     */
    private HouseMember findHouseMember(final long houseMemberId) {
        return userRepository.findById(HouseMember.class, houseMemberId)
                .orElseThrow(() -> new SHSNotFoundException(String.format("House member with id: %d was not found", houseMemberId)));
    }
}
