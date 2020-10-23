package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.HouseMember;
import com.soen343.shs.dal.repository.HouseMemberRepository;
import com.soen343.shs.dto.HouseMemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import static com.soen343.shs.dal.service.helpers.HouseMemberHelper.getHouseMemberDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HouseMemberServiceTest {

    @Mock
    private HouseMemberRepository houseMemberRepository;

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private RoomService roomService;
    
    @InjectMocks
    private HouseMemberService classUnderTest;

    @Test
    public void testCreateNewHouseMember() {
        final HouseMemberDTO dto = getHouseMemberDTO();

        when(houseMemberRepository.save(any(HouseMember.class))).thenReturn(HouseMember.builder().build());
        final HouseMemberDTO dtoTest = classUnderTest.createNewHouseMember(dto);

        Assertions.assertEquals(dto, dtoTest);

    }

}
