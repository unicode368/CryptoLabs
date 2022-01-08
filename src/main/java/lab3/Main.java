package lab3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //new API().createAccount("63467");
        new API().play("63467", 1, 251157735);
        System.out.println();
        //{"money":1000,"deletionTime":"2022-01-08T14:03:50.9633993Z","id":"63467"}
        //{"realNumber":-2029718102,"message":"You lost this time","account":{"money":999,
        //        "deletionTime":"2022-01-08T14:03:50.9633993Z","id":"63467"}}
        /*long a = (-677747287 + 998743310) * (modInverse((long) -998743310 - 1748853791,
                (long) Math.pow(2, 32))) % (long) Math.pow(2, 32);
        System.out.println(a);*/
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
