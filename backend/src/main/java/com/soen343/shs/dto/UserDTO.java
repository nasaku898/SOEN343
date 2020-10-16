package com.soen343.shs.dto;

import com.soen343.shs.dal.model.UserRole;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class UserDTO {
    private final long id;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final long roomId;
    private final UserRole role;
}