package lab4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class PasswordStorage {
    enum StorageType {
        TOP1M,
        TOP100,
        REALLY_RANDOM,
        MY_PASSWORD
    }

    public static String getPassword(StorageType storageType) {
        String password;
        switch (storageType) {
            case TOP1M:
                int passwordNum = 1 + new Random().nextInt(1000000);
                try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
                    password = lines.skip(passwordNum - 1).findFirst().get();
                    password = password.substring(0, password.length() - 1);
                    return password;
                } catch (IOException e) {
                    System.exit(1);
                }
            case TOP100:
                try {
                    passwordNum = 1 + new Random().nextInt(100);
                    password = Files.readAllLines(Paths.get("file.txt")).get(passwordNum);
                    return password;
                } catch (IOException e) {
                    System.exit(1);
                }
            case REALLY_RANDOM: break;
            case MY_PASSWORD: break;
        }
        return "";
    }
}
