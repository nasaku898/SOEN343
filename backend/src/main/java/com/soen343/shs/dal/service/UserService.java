package com.soen343.shs.dal.service;

import com.soen343.shs.DTO.RegistrationDTO;
import com.soen343.shs.DTO.UserDTO;
import com.soen343.shs.dal.model.User;
import com.soen343.shs.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final ConversionService conversionService;

    private final UserRepository USER_REPOSITORY;

    @Autowired
    public UserService(ConversionService conversionService, UserRepository USER_REPOSITORY) {
        this.conversionService = conversionService;
        this.USER_REPOSITORY = USER_REPOSITORY;
    }

    @Transactional
    public UserDTO createUser(RegistrationDTO details) throws SHSUserAlreadyExists {

        if (USER_REPOSITORY.FindBytEmail(details.getEmail()) != null) {
            throw new SHSUserAlreadyExists("Username/email already exists!");
        }
        USER_REPOSITORY.save(conversionService.convert(details, User.class));
        return conversionService.convert(details, UserDTO.class);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        USER_REPOSITORY.save(conversionService.convert(userDTO, User.class));
        return userDTO;
    }

    public UserDTO getUserByUsername(String username) {
        User user = USER_REPOSITORY.findByUsername(username); //This might not work...test please
        if (user == null) {
            throw new UsernameNotFoundException("Username doesn't exist");
        } else {
            return conversionService.convert(user, UserDTO.class);
        }
    }

    public UserDTO getUserById(Long id) {
        User user = USER_REPOSITORY.findById(id).get();
        if (user == null) {
            throw new UsernameNotFoundException("Username doesn't exist");
        } else {
            return conversionService.convert(user, UserDTO.class);
        }
    }
}
