package lab2.task6;
import lab2.task6.geometry2d.Circle;
import lab2.task6.geometry2d.Rectangle;
import lab2.task6.geometry3d.Cylinder;

public class Main {
    public static void main(String[] args) {
        try {
            Circle circle = new Circle(5.0);
            Rectangle rectangle = new Rectangle(3.0, 4.0);
            
            Cylinder cylinder1 = new Cylinder(circle, 10.0);
            Cylinder cylinder2 = new Cylinder(rectangle, 8.0);
            
            System.out.println(circle);
            System.out.println("Area: " + circle.area());
            System.out.println("Perimeter: " + circle.perimeter());
            
            System.out.println(cylinder1);
            System.out.println("Volume: " + cylinder1.volume());
            
            System.out.println(cylinder2);
            System.out.println("Volume: " + cylinder2.volume());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}