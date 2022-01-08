package lab3;

import java.math.BigInteger;

public class Lcg {
    public static final long m = (long) Math.pow(2, 32);
    long a;
    long c;
    int last;

    Lcg(int current, long a, long c) {
        this.a = a;
        this.c = c;
        last = current;
    }

    public int getNext() {
        return (int) new BigInteger(String.valueOf(a))
                .multiply(new BigInteger(String.valueOf(last)))
                .add(new BigInteger(String.valueOf(c)))
                .mod(new BigInteger(String.valueOf(m)))
                .longValue();
    }
}
