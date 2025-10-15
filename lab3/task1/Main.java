package lab3.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int N = 15;
        
        // a
        int[] randomArray = ArrayGenerator.generateRandomArray(N);
        ArrayGenerator.printArray(randomArray, "a) Array:");
        
        // b
        List<Integer> numbers = CollectionOperations.createListFromArray(randomArray);
        CollectionOperations.printList(numbers, "b) List of array");
        
        // c
        List<Integer> sortedAsc = new ArrayList<>(numbers);
        CollectionOperations.sortAscending(sortedAsc);
        CollectionOperations.printList(sortedAsc, "c) Sorted by ascending");
        
        // d
        List<Integer> sortedDesc = new ArrayList<>(numbers);
        CollectionOperations.sortDescending(sortedDesc);
        CollectionOperations.printList(sortedDesc, "d) Sorted by descending");
        
        // e
        List<Integer> shuffled = new ArrayList<>(numbers);
        CollectionOperations.shuffleList(shuffled);
        CollectionOperations.printList(shuffled, "e) Shuffled list");
        
        // f
        List<Integer> rotated = new ArrayList<>(numbers);
        CollectionOperations.rotateList(rotated);
        CollectionOperations.printList(rotated, "f) Rotate by 1");
        
        // g
        List<Integer> unique = CollectionOperations.getUniqueElements(numbers);
        CollectionOperations.printList(unique, "g) Only unique elements");
        
        // h
        List<Integer> duplicates = CollectionOperations.getDuplicateElements(numbers);
        CollectionOperations.printList(duplicates, "h) Only duplicate elements");
        
        // i
        int[] backToArray = CollectionOperations.convertListToArray(numbers);
        ArrayGenerator.printArray(backToArray, "i) Array converted to list");
        
        // j
        Map<Integer, Integer> frequencyMap = CollectionOperations.getFrequencyMap(numbers);
        CollectionOperations.printFrequencyMap(frequencyMap, "j) Frequency map");
    }
}