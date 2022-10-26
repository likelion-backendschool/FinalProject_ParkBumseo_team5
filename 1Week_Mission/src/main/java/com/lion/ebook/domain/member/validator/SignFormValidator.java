package com.lion.ebook.domain.member.validator;

import com.lion.ebook.domain.member.dto.SignForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignForm signForm = (SignForm) target;

        if (!signForm.getPassword().equals(signForm.getPasswordConfirm())) {
            errors.rejectValue("password", "passwordError", "error : password is not same");
        }
    }
}