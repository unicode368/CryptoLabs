package lab3;

import org.apache.commons.math3.random.MersenneTwister;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String id = "6536635";
        new API().createAccount(id);
        crackLcg(id);
        crackMt(id);
    }

    public static void crackLcg(String id) throws IOException, InterruptedException {
        int x0 = (int) new API().play(id, "Lcg",1, 1860214296)
                .getRealNumber();
        int x1 = (int) new API().play(id, "Lcg",1, 1860214296)
                .getRealNumber();
        int x2 = (int) new API().play(id, "Lcg",1, 1860214296)
                .getRealNumber();
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
        for (int i = 0; i < 2; i++) {
            int next = new Lcg(x2, a, c).getNext();
            int trueValue = (int) new API().play(id, "Lcg",996, next)
                    .getRealNumber();
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

    public static void crackMt(String id) throws IOException, InterruptedException {
        long res = new API().play(id, "Mt",1, 3254542)
                .getRealNumber();
        //long timeStamp = new API().play(id, "Mt",1, 3254542)
        //        .getDeletionTime();
        long timeStamp = Instant.now().getEpochSecond();
        for (int i = -10; i <= 10; i++) {
            MersenneTwister mt = new MersenneTwister(timeStamp + i);
            int aaaaaaaaa = mt.nextInt();
                //new API().play(id, "Mt",1, getUnsignedInt(aaaaaaaaa));
                //System.out.println(getUnsignedInt(aaaaaaaaa));
               // System.out.println(".");
            if (aaaaaaaaa == res) {
                System.out.println("it works");
            }
        }
        //System.out.println(Timestamp.valueOf("2022-01-16 01:26:09.5096951")
        //        .getTime());
       //System.out.println(Instant.now().getEpochSecond());

        //System.out.println("{ 1: " + aaaaaaaaa);
        // System.out.println("2:" + getUnsignedInt(aaaaaaaaa) + " }");
       // System.out.println(Instant.now().getEpochSecond());
    }

    private static long getUnsignedInt(int x) {
        return x & 0x00000000ffffffffL;
    }

}
