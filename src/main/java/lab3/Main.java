package lab3;

import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String id = "54737";
        //new API().createAccount(id);
        int x0 = new API().play(id, 1, 1860214296);
        int x1 = new API().play(id, 1, 1860214296);
        int x2 = new API().play(id, 1, 1860214296);
        /*
          x1 = a * x0 + c (mod 2^32);
          x2 = a * x1 + c (mod 2^32);
        */
        long x01 = x0 - x1;
        long x12 = x1 - x2;
        BigInteger subA = new BigInteger(String.valueOf(modInverse(x01,
                Lcg.m))).multiply(new BigInteger(String.valueOf(x12)))
                .mod(new BigInteger(String.valueOf(Lcg.m)));
        BigInteger subC = new BigInteger(String.valueOf(x1)).subtract(new BigInteger(String.valueOf(x0))
                .multiply(new BigInteger(String.valueOf(subA))))
                .mod(new BigInteger(String.valueOf(Lcg.m)));
        long a = subA.longValue();
        long c = subC.longValue();
        System.out.println(modInverse(x01,
                Lcg.m));
        System.out.println(subA);
        System.out.println(a);
        System.out.println(subC);
        System.out.println(c);
        for (int i = 0; i < 2; i++) {
            int next = new Lcg(x2, a, c).getNext();
            int trueValue = new API().play(id, 996, next);
            x2 = next;
        }
    }

    static long modInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;


        if (m == 1)
            return 0;

        while (Math.abs(a) > 1) {
            long q = a / m;
            long t = m;

            //Java returns not the modulus but the remainder,
            //which is bad for negative a, and there're plenty
            //of negative a in total.
            //This thing resolves the issue
            m = (((a % m) + m) % m);
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
