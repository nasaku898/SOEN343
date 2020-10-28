package com.soen343.shs.dal.service.Login;

import com.soen343.shs.dto.RealUserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LoginResponse {
    private String token;
    private RealUserDTO user;
}