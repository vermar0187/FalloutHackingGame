import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class WordMap {

    public Map<Integer, List<String>> parseFileByCount(String filePath) {
        Map<Integer, List<String>> wordMap = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String word = reader.readLine();
            while (word != null) {
                int wordLength = word.length();
                if (wordMap.containsKey(wordLength)) {
                    wordMap.get(wordLength).add(word);
                } else {
                    List<String> stringList = new ArrayList<>();
                    stringList.add(word);
                    wordMap.put(wordLength, stringList);
                }
                word = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordMap;
    }
}