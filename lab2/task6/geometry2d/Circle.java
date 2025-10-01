package lab2.task6.geometry2d;
import lab2.task6.exceptions.NegativeRadiusException;

public class Circle implements Figure {
    private double radius;
    
    public Circle(double radius) throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius cannot be negative: " + radius);
        }
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
    
    @Override
    public String toString() {
        return "Circle(radius=" + radius + ")";
    }
}
