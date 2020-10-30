package com.soen343.shs.dto;

import com.soen343.shs.dal.model.UserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@SuperBuilder
public class UserDTO {
    private long id;
    private String username;
    private RoomDTO location;
    private UserRole role;
    private boolean isOutside;
    private Set<Long> houseIds;
}