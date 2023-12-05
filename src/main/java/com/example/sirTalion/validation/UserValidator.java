package com.example.sirTalion.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.sirTalion.entities.User;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserValidator implements ConstraintValidator<ValidUser, User>
{
    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) 
    {
        boolean isValid = true;

        if (value.getProvider() == null || value.getProvider().toString().equals(""))
        {
            if (value.getPassword() == null ? true : value.getPassword().equals(""))
            {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("User password can't be blank").addConstraintViolation();
                isValid = false;
            }

            if (value.getConfirmPassword() == null ? true : value.getConfirmPassword().equals(""))
            {
                if (!value.getPassword().contains("$2a$"))
                {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Confirm password can't be blank").addConstraintViolation();
                    isValid = false;
                }
            }

            if (value.getPassword() != null && value.getConfirmPassword() != null)
            {
                if (!value.getPassword().equals(value.getConfirmPassword()))
                {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Confirm password doesn't match").addConstraintViolation();
                    isValid = false;
                }
            }

            if (value.getPhoneNumber() == null ? true : value.getPhoneNumber().equals(""))
            {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("User phone number can't be blank").addConstraintViolation();
                isValid = false;
            }
        }

        return isValid;
    }

}
