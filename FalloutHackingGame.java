import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class FalloutHackingGame {

    private final static int attempts = 4;
    private static Random random;
    private static Scanner scanner;

    public static void main(String[] args) throws IllegalArgumentException {
        WordMap wordMap = new WordMap();
        Map<Integer, List<String>> wordMapByCount = wordMap.parseFileByCount("enable1.txt");

        scanner = new Scanner(System.in);
        System.out.print("Select a difficulty through 1-5: ");

        int difficulty;
        try {
            difficulty = scanner.nextInt();
            if (difficulty < 1 || difficulty > 5) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Provide a number from 1 to 5");
        }

        Map<Integer, List<Integer>> difficultyMap = new HashMap<>();
        difficultyMap.put(1, Arrays.asList(4,5));
        difficultyMap.put(2, Arrays.asList(5,6,7));
        difficultyMap.put(3, Arrays.asList(8,9,10));
        difficultyMap.put(4, Arrays.asList(11,12,13));
        difficultyMap.put(5, Arrays.asList(14,15));

        random = new Random();
        List<Integer> amt = difficultyMap.get(difficulty);

        int wordCount = amt.get(random.nextInt(amt.size()));
        int wordLength = amt.get(random.nextInt(amt.size()));

        playGame(wordMapByCount.get(wordLength), wordCount);
    }

    private static void playGame(List<String> words, int wordCount) {
        Collections.shuffle(words);
        List<String> randomWords = words.subList(0, wordCount);

        String password = randomWords.get(random.nextInt(randomWords.size()));

        int wordIndex = 0;
        for (String s: randomWords) {
            wordIndex++;
            System.out.println("" + wordIndex + ": " + s);
        }

        for (int i = 0; i < attempts; i++) {
            System.out.print("Take a guess: ");
            try {
                int guessIndex = scanner.nextInt() - 1;
                String guess = randomWords.get(guessIndex);
                if (guess.equals(password)) {
                    System.out.println("Logging In...");
                    return;
                } else {
                    System.out.println(similarCharacters(password, guess));
                }

            } catch (Exception e) {
                System.out.println("Enter a number from 1 to " + randomWords.size());
                i--;
                continue;
            }
        }

        System.out.println("Authentication Failed");
    }

    private static int similarCharacters(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        int similarChars = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                similarChars++;
            }
        }

        return similarChars;
    }
}