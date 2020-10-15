package com.soen343.shs.dal.service;

import com.soen343.shs.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LoginResponse {
    private String token;
    private UserDTO user;
}
