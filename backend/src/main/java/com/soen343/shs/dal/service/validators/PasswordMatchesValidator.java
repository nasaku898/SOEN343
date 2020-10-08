package com.soen343.shs.dal.service.validators;

import com.soen343.shs.dto.RegistrationDTO;
import com.soen343.shs.dal.service.validators.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RegistrationDTO registrationDTO = (RegistrationDTO) o;
        return registrationDTO.getPassword().equals(registrationDTO.getMatchingPassword());
    }

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
}
