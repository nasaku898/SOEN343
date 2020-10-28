package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.AuthenticationService;
import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    RealUserDTO addNewUser(final HttpServletRequest request, @RequestBody final RegistrationDTO registrationDTO) {
        return authenticationService.createUser(registrationDTO);
    }

    @PostMapping(path = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    LoginResponse loginUser(final HttpServletRequest request, @RequestBody final LoginRequest loginRequest) {
        return authenticationService.login(request, loginRequest);
    }
}
