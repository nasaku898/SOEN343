package com.soen343.shs.dto;

import com.soen343.shs.dal.service.validators.annotations.PasswordMatches;
import com.soen343.shs.dal.service.validators.annotations.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@PasswordMatches
@ValidEmail
public class RegistrationDTO {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String role;
}