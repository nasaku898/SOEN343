package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.HouseMemberService;
import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/simulation")
public class HouseMemberController {

    private final HouseMemberService houseMemberService;
    private final SimulationService simulationService;

    @PostMapping(value = "/houseMember")
    @ResponseStatus(value = HttpStatus.CREATED)
    public HouseMemberDTO createNewHouseMember(@RequestBody final HouseMemberDTO houseMemberDTO) {
        return simulationService.createNewHouseMember(houseMemberDTO);
    }

    @GetMapping(value = "/house/{houseId}/houseMember/all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<HouseMemberDTO> findAllHouseMembers(@PathVariable final Long houseId) {
        return houseMemberService.findAllHouseMembers(houseId);
    }


    @PutMapping(path = "/houseMember/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody
    UserDTO updateHouseMemberLocation(@AuthenticationPrincipal final Authentication authentication, @RequestBody final HouseMemberDTO houseMember) {
        return houseMemberService.updateHouseMember(houseMember);
    }

}
