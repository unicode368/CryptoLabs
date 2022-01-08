package lab3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //new API().createAccount("63467");
        new API().play("63467", 1, 251157735);
        //System.out.println();
        //{"money":1000,"deletionTime":"2022-01-08T15:08:05.8044726Z","id":"63467"}
        //{"realNumber":90393952,"message":"You lost this time","account":{"money":999,
        // "deletionTime":"2022-01-08T15:08:05.8044726Z","id":"63467"}}
        //{"realNumber":-1582423745,"message":"You lost this time","account":{"money":998,
        // "deletionTime":"2022-01-08T15:08:05.8044726Z","id":"63467"}}
        //{"realNumber":313310610,"message":"You lost this time","account":
        // {"money":997,"deletionTime":"2022-01-08T15:08:05.8044726Z","id":"63467"}}
        //System.out.println(a);
        /*System.out.println(kInt);
        System.out.println(k);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);*/
        /*System.out.println(x0);
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(a1);
        System.out.println(a2);*/
    }

    static long modInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            // q is quotient
            long q = a / m;

            long t = m;

            // m is remainder now, process
            // same as Euclid's algo
            m = a % m;
            a = t;
            t = y;

            // Update x and y
            y = x - q * y;
            x = t;
        }

        // Make x positive
        if (x < 0)
            x += m0;

        return x;
    }



}
