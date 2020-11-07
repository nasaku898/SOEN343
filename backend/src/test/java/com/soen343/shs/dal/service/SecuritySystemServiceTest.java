package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.House;
import com.soen343.shs.dal.model.Room;
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

import java.util.Optional;

import static com.soen343.shs.dal.service.helpers.HouseHelper.HOUSE_ID;
import static com.soen343.shs.dal.service.helpers.RoomHelper.createRooms;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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

    public static final long SECURITY_ID = 1L;

    @Test
    void createSecuritySystemTest() {
        final SecuritySystemDTO dto = buildSecuritySystemDTO();

        when(repository.save(any(SecuritySystem.class))).thenReturn(getEntity());
        when(mvcConversionService.convert(any(SecuritySystem.class), any())).thenReturn(dto);

        final SecuritySystemDTO system = classUnderTest.createSecuritySystem(HOUSE_ID, 30000);
        Assertions.assertEquals(dto, system);
    }

    @Test
    void toggleAwayTest() {
        final SecuritySystem system = mock(SecuritySystem.class);

        when(repository.findById(SECURITY_ID)).thenReturn(Optional.ofNullable(system));
        when(system.getHouseId()).thenReturn(HOUSE_ID);
        when(houseService.fetchHouse(HOUSE_ID)).thenReturn(House.builder().rooms(createRooms()).build());
        when(roomRepository.save(any(Room.class))).thenReturn(Room.builder().build());
        when(repository.save(any(SecuritySystem.class))).thenReturn(SecuritySystem.builder().build());
        when(mvcConversionService.convert(any(), any())).thenReturn(SecuritySystemDTO.builder().away(false).build());

        final SecuritySystemDTO dto = classUnderTest.toggleAway(true, SECURITY_ID);
        Assertions.assertNotEquals(buildSecuritySystemDTO(), dto);
    }


    private static SecuritySystem getEntity() {
        return SecuritySystem.builder()
                .auto(false)
                .away(false)
                .houseId(HOUSE_ID)
                .id(SECURITY_ID)
                .build();
    }

    private static SecuritySystemDTO buildSecuritySystemDTO() {
        return SecuritySystemDTO.builder()
                .auto(false)
                .away(false)
                .id(SECURITY_ID)
                .houseId(1L)
                .build();
    }

}
