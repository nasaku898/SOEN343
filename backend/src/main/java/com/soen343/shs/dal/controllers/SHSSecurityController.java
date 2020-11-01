package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.model.SHSSecurity;
import com.soen343.shs.dal.service.SHSSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/security")
public class SHSSecurityController {
    private final SHSSecurityService securityService;

    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public SHSSecurity getRoom(@PathVariable final long id) {
        return securityService.getSHSSecurity(id);
    }

}
