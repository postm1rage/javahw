import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coefficients a, b, c for equation: ax^2 + bx + c = 0");
        System.out.print("a = ");
        double a = scanner.nextDouble();
        
        System.out.print("b = ");
        double b = scanner.nextDouble();
        
        System.out.print("c = ");
        double c = scanner.nextDouble();
        
        solveQuadraticEquation(a, b, c);
        scanner.close();
    }


    private static void solveQuadraticEquation(double a, double b, double c) {
        double d = Math.pow(b,2) - 4 * a * c;
        if (d < 0) System.out.println("No real roots");
        if (d == 0) {
            double x = -b / (2 * a);
            System.out.println("One real root: x = " + x);
        }
        else {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("Two real roots:");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }
    }
}
