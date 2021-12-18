package lab1;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SecondPart {
    public void process(String encryptedText, String hint) {
        encryptedText = new String(Base64.getDecoder().decode(encryptedText));
        System.out.println(encryptedText);
        System.out.println(hint);
    }
}
