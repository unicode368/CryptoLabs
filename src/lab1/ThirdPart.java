package lab1;

import java.util.*;
import java.util.stream.Collectors;

public class ThirdPart {
    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int populationSize = 0;
    private int topPopulation = 0;
    private int stabilityIntervals = 0;
    private int crossoverCount = 0;
    private int mutationsCount = 0;

    public String process(String encryptedText) {
        char[] chars = encryptedText.toCharArray();
        HashMap<Character, Character> decipherPairs = getDecipherPairs();
        Set<String> alphabets = new HashSet<>();
        getRandomAlphabet();
        /*for (int i = 0; i < chars.length; i++) {
            chars[i] = decipherPairs.get(chars[i]);
        }*/
        return String.valueOf(chars);
    }

    public HashMap<Character, Character> getDecipherPairs() {
        return new HashMap<>();
    }

    public String getRandomAlphabet() {
        String newAlphabet = "";
        ArrayList<Character> tempChars = new ArrayList<>(
                alphabet.chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()
                        )
        );
        while (!tempChars.isEmpty()) {
            int choosenChar = new Random().nextInt(tempChars.size());
            newAlphabet += tempChars.get(choosenChar);
            tempChars.remove(choosenChar);
        }
        return newAlphabet;
    }

}
