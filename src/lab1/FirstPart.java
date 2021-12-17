package lab1;

public class FirstPart implements EncryptedPart {
    @Override
    public void process(String encryptedText) {
        StringBuilder toASCII = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i += 2) {
            String str = encryptedText.substring(i, i + 2);
            toASCII.append((char)Integer.parseInt(str, 16));
        }
        char[] textForDecryption = toASCII.toString().toCharArray();
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < textForDecryption.length; j++) {
                textForDecryption[j] = (char) (textForDecryption[j] ^ i);
            }
            System.out.println(textForDecryption);
            textForDecryption = toASCII.toString().toCharArray();
        }
    }
}
