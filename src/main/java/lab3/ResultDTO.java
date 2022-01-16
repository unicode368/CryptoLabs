package lab3;

public class ResultDTO {
    private long realNumber;
    private long money;
    private long deletionTime;

    ResultDTO(long realNumber, long money, long deletionTime) {
        this.realNumber = realNumber;
        this.money = money;
        this.deletionTime = deletionTime;
    }

    public long getRealNumber() {
        return realNumber;
    }

    public long getMoney() {
        return money;
    }

    public long getDeletionTime() {
        return deletionTime;
    }
}
