package com.example.lab5.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.objenesis.SpringObjenesis;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

public class AES {
    public static String getPassword(PasswordType passwordType) {
        ArrayList<String> passwords = processFile("resources/my_config.txt");
        switch (passwordType) {
            case NAME:
                return passwords.stream()
                        .filter(line -> line.contains("name_password"))
                        .findAny().get()
                        .split("=")[1];
            case SURNAME:
                return passwords.stream()
                        .filter(line -> line.contains("surname_password"))
                        .findAny().get()
                        .split("=")[1];
            case PATRONIMIC:
                return passwords.stream()
                        .filter(line -> line.contains("patronimic_password"))
                        .findAny().get()
                        .split("=")[1];
            case EMAIL:
                return passwords.stream()
                        .filter(line -> line.contains("email_password"))
                        .findAny().get()
                        .split("=")[1];
            case PHONE_NUMBER:
                return passwords.stream()
                        .filter(line -> line.contains("phone_number_password"))
                        .findAny().get()
                        .split("=")[1];
        }
        return "";
    }

    public static ArrayList<String> processFile(String location) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(location),
                    Charset.defaultCharset());
            return (ArrayList<String>) lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        
    }

    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        return "";
    }
}
