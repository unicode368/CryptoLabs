package com.example.lab5.service;

import com.example.lab5.dto.UserDTO;
import com.example.lab5.entity.User;
import com.example.lab5.entity.UserInfo;
import com.example.lab5.exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(@RequestBody UserDTO request,
                         String roleName) throws UserAlreadyExistsException {
        return userService.signUpUser(new User(
                request.getLogin(),
                        bCryptPasswordEncoder.encode(request.getPassword())),
                new UserInfo(request.getLastName(),
                        request.getFirstName(), request.getPatronimic(),
                        request.getPhoneNumber(), request.getEmail()), roleName);
    }
}
