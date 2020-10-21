package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.HouseMemberService;
import com.soen343.shs.dto.HouseMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @PutMapping(value = "/houseMember/nameModification/{houseMemberId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public HouseMemberDTO editHouseMemberName(@PathVariable final long houseMemberId, @RequestParam @NotNull final String newName) {
        return houseMemberService.editName(houseMemberId, newName);
    }

    @PutMapping(value = "/houseMember/roleModification/{houseMemberId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public HouseMemberDTO editHouseMemberRole(@PathVariable final long houseMemberId, @RequestParam @NotNull final String newRole) {
        return houseMemberService.editRole(houseMemberId, newRole);
    }

    @PutMapping(value = "/houseMember/locationModification/{houseMemberId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public HouseMemberDTO editHouseMemberLocation(@PathVariable final long houseMemberId, @RequestParam @NotEmpty final long newRoomId) {
        return houseMemberService.editLocation(houseMemberId, newRoomId);
    }

    @GetMapping(value = "/houseMember/all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<HouseMemberDTO> findAllHouseMember() {
        return houseMemberService.findAllHouseMembers();
    }

}
