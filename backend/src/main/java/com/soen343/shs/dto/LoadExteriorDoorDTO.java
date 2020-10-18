package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class LoadExteriorDoorDTO extends LoadDoorDTO {
    private boolean locked;
}