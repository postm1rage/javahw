package lab3.task4;
import java.util.*;

public class WordFrequencyCounter {
    
    public static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        if (text == null || text.trim().isEmpty()) {
            return frequencyMap;
        }
        
        String[] words = text.split("[\\s\\p{Punct}]+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                String normalizedWord = word.toLowerCase();
                frequencyMap.put(normalizedWord, frequencyMap.getOrDefault(normalizedWord, 0) + 1);
            }
        }
        
        return frequencyMap;
    }
}