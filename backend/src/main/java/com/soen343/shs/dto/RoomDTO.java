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
public class RoomDTO {
    private long roomId;
    private String name;
    private Set<DoorDTO> doors;
    private Set<WindowDTO> windows;
    private Set<LightDTO> lights;
    private double temperature;
    private Set<Long> userIds;
}