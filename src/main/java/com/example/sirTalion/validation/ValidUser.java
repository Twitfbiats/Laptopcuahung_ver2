package com.example.sirTalion.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UserValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidUser
{
    String message() default "";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}

