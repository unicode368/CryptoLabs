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

    final String[] firstPart = {"Albedo", "Amber", "Barbara", "Beidou", "Bennett",
               "Childe", "Diluc", "Diona", "Eula", "Fischl", "Ganyu", "HuTao", "Jean",
               "Kazuha", "Kokomi", "Klee", "Lisa", "Mona", "Ningguang", "Qiqi",
               "Raiden", "Razor", "Rosaria", "Thoma", "Venti", "Xiangling", "Xiao",
               "Xinyan", "Yoimia", "Kaeya", "Zhongli", "Shenhe", "YunJin", "Gorou",
               "Itto"};

    public static String getPassword(StorageType storageType) {
        String password;
        switch (storageType) {
            case TOP1M:
                int passwordNum = 1 + new Random().nextInt(1000000);
                try (Stream<String> lines = Files.lines(Paths
                        .get("10-million-password-list-top-1000000.txt"))) {
                    password = lines.skip(passwordNum - 1).findFirst().get();
                    password = password.substring(0, password.length() - 1);
                    return password;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case TOP100:
                try {
                    passwordNum = 1 + new Random().nextInt(100);
                    password = Files.readAllLines(Paths.get("top100Passwords.txt")).get(passwordNum);
                    password = password.substring(0, password.length() - 1);
                    return password;
                } catch (IOException e) {
                    System.exit(1);
                }
            case REALLY_RANDOM: return genReallyRandomPassword();
            case MY_PASSWORD: break;
        }
        return "";
    }

    public static String genReallyRandomPassword() {
        StringBuilder password = new StringBuilder();
        int randomLength = 8 + new Random().nextInt(13);
        for (int i = 0; i < randomLength; i++) {
            password.append(Character.valueOf(
                    (char) (33 + new Random().nextInt(94))));
        }
        return password.toString();
    }


}
