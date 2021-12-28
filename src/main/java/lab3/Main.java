package lab3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        new API().createAccount("63467");
        //{"message":"You lost this time","account":
        // {"id":"63467","money":999,"deletionTime":"2021-12-27T23:36:59.341588Z"},"realNumber":186041942}
        //{"message":"You lost this time","account":{"id":"63467","money":998,
        // "deletionTime":"2021-12-27T23:36:59.341588Z"},"realNumber":1040402877}
        //{"message":"You lost this time","account":{"id":"63467","money":997,
        // "deletionTime":"2021-12-27T23:36:59.341588Z"},"realNumber":-445644808}
    }
}
