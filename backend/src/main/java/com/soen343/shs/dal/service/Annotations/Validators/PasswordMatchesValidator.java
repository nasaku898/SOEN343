package com.soen343.shs.dal.service.Annotations.Validators;

import com.soen343.shs.DTO.RegistrationDTO;
import com.soen343.shs.dal.service.Annotations.PasswordMatches;

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
