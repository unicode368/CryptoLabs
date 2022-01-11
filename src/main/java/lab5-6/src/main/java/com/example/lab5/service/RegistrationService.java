package com.example.lab5.service;

import com.example.lab5.dto.UserDTO;
import com.example.lab5.entity.User;
import com.example.lab5.entity.UserInfo;
import com.example.lab5.exceptions.UserAlreadyExistsException;
import com.example.lab5.util.UserAESBuilder;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(@RequestBody UserDTO request,
                         String roleName) throws UserAlreadyExistsException {
        try {
            UserAESBuilder encryptedData = new UserAESBuilder(request.getLastName(),
                    request.getFirstName(), request.getPatronimic(),
                    request.getEmail(), request.getPhoneNumber());
            return userService.signUpUser(new User(
                            request.getLogin(),
                            bCryptPasswordEncoder.encode(request.getPassword()),
                            encryptedData.getVectorIV()),
                    new UserInfo(encryptedData.getSurname(),
                            encryptedData.getName(), encryptedData.getPatronimic(),
                            encryptedData.getPhoneNumber(), encryptedData.getEmail(),
                            encryptedData.getSalt1(), encryptedData.getSalt2(),
                            encryptedData.getSalt3(), encryptedData.getSalt4(),
                            encryptedData.getSalt5()), roleName);
        } catch (InvalidKeySpecException |
                NoSuchAlgorithmException | IllegalBlockSizeException
                | InvalidKeyException | BadPaddingException | InvalidAlgorithmParameterException
                | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return new User();
    }
}
