package com.soen343.shs.DTO;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HouseDTO {
    private long id;
    private Set<RoomDTO> rooms;
    private double temperatureOutside;
}
