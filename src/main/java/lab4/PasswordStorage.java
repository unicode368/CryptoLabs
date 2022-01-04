package lab4;

public class PasswordStorage {
    enum StorageType {
        TOP1M,
        TOP100,
        REALLY_RANDOM,
        MY_PASSWORD
    }

    public String getPassword(StorageType storageType) {
        switch (storageType) {
            case TOP1M: break;
            case TOP100: break;
            case REALLY_RANDOM: break;
            case MY_PASSWORD: break;
        }
        return "";
    }
}
