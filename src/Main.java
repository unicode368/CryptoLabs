import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        String stringBytes = FileReader
                .extractRawData("1lab.txt", Charset.defaultCharset());
    }
}
