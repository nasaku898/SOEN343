package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.SimulationService;
import com.soen343.shs.dal.service.TimeService;
import com.soen343.shs.dto.HouseMemberDTO;
import com.soen343.shs.dto.RealUserDTO;
import com.soen343.shs.dto.RoomDTO;
import com.soen343.shs.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/simulation")
public class SimulationController {

    private final SimulationService simulationService;
    private final TimeService timeService;

    @PutMapping(value = "/user/{username}/room/{roomId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public UserDTO moveUserToRoom(@NotNull @PathVariable final String username, @PathVariable final long roomId) {
        return simulationService.moveUserToRoom(username, roomId, RealUserDTO.class);
    }

    // This should be made...less dumb
    @PutMapping(value = "/houseMember/{username}/room/{roomId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public UserDTO moveHouseMemberToRoom(@NotNull @PathVariable final String username, @PathVariable final long roomId) {
        return simulationService.moveUserToRoom(username, roomId, HouseMemberDTO.class);
    }

    @GetMapping(value = "/house/{houseId}/room/all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Set<RoomDTO> findAllRoom(@PathVariable final long houseId) {
        return simulationService.findAllRooms(houseId);
    }

    @GetMapping(value = "/time")
    public long getCurrentDate() {
        return timeService.getCurrentDateInMilliseconds();
    }

    @PutMapping(value = "/time/{dateInMilliseconds}")
    public long setNewDate(@PathVariable final long dateInMilliseconds) {
        return timeService.setNewDate(dateInMilliseconds);
    }
}
