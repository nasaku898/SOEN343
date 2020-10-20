package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dal.service.UserService;
import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SimulationService simulationService;


    @PostMapping(path = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    UserDTO addNewUser(final HttpServletRequest request, @RequestBody final RegistrationDTO registrationDTO) {
        return userService.createUser(registrationDTO);
    }

    @PostMapping(path = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    LoginResponse loginUser(final HttpServletRequest request, @RequestBody final LoginRequest loginRequest) {
        return userService
                .login(request, loginRequest);
    }

    @PutMapping(path = "/user/{username}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    UserDTO updateUserLocation(@AuthenticationPrincipal final Authentication authentication, final UserDTO user) {
        return userService.updateUser(user);
    }

    @GetMapping(value = "/user/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public UserDTO getUser(@RequestParam final String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping(value = "user/{username}/room/{roomId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public UserDTO moveUserToRoom(@RequestParam final String username, @PathVariable final long roomId) {
        return simulationService.moveUserToRoom(username, roomId);
    }
}
