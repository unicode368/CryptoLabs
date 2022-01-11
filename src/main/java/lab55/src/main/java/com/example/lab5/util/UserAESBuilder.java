package com.example.lab5.util;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserAESBuilder {
    String surname;
    String name;
    String patronimic;
    String email;
    String phoneNumber;
    String salt1;
    String salt2;
    String salt3;
    String salt4;
    String salt5;
    String vectorIV;

    UserAESBuilder(String surname, String name, String patronimic,
                   String email, String phoneNumber) throws InvalidKeySpecException,
            NoSuchAlgorithmException {
        this.salt1 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt2 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt3 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt4 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt5 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.vectorIV = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.surname = AES.encrypt(AES.ALGORITHM, surname,
                AES.getKeyFromPassword(AES.getPassword(PasswordType.SURNAME),
                        salt1), );
    }
}
