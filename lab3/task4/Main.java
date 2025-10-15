package lab3.task4;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter text:");
        String text = scanner.nextLine();
        
        Map<String, Integer> wordFrequency = WordFrequencyCounter.countWordFrequency(text);
        
        System.out.println("\nWord frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        scanner.close();
    }
}
