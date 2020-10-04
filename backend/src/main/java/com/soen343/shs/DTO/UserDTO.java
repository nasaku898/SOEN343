package com.soen343.shs.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private long roomId;
}
