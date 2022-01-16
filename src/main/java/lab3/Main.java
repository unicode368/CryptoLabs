package lab3;

import org.apache.commons.math3.random.MersenneTwister;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String id = "65366391";
        new API().createAccount(id);
        //crackLcg(id);
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
        ResultDTO res = new API().play(id, "Mt",1, 3254542);
        long money = res.getMoney();
        int timeStamp = (int) Instant.now().getEpochSecond();
        for (int i = -5000; i <= 5000; i++) {
            MersenneTwister mt = new MersenneTwister(timeStamp + i);
            for (int j = 0; j < 624; j++) {
                int aaaaaaaaa = mt.nextInt();
                //{"realNumber":317117367,"message":"Yay! It's different from
                // the first one: https://docs.google.com/document/d/1_W00GZXLNTk6BM
                // L6jEaAJDqwMVjaQUv5WL1DCW7ipy4/edit - шоста лаба","account":{"money":41792083,
                // "deletionTime":"2022-01-16T13:38:54.7809208Z","id":"6536635"}}
                if (getUnsignedInt(aaaaaaaaa) == res.getRealNumber()) {
                    System.out.println("i = " + i + ", j = " + j);
                    while (money < 1_000_000) {
                        res = new API().play(id, "Mt",100, getUnsignedInt(mt.nextInt()));
                        money += res.getMoney();
                    }

                }
            }
        }
    }

    private static long getUnsignedInt(int x) {
        return x & 0x00000000ffffffffL;
    }

}
