package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.repository.mapping.HouseMemberMapper;
import com.soen343.shs.dto.HouseMemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.buildMockHouseMember;
import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.getHouseMemberDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HouseMemberServiceTest {

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HouseMemberMapper mapper;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private HouseMemberService classUnderTest;

    @Test
    public void testCreateNewHouseMember() {
        final HouseMemberDTO dto = getHouseMemberDTO();
        final HouseMember value = buildMockHouseMember();

        when(userRepository.save(any(HouseMember.class))).thenReturn(value);
        when(mvcConversionService.convert(value, HouseMemberDTO.class)).thenReturn(dto);
        final HouseMemberDTO dtoTest = classUnderTest.createNewHouseMember(dto);

        Assertions.assertEquals(dto, dtoTest);
    }

}
