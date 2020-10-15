package com.soen343.shs.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoadHouseDTO {
    Set<LoadRoomDTO> rooms;
}
