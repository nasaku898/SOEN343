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
    private String name;
    private Set<Long> doorIds;
    private Set<Long> windowIds;
    private Set<Long> lightIds;
    private double temperature;
    private Set<Long> userIds;
}
