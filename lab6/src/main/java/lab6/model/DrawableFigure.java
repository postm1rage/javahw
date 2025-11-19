package lab6.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lab6.geometry2d.Figure;
import lab6.geometry2d.Circle;
import lab6.geometry2d.Rectangle;

public class DrawableFigure {
    private Image texture;
    private final Figure figure;
    private double x, y;
    private Color color;

    public Image getTexture() { return texture; }
    public void setTexture(Image texture) { this.texture = texture; }

    public DrawableFigure(Figure figure, double x, double y, Color color) {
        this.figure = figure;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Figure getFigure() { return figure; }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public boolean contains(double px, double py) {
        if (figure instanceof Circle c) {
            double r = c.getRadius();
            double cx = x + r;
            double cy = y + r;
            double dx = px - cx;
            double dy = py - cy;
            return dx * dx + dy * dy <= r * r;
        } else if (figure instanceof Rectangle r) {
            return px >= x && px <= x + r.getWidth() && py >= y && py <= y + r.getHeight();
        }
        return false;
    }
}
