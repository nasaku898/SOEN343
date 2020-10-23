package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DoorDTO {
    private Long id;
    private Boolean open;
    private Boolean locked;
    private Map<Long, String> rooms;
}