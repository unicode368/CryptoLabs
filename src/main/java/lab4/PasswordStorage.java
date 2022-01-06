package lab4;

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
                    password = lines.skip(passwordNum).findFirst().get();
                }
                return password;
            case TOP100: break;
            case REALLY_RANDOM: break;
            case MY_PASSWORD: break;
        }
        return "";
    }
}
