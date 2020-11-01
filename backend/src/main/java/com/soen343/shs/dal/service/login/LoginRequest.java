package com.soen343.shs.dal.service.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
