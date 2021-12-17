package lab1;

import java.util.Base64;

public class SecondPart implements EncryptedPart{
    @Override
    public void process(String encryptedText) {
        String decodedLabText = new String(Base64.getDecoder().decode(encryptedText));
        System.out.println(decodedLabText);
    }
}
