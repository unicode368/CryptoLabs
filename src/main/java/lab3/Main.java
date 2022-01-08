package lab3;

import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //new API().createAccount("63467");
        new API().play("63467", 1, 1860214296);
        //x0: {"realNumber":-193188616,"message":"You lost this time","account":{"money":999,
        // "deletionTime":"2022-01-08T20:49:08.9668497Z","id":"63467"}}
        //x1: {"realNumber":-2065691657,"message":"You lost this time","account":{"money":998,
        // "deletionTime":"2022-01-08T20:49:08.9668497Z","id":"63467"}}
        //x2: {"realNumber":1806891242,"message":"You lost this time","account":{"money":997,
        // "deletionTime":"2022-01-08T20:49:08.9668497Z","id":"63467"}}
        /*
          x1 = a * x0 + c (mod 2^32);
          x2 = a * x1 + c (mod 2^32);
        */
        long x01 = -193188616L + 2065691657L;
        long x12 = -2065691657L - 1806891242L;
        BigInteger a = new BigInteger("0");
       // long a =  (inverseA * a2) % (long) Math.pow(2, 32);
        //long c = 313310610 - 1582423745 * -851249410;
        //System.out.println(a543);
        //System.out.println(c);
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
            long q = a / m;
            long t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += m0;

        return x;
    }



}
