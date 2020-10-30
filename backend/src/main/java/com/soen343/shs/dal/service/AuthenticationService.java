package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dal.service.exceptions.user.SHSUserAlreadyExistsException;
import com.soen343.shs.dal.service.validators.FieldValidator;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ConversionService mvcConversionService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authProvider;
    private final UserService userService;

    /**
     * @param details registrationDTO object containing registration fields
     * @return UserDTO object containing the state of the object upon registration
     */
    public RealUserDTO registerUser(final RegistrationDTO details) {
        if (userService.emailExists(details.getEmail()) || userService.usernameExists(details.getUsername())) {
            throw new SHSUserAlreadyExistsException("Username/email already exists!");
        }
        FieldValidator.validateRegistration(details);

        final RealUser user = mvcConversionService.convert(details, RealUser.class);
        Objects.requireNonNull(user).setPassword(passwordEncoder.encode(details.getPassword()));// encode password

        return userService.createUser(user);
    }

    /**
     * @param request      HttpServletRequest object containing request information, so we can retrieve the session attribute
     * @param loginRequest LoginRequest object containing attempted login credentials
     * @return LoginResponse object containing user details, and their authentication token
     */
    public LoginResponse login(final HttpServletRequest request, final LoginRequest loginRequest) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authProvider.authenticate(authenticationToken);

        final SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);

        final HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
        return LoginResponse.builder()
                .token(session.getId()) // return token so we can use for testing in postman
                .user(userService.getUserByUsername(loginRequest.getUsername(), RealUserDTO.class))
                .build();
    }
    

}
