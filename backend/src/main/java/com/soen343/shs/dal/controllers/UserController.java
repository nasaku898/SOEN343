package com.soen343.shs.dal.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.soen343.shs.dal.service.LoginResponse;
import com.soen343.shs.dal.service.UserService;
import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    UserDTO addNewUser(final HttpServletRequest request, @RequestBody final ObjectNode objectNode) {
        return userService.createUser(
                new RegistrationDTO(
                        objectNode.get("email").asText(),
                        objectNode.get("username").asText(),
                        objectNode.get("firstName").asText(),
                        objectNode.get("lastName").asText(),
                        objectNode.get("password").asText(),
                        objectNode.get("matchingPassword").asText()
                )
        );
    }

    @PostMapping(path = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    LoginResponse loginUser(final HttpServletRequest request, @RequestBody final ObjectNode objectNode) {
        return userService
                .login(
                        request,
                        objectNode.get("username").asText(),
                        objectNode.get("password").asText()
                );
    }goit
}
