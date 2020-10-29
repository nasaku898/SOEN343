package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.Login.LoginRequest;
import com.soen343.shs.dal.service.Login.LoginResponse;
import com.soen343.shs.dal.service.UserService;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.RealUserDTO;
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


    @PostMapping(path = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    RealUserDTO addNewUser(final HttpServletRequest request, @RequestBody final RegistrationDTO registrationDTO) {
        return userService.createUser(registrationDTO);
    }

    @PostMapping(path = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    LoginResponse loginUser(final HttpServletRequest request, @RequestBody final LoginRequest loginRequest) {
        return userService.login(request, loginRequest);
    }

    @PutMapping(path = "/user/update}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    UserDTO updateUserLocation(@AuthenticationPrincipal @RequestBody final Authentication authentication, final RealUserDTO user) {
        return userService.updateUser(user.getId(), user);
    }

    @GetMapping(value = "/user/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public RealUserDTO getUser(@PathVariable final String username) {
        return userService.getUserByUsername(username, RealUserDTO.class);
    }

    @PutMapping(path = "/user/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    UserDTO updateHouseMemberLocation(@AuthenticationPrincipal @RequestBody final Authentication authentication, final HouseMemberDTO houseMember) {
        return userService.updateUser(houseMember.getId(), houseMember);
    }


    @GetMapping(value = "/logout")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String logoutUser() {
        return "USER SUCCESSFULLY LOGGED OUT AND CLOSED THE SIMULATION";
    }

    @GetMapping(value = "/user")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public RealUserDTO getAuthenticatedUser(@AuthenticationPrincipal final Authentication authentication) {
        return userService.getUserByUsername(authentication.getName(), RealUserDTO.class);
    }

}
