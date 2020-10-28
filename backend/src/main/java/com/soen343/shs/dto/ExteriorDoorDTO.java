package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ExteriorDoorDTO extends DoorDTO {
    private Boolean locked;
}