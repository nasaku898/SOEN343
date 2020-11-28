package com.soen343.shs.dto;

import com.soen343.shs.dal.model.ZoneState;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ZoneDTO {
    private long id;
    private Set<RoomDTO> rooms;

    private double temperature;

    private ZoneState zoneState;
}
