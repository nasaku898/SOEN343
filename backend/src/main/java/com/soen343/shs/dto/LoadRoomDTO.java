package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadRoomDTO {
    private String name;
    private Set<LoadDoorDTO> doors;
    private Set<LoadLightDTO> lights;
    private Set<LoadHouseWindowDTO> houseWindows;
}
