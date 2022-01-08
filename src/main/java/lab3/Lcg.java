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
    
}
