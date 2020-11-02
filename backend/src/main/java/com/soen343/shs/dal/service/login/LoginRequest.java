package com.soen343.shs.dal.service.login;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
