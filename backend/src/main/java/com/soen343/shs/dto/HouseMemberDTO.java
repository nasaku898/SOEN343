package com.soen343.shs.dto;

import com.soen343.shs.dal.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class HouseMemberDTO {
    private String name;
    private long roomId;
    private long id;
    private String role;
    private String roomName;
}
