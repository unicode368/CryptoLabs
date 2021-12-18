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
        HashMap<String, ArrayList<Integer>> bigramsAndOccurences =
                new HashMap<>();
        //ArrayList<String> bigrams = new ArrayList<>();
        ArrayList<String> trigrams = new ArrayList<>();
        for (int i = 0; i < encryptedText.length() - 2; i++) {
            String bigram = encryptedText.substring(i, i + 2);
            ArrayList<Integer> occurences = countMatches(encryptedText, bigram);
            if (occurences.size() > 1) {
                bigramsAndOccurences.put(bigram, occurences);
            }
        }
        for (int i = 0; i < encryptedText.length() - 3; i++) {
            String bigram = encryptedText.substring(i, i + 3);
            if (countMatches(encryptedText, bigram).size() > 1) {
                trigrams.add(bigram);
            }
        }
        for (String name: bigramsAndOccurences.keySet()) {
            String value = bigramsAndOccurences.get(name).toString();
            System.out.println(name + " - " + value);
        }
        /*String key = "a";
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ key.charAt(i % key.length()));
        }
        System.out.println(chars);*/
        //System.out.println(encryptedText);
        //System.out.println(hint);
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
