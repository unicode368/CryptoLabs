package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
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

    static final String[] firstPart = {"Albedo", "Amber", "Barbara", "Beidou", "Bennett",
               "Childe", "Diluc", "Diona", "Eula", "Fischl", "Ganyu", "HuTao", "Jean",
               "Kazuha", "Kokomi", "Klee", "Lisa", "Mona", "Ningguang", "Qiqi",
               "Raiden", "Razor", "Rosaria", "Thoma", "Venti", "Xiangling", "Xiao",
               "Xinyan", "Yoimia", "Kaeya", "Zhongli", "Shenhe", "YunJin", "Gorou",
               "Itto"};

    static final String[] secondPart = {"Hairy", "Smelly", "Big", "Humongus", "Gigantic",
            "Hard", "Veiny", "Long", "Girthy", "Sexy", "Glorious", "Monster", "Sussy",
            "Purple", "Swollen", "Funny", "Soggy", "Old", "Massive", "Sticky",
            "Dangerous", "Tiny", "Racist", "Obese", "Sexist", "French"};

    static final String[] thirdPart = {"Cocogoat", "Vases", "Claymore", "Traveler", "Artifacts",
            "Family", "Polearm", "Сaliphate", "Shaft", "Ukraine", "Bow", "Crate", "Xbox",
            "Warcrimes", "Dad", "Nuclearwarhead", "Catalyst", "Wand", "Twitteraccount", "Mosque",
            "Primogems", "KlaptykZemliTaDvaRaba", "Bom", "Meat", "Gun", "Chest"};

    public static String getPassword(StorageType storageType) {
        String password;
        switch (storageType) {
            case TOP1M:
                int passwordNum = 1 + new Random().nextInt(1000000);
                try (BufferedReader br = new BufferedReader(
                        new FileReader("10-million-password-list-top-1000000.txt"))) {
                    for (int i = 0; i < passwordNum; i++)
                        br.readLine();
                    password = br.readLine();
                    return password;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case TOP100:
                passwordNum = 1 + new Random().nextInt(100);
                try {
                    password = Files.readAllLines(Paths
                            .get("top100Passwords.txt")).get(passwordNum - 1);
                    password = password.substring(0, password.length() - 1);
                    return password;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case REALLY_RANDOM: return genReallyRandomPassword();
            case MY_PASSWORD: return genHumanLikePassword();
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

    public static String genHumanLikePassword() {
        String first = firstPart[new Random().nextInt(firstPart.length)];
        String second = secondPart[new Random().nextInt(secondPart.length)];
        String third = thirdPart[new Random().nextInt(thirdPart.length)];
        String password = first + second + third;
        if (new Random().nextBoolean()) {
            if (password.contains("o")) {
                password =password.replaceAll("o", "0");
            }
        }
        if (new Random().nextBoolean()) {
            if (password.contains("l")) {
                password = password.replaceAll("l", "1");
            }
        }
        return password;
    }


}
