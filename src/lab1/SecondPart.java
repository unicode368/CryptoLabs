package lab1;

import java.nio.charset.StandardCharsets;
import java.util.*;

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
        int max = Collections.max(aliquotNumbers);
        HashMap<Integer, Integer> keyLengths =
                possibleKeyLengthsAndMultiplicity(max, aliquotNumbers);
        int aliquotCount = 0;
        int keyLength = 0;
        for (Integer name: keyLengths.keySet()) {
            Integer value = keyLengths.get(name);
            if (value > aliquotCount) {
                aliquotCount = value;
                keyLength = name;
            }
        }
        System.out.println("Довжина ключа - " + keyLength);
        //System.out.println(aliquotNumbers.toString());
        /*String key = "a";
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ key.charAt(i % key.length()));
        }
        System.out.println(chars);*/
        //System.out.println(encryptedText);
        //System.out.println(hint);
    }

    public HashMap<Integer, Integer> possibleKeyLengthsAndMultiplicity
            (int max, ArrayList<Integer> numbers) {
        HashMap<Integer, Integer> keyLengths = new HashMap<>();
        int count = 0;
        for (int i = 2; i < max; i++) {
            count = 0;
            for (int j = 0; j < numbers.size(); j++) {
                if (numbers.get(j) % i == 0) {
                    count++;
                }
            }
            keyLengths.put(i, count);
        }
        return keyLengths;
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
