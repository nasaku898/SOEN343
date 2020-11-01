package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.SecuritySystem;
import com.soen343.shs.dal.repository.RoomRepository;
import com.soen343.shs.dal.repository.SecuritySystemRepository;
import com.soen343.shs.dto.SecuritySystemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecuritySystemServiceTest {
    @Mock
    private SecuritySystemRepository repository;

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private HouseService houseService;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private SecuritySystemService classUnderTest;

    @Test
    void createSecuritySystem() {
        final SecuritySystemDTO dto = buildSecuritySystemDTO();

        when(repository.save(any(SecuritySystem.class))).thenReturn(getEntity());
        when(mvcConversionService.convert(any(SecuritySystem.class), any())).thenReturn(buildSecuritySystemDTO());
        final SecuritySystemDTO system = classUnderTest.createSecuritySystem(dto);
        Assertions.assertNotNull(dto);
    }

    private static SecuritySystem getEntity() {
        return SecuritySystem.builder()
                .auto(false)
                .away(false)
                .houseId(1L)
                .rooms(Collections.emptySet())
                .id(1L)
                .build();
    }

    private static SecuritySystemDTO buildSecuritySystemDTO() {
        return SecuritySystemDTO.builder()
                .auto(false)
                .away(false)
                .id(1L)
                .rooms(Collections.emptySet())
                .houseId(1L)
                .build();
    }

}
