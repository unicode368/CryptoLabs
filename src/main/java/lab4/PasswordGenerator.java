package lab4;

import java.util.Random;

public class PasswordGenerator {
    final double top1MpasswordProbability
            = (50 + new Random().nextInt(41)) / 100;
    final double top100passwordProbability
            = (5 + new Random().nextInt(6)) / 100;
}
