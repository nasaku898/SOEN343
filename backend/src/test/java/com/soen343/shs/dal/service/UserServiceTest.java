package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.repository.mapping.RealUserMapper;
import com.soen343.shs.dto.RealUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static com.soen343.shs.dal.service.helpers.UserTestHelper.createUser;
import static com.soen343.shs.dal.service.helpers.UserTestHelper.createUserDTO;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RealUserMapper mapper;

    @InjectMocks
    private UserService classUnderTest;

    @Test
    void testUpdate() {
        final RealUserDTO dto = createUserDTO();
        final RealUser user = createUser();

        when(userRepository.findById(RealUser.class, user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(mapper.mapRealUserDTOToRealUser(dto, user))).thenReturn(user);
        when(mvcConversionService.convert(user, RealUserDTO.class)).thenReturn(dto);

        final RealUserDTO real = classUnderTest.updateUser(dto);
        Assertions.assertEquals(dto.getFirstName(), real.getFirstName());
    }

}
