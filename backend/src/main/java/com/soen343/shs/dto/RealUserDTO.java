package com.soen343.shs.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class RealUserDTO extends UserDTO {
    private String email;
    private String firstName;
    private String lastName;
}