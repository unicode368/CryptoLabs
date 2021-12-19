package lab1;

import java.util.HashMap;

public class ThirdPart {
    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int populationSize = 0;
    private int topPopulation = 0;
    private int stabilityIntervals = 0;
    private int crossoverCount = 0;
    private int mutationsCount = 0;

    public String process(String encryptedText) {
        char[] chars = encryptedText.toCharArray();
        HashMap<Character, Character> decipherPairs = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = decipherPairs.get(chars[i]);
        }
        return String.valueOf(chars);
    }
}
