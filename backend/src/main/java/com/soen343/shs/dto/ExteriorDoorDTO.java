package com.soen343.shs.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class ExteriorDoorDTO extends DoorDTO {
    private Boolean locked;
}