package lab3.task5;
import java.util.*;

public class MapSwapper {
    public static <K, V> Map<V, K> swapKeysAndValues(Map<K, V> map) {
        Map<V, K> swapped = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            swapped.put(entry.getValue(), entry.getKey());
        }
        return swapped;
    }
    
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println("Original: " + map);
        Map<Integer, String> swapped = swapKeysAndValues(map);
        System.out.println("Swapped: " + swapped);
    }
}
