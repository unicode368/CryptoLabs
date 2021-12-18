package lab1;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class SecondPart {
    public void process(String encryptedText, String hint) {
        encryptedText = new String(Base64.getDecoder().decode(encryptedText));
        char[] chars = encryptedText.toCharArray();
        String key = "a";
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ key.charAt(i % key.length()));
        }
        System.out.println(chars);
        //System.out.println(encryptedText);
        //System.out.println(hint);
    }


}
