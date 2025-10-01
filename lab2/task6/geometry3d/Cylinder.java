package lab2.task6.geometry3d;

import lab2.task6.geometry2d.Figure;
import lab2.task6.exceptions.InvalidBaseException;
import lab2.task6.exceptions.NegativeHeightException;

public class Cylinder {
    private Figure base;
    private double height;
    
    public Cylinder(Figure base, double height) throws InvalidBaseException, NegativeHeightException {
        if (base == null) {
            throw new InvalidBaseException("Base cannot be null");
        }
        if (height < 0) {
            throw new NegativeHeightException("Height cannot be negative: " + height);
        }
        this.base = base;
        this.height = height;
    }
    
    public double volume() {
        return base.area() * height;
    }
    
    @Override
    public String toString() {
        return "Cylinder(base=" + base + ", height=" + height + ")";
    }
}