package com.soen343.shs.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class RealUserDTO extends UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private boolean isOutside;
}