package lab1;

public class FirstPart {
    public String process(String encryptedText) throws InterruptedException {
        String hint = "";
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
            //System.out.println(textForDecryption);
            //Very important line. You'll never see an answer without it
            //System.out.println();
            if (i == 55) {
                hint = String.valueOf(textForDecryption);
            }
            //Thread.sleep(1000);
            textForDecryption = toASCII.toString().toCharArray();
        }
        return hint;
    }
}
