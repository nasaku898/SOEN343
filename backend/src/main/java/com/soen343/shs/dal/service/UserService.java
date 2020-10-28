package com.soen343.shs.dal.service;

import com.soen343.shs.dal.model.RealUser;
import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.UserRepository;
import com.soen343.shs.dal.repository.mapping.SHSUserMapper;
import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dal.service.exceptions.state.SHSNotFoundException;
import com.soen343.shs.dal.service.exceptions.user.SHSUserAlreadyExistsException;
import com.soen343.shs.dal.service.validators.FieldValidator;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dto.UserDTO;
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
public class UserService {

    private final ConversionService mvcConversionService;
    private final UserRepository userRepository;
    private final SHSUserMapper userMapper;

    public UserDTO updateUser(final long id, final UserDTO dto) {
        userRepository.save(userMapper.updateUserFromDTO(dto, userRepository.findById(User.class, id)
                .orElseThrow(() -> new SHSNotFoundException(getNotFoundExceptionMessage("id", String.valueOf(id))))
        ));
        return dto;
    }


    /**
     * @param username String value used to fetch from repository by username
     * @return UserDTO corresponding to the unique given username, or throw UsernameNotFoundException
     */
    public <DTO> DTO getUserByUsername(final String username, final Class<DTO> dtoClass) {
        return mvcConversionService.convert(
                userRepository.findByUsername(RealUser.class, username)
                        .orElseThrow(() -> new SHSNotFoundException(getNotFoundExceptionMessage("username", username)))
                , dtoClass);
    }

    private static String getNotFoundExceptionMessage(final String username, final String parameter) {
        return String.format("%s: %s doesn't exist", username, parameter);
    }
}