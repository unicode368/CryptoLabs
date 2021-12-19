package lab1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String stringBytes = FileReader
                .extractRawData("1lab.txt", Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();

        Arrays.stream(stringBytes.split("(?<=\\G.{8})"))
                .forEach(s -> sb.append((char) Integer.parseInt(s, 2)));

        String labText = sb.toString();
        String decodedLabText = new String(Base64.getDecoder().decode(labText));
        String taskOnly = decodedLabText
                .substring(0, decodedLabText.lastIndexOf('.') + 2);
        System.out.println(taskOnly);
        String rawEncryptedText = decodedLabText
                .substring(decodedLabText.lastIndexOf('.') + 2);
        String firstPart = rawEncryptedText.split("\n")[0];
        System.out.println(firstPart);
        System.out.println();
        String secondPart = rawEncryptedText.split("\n")[1];
        String thirdPart = rawEncryptedText.split("\n")[2];
        String fourthPart = rawEncryptedText.split("\n")[3];
        String hint1 = new FirstPart().process(firstPart);
        System.out.println(hint1);
        System.out.println();
        System.out.println(secondPart);
        System.out.println();
        String hint2 = new SecondPart().process(secondPart);
        System.out.println(hint2);
        System.out.println();
        System.out.println(thirdPart);
        new ThirdPart().process(secondPart);
    }

}
