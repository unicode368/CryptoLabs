package lab1;

public class FirstPart implements EncryptedPart {
    @Override
    public void process(String encryptedText) {
        char[] chars = encryptedText.toCharArray();
        char[] charsCopy = chars;
        for (int i = 1; i < 64; i++) {
            for (int j = 0; j < chars.length; j++) {
                charsCopy[j] = (char) (charsCopy[j] ^ i);
            }
            System.out.println(charsCopy);
            charsCopy = chars;
        }
    }
}
