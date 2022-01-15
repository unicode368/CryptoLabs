package lab3;

public class Mt {
    final int N = 624;
    //0, 2w - 1
    //For a w-bit word length, the Mersenne Twister
    // generates integers in the range [0, 2w - 1]. => unsigned integer
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
    final int F = 1812433253;

    long[] MT = new long[N];

    int lowerMask;
    int upperMask;
    int index;

    public Mt(long seed) {
        index = N;
        lowerMask = (1 << R) - 1;
        upperMask = (1 << W) - (1 << R);
        setSeed(seed);
    }

    private void setSeed(long seed) {
        MT[0] = seed;
        for (int i = 1; i < N; i++) { // loop over each element
            MT[i] = ((F * (MT[i-1] ^ (MT[i-1] >> (W-2))) + i) << W) >> W;
        }
    }



}
