package com.soen343.shs.dto;

import com.soen343.shs.dal.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@SuperBuilder
public class UserDTO {
    private long id;
    private String username;
    private Map<Long, String> roomId;
    private UserRole role;
}