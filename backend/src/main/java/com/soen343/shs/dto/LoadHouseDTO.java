package com.soen343.shs.dto;

import com.soen343.shs.dal.model.City;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class LoadHouseDTO {
    private Set<LoadRoomDTO> rooms;
    private City city;
}
