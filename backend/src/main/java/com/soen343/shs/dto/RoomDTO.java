package com.soen343.shs.dto;

import com.soen343.shs.dal.model.Window;
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
    private Set<DoorDTO> doors;
    private Set<Window> windows;
    private Set<Long> lightIds;
    private double temperature;
    private Set<Long> userIds;
}
