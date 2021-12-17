package lab1;

public class FirstPart implements EncryptedPart {
    @Override
    public void process(String encryptedText) {
        char[] chars = encryptedText.toCharArray();
        for (int i = 0; i < 26; i++) {
            String a = "";
            for (int j = 0; j < chars.length; j++){
                a += chars[j]--;
            }
            System.out.println(a);
        }
        //System.out.println(encryptedText);
    }
}
