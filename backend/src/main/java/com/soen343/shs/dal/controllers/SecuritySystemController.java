package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.SecuritySystemService;
import com.soen343.shs.dto.SecuritySystemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/security")
public class SecuritySystemController {
    private final SecuritySystemService securityService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public SecuritySystemDTO getRoom(@PathVariable final long id) {
        return securityService.getSHSSecurity(id);
    }

    @PutMapping(value = "/{id}/away")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public SecuritySystemDTO setAwayMode(@PathVariable final long id, @RequestBody final boolean desiredState) {
        SecuritySystemDTO securitySystemDTO = securityService.toggleAway(desiredState, id);
        securityService.notifySHH(id);
        return securitySystemDTO;
    }

    @PostMapping(value = "/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SecuritySystemDTO createSecuritySystem(@RequestBody final SecuritySystemDTO securitySystemDTO) {
        return securityService.createSecuritySystem(securitySystemDTO);
    }
}
