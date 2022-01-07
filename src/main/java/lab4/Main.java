package lab4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("weakHash2.csv"));
        for (int i = 0; i < 100000; i++) {
            writer.write(encrypt(new PasswordGenerator().generate(),
                               "SHA-1") + "\n");
        }

        writer.close();
    }

    public static String encrypt(String password, String algorithmName)
            throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithmName);
        if (algorithmName.equals("SHA-1")) {
            SecureRandom random = new SecureRandom();
            byte salt[] = new byte[20];
            random.nextBytes(salt);
            digest.reset();
            digest.update(salt);
        }
        String hash = new BigInteger(1,
                digest.digest(password.getBytes())).toString(16);
        while (hash.length() < 32 ){
            hash = "0" + hash;
        }
        return hash;
    }
}
