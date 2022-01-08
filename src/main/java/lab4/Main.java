package lab4;

import org.springframework.security.crypto.bcrypt.BCrypt;

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
        BufferedWriter writer = new BufferedWriter(new FileWriter("weakHash1.csv"));
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("notStrongHash1.csv"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("strongHash1.csv"));

        for (int i = 0; i < 100000; i++) {
            writer.write(encrypt(new PasswordGenerator().generate(), "MD5") + "\n");
            writer1.write(encrypt(new PasswordGenerator().generate(), "SHA-1") + "\n");
            writer2.write(encryptBcrypt(new PasswordGenerator().generate()) + "\n");
        }

        writer.close();
        writer1.close();
        writer2.close();
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
        while (hash.length() < 32 ) {
            hash = "0" + hash;
        }
        if (algorithmName.equals("SHA-1")) {
            return hash + "," + new String(salt, StandardCharsets.UTF_8);
        }
        return hash;
    }

    public static String encryptBcrypt(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt) + "," + salt;
    }
}
