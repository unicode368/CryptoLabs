package lab4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("weakHash.csv"));
        for (int i = 0; i < 100000; i++) {
            writer.write(md5Hash(new PasswordGenerator().generate()) + "\n");
        }

        writer.close();
    }

    public static String md5Hash(String password) throws NoSuchAlgorithmException {
        String hash = new BigInteger(1, MessageDigest.getInstance("MD5")
                .digest((new PasswordGenerator().generate()
                        + "\n").getBytes())).toString(16);
        while (hash.length() < 32 ){
            hash = "0" + hash;
        }
        return hash;
    }
}
