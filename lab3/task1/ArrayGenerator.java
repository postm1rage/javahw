package lab3.task1;

import java.util.Random;

public class ArrayGenerator {
    private static final Random random = new Random();
    
    public static int[] generateRandomArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }
    
    public static void printArray(int[] array, String message) {
        System.out.print(message + ": [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
