package com.example.lab5.dto;

import com.example.lab5.validation.PasswordMatches;
import com.example.lab5.validation.ValidEmail;
import com.example.lab5.validation.ValidLogin;
import com.example.lab5.validation.ValidMobilePhone;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@PasswordMatches
public class UserDTO {

    @NotNull
    @NotEmpty
    @ValidLogin
    private String login;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String patronimic;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidMobilePhone
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;
}
