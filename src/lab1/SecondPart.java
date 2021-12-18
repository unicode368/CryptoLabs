package lab1;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public class SecondPart {
    public void process(String encryptedText, String hint) {
        encryptedText = new String(Base64.getDecoder().decode(encryptedText));
        char[] chars = encryptedText.toCharArray();
        //searching for bigrams
        HashMap<String, ArrayList<Integer>> bigramsTrigramsAndOccurences =
                new HashMap<>();
        //ArrayList<String> bigrams = new ArrayList<>();
        for (int i = 0; i < encryptedText.length() - 2; i++) {
            String bigram = encryptedText.substring(i, i + 2);
            ArrayList<Integer> occurences = countMatches(encryptedText, bigram);
            if (occurences.size() > 1) {
                bigramsTrigramsAndOccurences.put(bigram, occurences);
            }
        }
        for (int i = 0; i < encryptedText.length() - 3; i++) {
            String trigram = encryptedText.substring(i, i + 3);
            ArrayList<Integer> occurences = countMatches(encryptedText, trigram);
            if (countMatches(encryptedText, trigram).size() > 1) {
                bigramsTrigramsAndOccurences.put(trigram, occurences);
            }
        }
        ArrayList<Integer> aliquotNumbers = new ArrayList<>();
        for (String name: bigramsTrigramsAndOccurences.keySet()) {
            getAllDiffereces(aliquotNumbers,
                    bigramsTrigramsAndOccurences.get(name));
        }
        System.out.println(aliquotNumbers.toString());
        /*String key = "a";
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ key.charAt(i % key.length()));
        }
        System.out.println(chars);*/
        //System.out.println(encryptedText);
        //System.out.println(hint);
    }

    public void getAllDiffereces(ArrayList<Integer> aliquotNumbers,
                                 ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < i; j++) {
                aliquotNumbers.add(numbers.get(i) - numbers.get(j));
            }
        }
    }

    public static ArrayList<Integer> countMatches(String text, String str) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int index = 0;
        while (true) {
            index = text.indexOf(str, index);
            if (index != -1) {
                indexes.add(index);
                index += str.length();
            }
            else {
                break;
            }
        }

        return indexes;
    }


}
