package com.soen343.shs.DTO;

import com.soen343.shs.dal.model.Room;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private String username;
    private long roomId;
}
