package com.soen343.shs.dal.service.validators;

import com.soen343.shs.dal.service.validators.exceptions.InvalidFieldException;
import com.soen343.shs.dto.RegistrationDTO;
import org.springframework.stereotype.Component;

@Component("SHSFieldValidator")
public class FieldValidator {

    public void validateRegistration(final RegistrationDTO dto) throws InvalidFieldException {
        validateField(dto.getFirstName(), "first name");
        validateField(dto.getLastName(), "last name");
        validateField(dto.getUsername(), "username");
    }

    private void validateField(final String s, final String field) throws InvalidFieldException {
        if (s.isEmpty()) {
            final String errorMsg = "Invalid field: " + field + " cannot be empty";
            throw new InvalidFieldException(errorMsg);
        }
    }
}