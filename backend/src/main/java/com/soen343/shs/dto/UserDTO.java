package com.soen343.shs.dto;

import com.soen343.shs.dal.model.UserRole;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
public class UserDTO {
    private long id;
    private String username;
    private Map<Long, String> roomId;
    private UserRole role;
}