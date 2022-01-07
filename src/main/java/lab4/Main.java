package lab4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("strongHash.csv"));
        for (int i = 0; i < 100000; i++) {
            writer.write(encryptBcrypt(new PasswordGenerator().generate()) + "\n");
        }

        writer.close();
    }

    public static String encrypt(String password, String algorithmName)
            throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithmName);
        byte salt[] = new byte[20];
        if (algorithmName.equals("SHA-1")) {
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            digest.reset();
            digest.update(salt);
        }
        String hash = new BigInteger(1,
                digest.digest(password.getBytes())).toString(16);
        while (hash.length() < 32 ){
            hash = "0" + hash;
        }
        if (algorithmName.equals("SHA-1")) {
            return hash + "," + new String(salt, StandardCharsets.UTF_8);
        }
        return hash;
    }

    public static String encryptBcrypt(String password) {
        
    }
}
