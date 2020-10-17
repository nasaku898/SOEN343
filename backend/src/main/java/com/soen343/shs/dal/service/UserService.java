package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dal.service.exceptions.user.SHSUserAlreadyExistsException;
import com.soen343.shs.dal.service.exceptions.user.UserIdDoesntExist;
import com.soen343.shs.dal.service.validators.FieldValidator;
import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dto.UserDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class UserService {
    private final ConversionService mvcConversionService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authProvider;

    public UserService(final ConversionService mvcConversionService,
                       final UserRepository userRepository,
                       final PasswordEncoder passwordEncoder,
                       final AuthenticationProvider authProvider) {
        this.mvcConversionService = mvcConversionService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authProvider = authProvider;
    }

    /**
     * @param details registrationDTO object containing registration fields
     * @return UserDTO object containing the state of the object upon registration
     */
    @Transactional
    public UserDTO createUser(final RegistrationDTO details) {

        // check to see if username/email exists already, if so throw exception
        if (userRepository.findByEmail(details.getEmail()).isPresent() || userRepository.findByUsername(details.getUsername()).isPresent()) {
            throw new SHSUserAlreadyExistsException("Username/email already exists!");
        }

        FieldValidator.validateRegistration(details); // Validate the rest of the fields;

        final User user = mvcConversionService.convert(details, User.class); // Convert to our model

        if (user == null) {
            throw new NullPointerException();
        }

        user.setPassword(passwordEncoder.encode(details.getPassword())); // encode password

        userRepository.save(user); // save to DB

        return mvcConversionService.convert(details, UserDTO.class); // return the dto object to our user
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
                .user(getUserByUsername(loginRequest.getUsername()))
                .build();
    }

    public UserDTO updateUser(final UserDTO userDTO) {
        userRepository.save(mvcConversionService.convert(userDTO, User.class));
        return userDTO;
    }

    public UserDTO getUserByUsername(final String username) {
        final User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username: %s doesn't exist", username))
        );

        return mvcConversionService.convert(user, UserDTO.class);
    }

    private UserDTO getUserById(final Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new UserIdDoesntExist(String.format("UserId: %d doesn't exist", id)));
        return mvcConversionService.convert(user, UserDTO.class);
    }
}