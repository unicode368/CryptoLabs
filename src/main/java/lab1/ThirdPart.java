package lab1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ThirdPart {
    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int populationSize = 150;
    private int crossoverCount = 100;
    private int mutationsProb = 50;

    public String process(String encryptedText) {
        char[] chars = encryptedText.toCharArray();

        HashMap<String, Double> bigrams = new HashMap<>();
        Set<String> alphabets = new HashSet<>();
        String bestAlphabet = "";
        try {
            bigrams = getBigrams();
        } catch (Exception e) {
            System.exit(1);
        }
        while (alphabets.size() < populationSize) {
            alphabets.add(getRandomAlphabet());
        }
        int generation = 0;
        /*while (generation < topPopulation) {

        }*/

        return String.valueOf(chars);
    }

    public String decrypt(String textForDecryption, String randomAlphabet) {
        for (int i = 0; i < alphabet.length(); i++) {
            textForDecryption = textForDecryption.replaceAll(alphabet.substring(i, i + 1),
                    randomAlphabet.substring(i, i + 1));
        }
        return textForDecryption;
    }

    public double fitness(String decryptedText, HashMap<String, Double> bigrams) {
        double fitness = 0;
        double count = 0;
        int i = 0;
        double sum = 0;
        double[] counts = new double[bigrams.size()];

        for (Map.Entry<String, Double> entry : bigrams.entrySet()) {
            String str = entry.getKey();
            int lastIndex = 0;
            count = 0;

            while (lastIndex != -1) {

                lastIndex = decryptedText.indexOf(str, lastIndex);

                if (lastIndex != -1) {
                    count++;
                    lastIndex += str.length();
                }
            }
            counts[i] = count;
            i++;
            sum = count;
        }
        i = 0;
        for (Double value : bigrams.values()) {
            fitness += Math.pow(value - counts[i] / sum, 2);
        }
        return fitness;
    }


    public HashMap<String, Double> getBigrams() throws IOException {
        HashMap<String, Double> occurences = new HashMap<>();
        int sum = 0;
        String[] bigramsData = FileReader
                .extractRawData("english_bigrams_1.txt",
                        Charset.defaultCharset()).split("\n");
        for (String row : bigramsData) {
            String[] values = row.split(" ");
            occurences.put(values[0], Double.valueOf(values[1]));
            sum += Double.valueOf(values[1]);
        }
        int finalSum = sum;
        occurences.replaceAll((k, v) -> v / finalSum);
        return occurences;
    }

    public String getRandomAlphabet() {
        String newAlphabet = "";
        ArrayList<Character> tempChars = new ArrayList<>(
                alphabet.chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()
                        )
        );
        while (!tempChars.isEmpty()) {
            int choosenChar = new Random().nextInt(tempChars.size());
            newAlphabet += tempChars.get(choosenChar);
            tempChars.remove(choosenChar);
        }
        return newAlphabet;
    }

}
