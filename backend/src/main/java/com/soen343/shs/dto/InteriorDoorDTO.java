package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InteriorDoorDTO {
    private long id;
    private boolean open;
}
