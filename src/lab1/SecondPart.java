package lab1;

public class SecondPart implements EncryptedPart{
    @Override
    public void process(String encryptedText) {
        char[] chars = encryptedText.toCharArray();
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < chars.length; j++) {
                chars[j] = (char) (chars[j] ^ i);
            }
            System.out.println(chars);
            chars = encryptedText.toCharArray();
        }
    }
}
