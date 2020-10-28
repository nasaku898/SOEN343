package com.soen343.shs.dal.service;

import static com.soen343.shs.dal.service.UserServiceTest.buildLoginRequest;
import static com.soen343.shs.dal.service.UserServiceTest.buildRegistrationDTO;
import static com.soen343.shs.dal.service.helpers.UserTestHelper.USERNAME;
import static com.soen343.shs.dal.service.helpers.UserTestHelper.createUser;
import static com.soen343.shs.dal.service.helpers.UserTestHelper.createUserDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dal.service.exceptions.user.SHSUserAlreadyExistsException;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RegistrationDTO;
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

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private ConversionService mvcConversionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationProvider authProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService classUnderTest;


    private static final RegistrationDTO REGISTRATION_DTO = buildRegistrationDTO();
    private static final String ENCODED_PASSWORD = "encodedString";
    private static final String REGISTRATION_ERR_MSG = "Username/email already exists!";

    @Test
    void testCreateUser_givenUserDoesntAlreadyExist() {
        when(mvcConversionService.convert(REGISTRATION_DTO, RealUser.class)).thenReturn(createUser());
        when(passwordEncoder.encode(REGISTRATION_DTO.getPassword())).thenReturn(ENCODED_PASSWORD);
        when(mvcConversionService.convert(userRepository.save(createUser()), RealUserDTO.class)).thenReturn(RealUserDTO.builder().build());

        final RealUserDTO dto = classUnderTest.createUser(REGISTRATION_DTO);
        Assertions.assertNotNull(dto);
    }

    @Test
    void testCreateUser_givenUserAlreadyExists() {
        when(userRepository.findByUsername(RealUser.class, REGISTRATION_DTO.getUsername())).thenReturn(Optional.of(createUser()));

        final SHSUserAlreadyExistsException exception = Assertions.assertThrows(SHSUserAlreadyExistsException.class, () -> {
                                                                                    classUnderTest.createUser(REGISTRATION_DTO);
                                                                                }
        );

        Assertions.assertEquals(REGISTRATION_ERR_MSG, exception.getMessage());
    }

    @Test
    void testLogin_givenValidCredentials() {
        final LoginRequest loginRequest = buildLoginRequest();
        final MockHttpServletRequest request = new MockHttpServletRequest();

        final RealUser user = createUser();

        when(userRepository.findByUsername(RealUser.class, USERNAME)).thenReturn(Optional.ofNullable(user));
        when(mvcConversionService.convert(user, RealUserDTO.class)).thenReturn(createUserDTO());

        final HttpSession session = request.getSession(true);
        final LoginResponse response = classUnderTest.login(request, loginRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(createUserDTO(), response.getUser());
        Assertions.assertEquals(Objects.requireNonNull(session).getId(), response.getToken());
    }

    @Test
    void testLogin_givenInvalidCredentials() {
        final LoginRequest loginRequest = buildLoginRequest();
        final MockHttpServletRequest request = new MockHttpServletRequest();

        when(authProvider.authenticate(any())).thenThrow(BadCredentialsException.class);

        Assertions.assertThrows(BadCredentialsException.class, () -> {
                                    classUnderTest.login(request, loginRequest);
                                }
        );
    }

}
