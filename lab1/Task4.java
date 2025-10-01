public class Task4 {
    public static void main(String[] args) {
        double limit = Math.pow(10, -6);
        double result = 0;
        double n = 2;
        double current;
        
        do {
            current = 1.0 / (n * n + n - 2);
            result += current;
            n++;
        } while (current <= limit);
        
        System.out.println("Result: " + result);
    }
}