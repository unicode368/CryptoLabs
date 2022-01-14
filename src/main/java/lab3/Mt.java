package lab3;

public class Mt {
    final int N = 624;
    final int W = 32;
    final int M = 397;
    final int R = 31;
    final int A = 0x9908B0DF;
    final int U = 11;
    final int D = 0xFFFFFFFF;
    final int S = 7;
    final int B = 0x9D2C5680;
    final int T = 15;
    final int C = 0xEFC60000;
    final int L = 18;

    long[] MT = new long[N];

    int lowerMask;
    int upperMask;
    int index;
}
