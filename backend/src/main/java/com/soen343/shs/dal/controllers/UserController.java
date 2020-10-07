package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.UserService;
import com.soen343.shs.dal.service.exceptions.InvalidFieldException;
import com.soen343.shs.dal.service.exceptions.SHSUserAlreadyExistsException;
import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(path="api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    UserDTO addNewUser (HttpServletRequest request, @NotNull String username, @NotNull String email, @NotNull String firstName,
                        @NotNull String lastName, @NotNull String password, @NotNull String matchingPassword) {

        try {
            return userService.createUser(new RegistrationDTO(email, username, firstName, lastName, password, matchingPassword));
        } catch (SHSUserAlreadyExistsException | InvalidFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(path="/login")
    public @ResponseBody UserDTO loginUser(HttpServletRequest request, @NotNull String email, @NotNull String password) {
        return userService.login(request, email, password);
    }
}
