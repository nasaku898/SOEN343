package com.soen343.shs.DTO;

import com.soen343.shs.dal.service.Annotations.PasswordMatches;
import com.soen343.shs.dal.service.Annotations.ValidEmail;
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
}