package com.example.sirTalion.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO 
{
    String id;

    @NotEmpty(message = "Email must not be blank")
    @Email(message = "Email format must be correct")
    private String email;

    @Length(min = 8, max = 32, message = "password length must be between 8-32 character")
    private String password;

    private String confirmPassword;

    @NotEmpty(message = "Address must not be null")
    private String address;

    @NotEmpty(message = "Fullname must not be null")
    private String fullName;

    @NotEmpty(message = "Phonenumber must not be null")
    private String phoneNumber;

    private String role;
}
