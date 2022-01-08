package lab3;

import java.math.BigInteger;

public class Lcg {
    static final long m = (long) Math.pow(2, 32);
    int a;
    int c;
    int last;

    Lcg(int current, int a, int c) {
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
