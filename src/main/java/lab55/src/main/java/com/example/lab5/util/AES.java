package com.example.lab5.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.objenesis.SpringObjenesis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AES {
    public static String getPassword(PasswordType passwordType) {
        ArrayList<String> passwords = processFile("resources/my_config.txt");
        switch (passwordType) {
            case NAME:
                break;
            case LOGIN:
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
}
