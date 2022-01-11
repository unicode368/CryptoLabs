package com.example.lab5.util;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
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

    public UserAESBuilder(String surname, String name, String patronimic,
                          String email, String phoneNumber) throws InvalidKeySpecException,
            NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        this.salt1 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt2 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt3 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt4 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.salt5 = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.vectorIV = new String(AES.genRandomBytes(), StandardCharsets.UTF_8);
        this.surname = AES.encrypt(AES.ALGORITHM, surname,
                AES.getKeyFromPassword(AES.getPassword(PasswordType.SURNAME),
                        salt1), AES.getVector(vectorIV));
        this.name = AES.encrypt(AES.ALGORITHM, name,
                AES.getKeyFromPassword(AES.getPassword(PasswordType.NAME),
                        salt2), AES.getVector(vectorIV));
        this.patronimic = AES.encrypt(AES.ALGORITHM, patronimic,
                AES.getKeyFromPassword(AES.getPassword(PasswordType.PATRONIMIC),
                        salt3), AES.getVector(vectorIV));
        this.email = AES.encrypt(AES.ALGORITHM, email,
                AES.getKeyFromPassword(AES.getPassword(PasswordType.EMAIL),
                        salt4), AES.getVector(vectorIV));
        this.phoneNumber = AES.encrypt(AES.ALGORITHM, phoneNumber,
                AES.getKeyFromPassword(AES.getPassword(PasswordType.PHONE_NUMBER),
                        salt5), AES.getVector(vectorIV));
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronimic() {
        return patronimic;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSalt1() {
        return salt1;
    }

    public String getSalt2() {
        return salt2;
    }

    public String getSalt3() {
        return salt3;
    }

    public String getSalt4() {
        return salt4;
    }

    public String getSalt5() {
        return salt5;
    }

    public String getVectorIV() {
        return vectorIV;
    }
}
