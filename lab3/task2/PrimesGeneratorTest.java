package lab3.task2;

import java.util.*;

public class PrimesGeneratorTest {
    public static void main(String[] args) {
        int N = 15;
        PrimesGenerator generator = new PrimesGenerator(N);
        

        List<Integer> forward = new ArrayList<>();
        while (generator.hasNext()) {
            forward.add(generator.next());
        }
        System.out.println("Forward sequence: " + forward);
        
        
        List<Integer> reverse = new ArrayList<>(forward);
        Collections.reverse(reverse);
        System.out.println("Reversed sequence: " + reverse); 
    }
}