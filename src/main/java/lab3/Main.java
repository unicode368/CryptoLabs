package lab3;

import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //new API().createAccount("63467");
        int x0 = new API().play("63467", 1, 1860214296);
        int x1 = new API().play("63467", 1, 1860214296);
        int x2 = new API().play("63467", 1, 1860214296);
        /*
          x1 = a * x0 + c (mod 2^32);
          x2 = a * x1 + c (mod 2^32);
        */
        long x01 = x0 - x1;
        long x12 = x1 - x2;
        long a = new BigInteger(String.valueOf(modInverse(x01,
                Lcg.m))).multiply(new BigInteger(String.valueOf(x12)))
                .mod(new BigInteger(String.valueOf(Lcg.m)))
                .longValue();
        long c = -2065691657L + 193188616L * a;
        System.out.println(a);
        System.out.println(c);
        int next = new Lcg(x2, a, c).getNext();
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
