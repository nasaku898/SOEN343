package com.soen343.shs.DTO;

import java.util.Set;
import com.soen343.shs.dal.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HouseDTO {
    private Set<Room> rooms;
    private double temperatureOutside;
}
