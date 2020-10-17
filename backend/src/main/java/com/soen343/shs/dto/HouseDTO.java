package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HouseDTO {
    private long id;
    private Set<Long> roomIds;
    private double temperatureOutside;
}
