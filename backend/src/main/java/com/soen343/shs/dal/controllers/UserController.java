package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.UserService;
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
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    UserDTO addNewUser(final HttpServletRequest request,
                       @NotNull final String username,
                       @NotNull final String email,
                       @NotNull final String firstName,
                       @NotNull final String lastName,
                       @NotNull final String password,
                       @NotNull final String matchingPassword) {

        return userService.createUser(
                new RegistrationDTO(
                        email,
                        username,
                        firstName,
                        lastName,
                        password,
                        matchingPassword
                )
        );
    }

    @PostMapping(path = "/login")
    public @ResponseBody
    UserDTO loginUser(final HttpServletRequest request,
                      @NotNull final String email,
                      @NotNull final String password) {

        return userService.login(request, email, password);
    }
}
