package lab3.task1;

import java.util.*;

public class CollectionOperations {
    
    public static List<Integer> createListFromArray(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }
    
    public static void sortAscending(List<Integer> list) {
        Collections.sort(list);
    }
    
    public static void sortDescending(List<Integer> list) {
        Collections.sort(list, Collections.reverseOrder());
    }
    
    public static void shuffleList(List<Integer> list) {
        Collections.shuffle(list);
    }
    
    public static void rotateList(List<Integer> list) {
        Collections.rotate(list, 1);
    }
    
    public static List<Integer> getUniqueElements(List<Integer> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }
    
    public static List<Integer> getDuplicateElements(List<Integer> list) {
        List<Integer> duplicates = new ArrayList<>();
        Map<Integer, Integer> frequencyMap = getFrequencyMap(list);
        
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }
        return duplicates;
    }
    

    public static int[] convertListToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    

    public static Map<Integer, Integer> getFrequencyMap(List<Integer> list) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        return frequencyMap;
    }
    
    public static void printList(List<Integer> list, String message) {
        System.out.println(message + ": " + list);
    }
    
    public static void printFrequencyMap(Map<Integer, Integer> frequencyMap, String message) {
        System.out.println(message + ":");
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue() + " time(s)");
        }
    }
}