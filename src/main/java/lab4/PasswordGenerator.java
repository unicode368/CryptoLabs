package lab4;

import java.util.Random;

public class PasswordGenerator {
    final double top1MpasswordProbability
            = (double) (50 + new Random().nextInt(41)) / 100;
    final double top100passwordProbability
            = (double) (5 + new Random().nextInt(6)) / 100;
    final double reallyRandomPasswordProbability
            = (double) (1 + new Random().nextInt(5)) / 100;

    public String generate() {
        double randomNumber = new Random().nextDouble();
        if (randomNumber <= reallyRandomPasswordProbability) {
            return PasswordStorage.getPassword(
                    PasswordStorage.StorageType.REALLY_RANDOM);
        } else if (randomNumber <= top100passwordProbability) {
            return PasswordStorage.getPassword(
                    PasswordStorage.StorageType.TOP100);
        } else if (randomNumber <= top1MpasswordProbability) {
            return PasswordStorage.getPassword(
                    PasswordStorage.StorageType.TOP1M);
        } else {
            return PasswordStorage.getPassword(
                    PasswordStorage.StorageType.MY_PASSWORD);
        }
    }

    public String genReallyRandomPassword() {
        StringBuilder password = new StringBuilder();
        int randomLength = 8 + new Random().nextInt(13);
        return "";
    }
}
