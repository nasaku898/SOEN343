package com.soen343.shs.dal.service.validators;

import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dal.service.exceptions.InvalidFieldException;
import org.springframework.stereotype.Component;

@Component("SHSFieldValidator")
public class FieldValidator {

    public void validateRegistration(RegistrationDTO dto) throws InvalidFieldException {
        validateField(dto.getFirstName(), "first name");
        validateField(dto.getLastName(), "last name");
        validateField(dto.getUsername(), "username");
    }

    private void validateField(String s, String field) throws InvalidFieldException {
        if (s.isEmpty()) {
            String errorMsg = "Invalid field: " + field + " cannot be empty";
            throw new InvalidFieldException(errorMsg);
        }
    }
}
