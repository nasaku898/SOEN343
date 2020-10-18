package com.soen343.shs.dal.controllers;

import com.soen343.shs.dal.service.HouseService;
import com.soen343.shs.dto.WindowDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/house")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }


}
