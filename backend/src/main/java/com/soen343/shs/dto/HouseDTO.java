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
    private Long id;
    private Set<RoomDTO> rooms;
    private String city;
    private Set<Long> parents;
    private Set<Long> children;
    private Set<Long> guests;
}
