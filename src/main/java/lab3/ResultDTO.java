package lab3;

public class ResultDTO {
    long realNumber;
    long money;
    long deletionTime;

    ResultDTO(long realNumber, long money, long deletionTime) {
        this.realNumber = realNumber;
        this.money = money;
        this.deletionTime = deletionTime;
    }
}
