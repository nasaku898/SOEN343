package com.soen343.shs.dal.service.Login;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
