package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.HouseMemberService;
import com.soen343.shs.dto.HouseMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/simulation")
public class HouseMemberController {

    private final HouseMemberService houseMemberService;

    @PostMapping(value = "/houseMember")
    @ResponseStatus(value = HttpStatus.CREATED)
    public HouseMemberDTO createNewHouseMember(@RequestBody final HouseMemberDTO houseMemberDTO) {
        return houseMemberService.createNewHouseMember(houseMemberDTO);
    }

    @GetMapping(value = "/houseMember/all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<HouseMemberDTO> findAllHouseMembers() {
        return houseMemberService.findAllHouseMembers();
    }
    
}
