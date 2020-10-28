package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.repository.mapping.SHSUserMapper;
import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dal.service.exceptions.user.SHSUserAlreadyExistsException;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

import static com.soen343.shs.dal.model.UserRole.PARENT;
import static com.soen343.shs.dal.service.helpers.UserTestHelper.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SHSUserMapper mapper;

    @Mock
    private AuthenticationProvider authProvider;

    @InjectMocks
    private UserService classUnderTest;

    @Test
    void testUpdate() {
        final RealUserDTO dto = createUserDTO();
        final RealUser user = createUser();

        when(userRepository.findById(User.class, user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(mapper.updateUserFromDTO(dto, user))).thenReturn(user);

        final UserDTO real = classUnderTest.updateUser(USER_ID, dto);
        Assertions.assertTrue(real instanceof RealUserDTO);
        Assertions.assertEquals(dto.getFirstName(), ((RealUserDTO) real).getFirstName());
    }

    static RegistrationDTO buildRegistrationDTO() {
        return RegistrationDTO.builder()

                .username(USERNAME)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .password(PASSWORD)
                .matchingPassword(PASSWORD)
                .role(PARENT.name())
                .build();
    }

    static LoginRequest buildLoginRequest() {
        return LoginRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }
}
