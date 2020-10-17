package com.soen343.shs.dal.service.validators;

import com.soen343.shs.dal.service.validators.exceptions.InvalidFieldException;
import com.soen343.shs.dto.RegistrationDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component("SHSFieldValidator")
public final class FieldValidator {

    private static final String FIRST_NAME = "first name";
    private static final String LAST_NAME = "last name";
    private static final String USERNAME = "USERNAME";
    private static final String ROLE = "ROLE";

    public static void validateRegistration(final RegistrationDTO dto) {
        validateField(dto.getFirstName(), FIRST_NAME);
        validateField(dto.getLastName(), LAST_NAME);
        validateField(dto.getUsername(), USERNAME);
        validateField(dto.getRole(), ROLE);
    }

    private static void validateField(final String s, final String field) {
        if (s.isEmpty()) {
            final String errorMsg = String.format("Invalid field: %s cannot be empty", field);
            throw new InvalidFieldException(errorMsg);
        }
    }
}