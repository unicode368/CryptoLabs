package lab1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws IOException {
        String stringBytes = FileReader
                .extractRawData("1lab.txt", Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();

        Arrays.stream(stringBytes.split("(?<=\\G.{8})"))
                .forEach(s -> sb.append((char) Integer.parseInt(s, 2)));

        String labText = sb.toString();
        String decodedLabText = new String(Base64.getDecoder().decode(labText));
        //System.out.println(decodedLabText);
        String rawEncryptedText = decodedLabText
                .substring(decodedLabText.lastIndexOf('.') + 2);
        String firstPart = rawEncryptedText.split("\n")[0];
        String secondPart = rawEncryptedText.split("\n")[1];
        new FirstPart().process(firstPart);
        //new SecondPart().process(secondPart);
    }

}
