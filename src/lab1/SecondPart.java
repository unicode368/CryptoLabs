package lab1;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class SecondPart {
    public void process(String encryptedText, String hint) {
        encryptedText = new String(Base64.getDecoder().decode(encryptedText));
        char[] chars = encryptedText.toCharArray();
        //searching for bigrams
        ArrayList<String> bigrams = new ArrayList<>();
        ArrayList<String> trigrams = new ArrayList<>();
        for (int i = 0; i < encryptedText.length() - 2; i++) {
            String bigram = encryptedText.substring(i, i + 2);
            if (countMatches(encryptedText, bigram).size() > 1) {
                bigrams.add(bigram);
            }
        }
        for (int i = 0; i < encryptedText.length() - 3; i++) {
            String bigram = encryptedText.substring(i, i + 3);
            if (countMatches(encryptedText, bigram).size() > 1) {
                trigrams.add(bigram);
            }
        }
        for (String str : trigrams) {
            System.out.print(str + " ");
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
