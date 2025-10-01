package lab2.task6.geometry2d;

import lab2.task6.exceptions.NegativeSizeException;

public class Rectangle implements Figure {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) throws NegativeSizeException {
        if (width < 0 || height < 0) {
            throw new NegativeSizeException("Width and height cannot be negative: " + width + ", " + height);
        }
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double area() {
        return width * height;
    }
    
    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
    
    @Override
    public String toString() {
        return "Rectangle(width=" + width + ", height=" + height + ")";
    }
}
