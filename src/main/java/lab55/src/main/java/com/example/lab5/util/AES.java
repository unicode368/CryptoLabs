package com.example.lab5.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class AES {
    final static String ALGORITHM = "AES/CBC/PKCS5Padding";
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

    public static IvParameterSpec getVector(String vectorString) {
        byte[] vectorBytes = Arrays.copyOfRange(vectorString
                .getBytes(StandardCharsets.UTF_8), 0, 16);
        return new IvParameterSpec(vectorBytes);
    }

    public static byte[] genRandomBytes() {
        byte[] rand = new byte[16];
        new SecureRandom().nextBytes(rand);
        return rand;
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    //in case if application contains pages which require this encrypted info
    public static String decrypt(String algorithm, String cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
