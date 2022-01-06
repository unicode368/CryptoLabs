package lab4;


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

            case TOP100: break;
            case REALLY_RANDOM: break;
            case MY_PASSWORD: break;
        }
        return "";
    }
}
