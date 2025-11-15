package lab6;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lab6.geometry2d.Circle;
import lab6.geometry2d.Rectangle;
import lab6.geometry2d.exceptions.NegativeRadiusException;
import lab6.geometry2d.exceptions.NegativeSizeException;

import java.util.Random;

public class AppController {

    @FXML
    private Canvas canvas;

    private Random rand = new Random();

    @FXML
    public void initialize() {
        canvas.setWidth(600);
        canvas.setHeight(400);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    private void addCircle() {
        double radius = 10 + rand.nextDouble() * 50; // случайный радиус от 10 до 60
        double x = rand.nextDouble() * (canvas.getWidth() - 2 * radius);
        double y = rand.nextDouble() * (canvas.getHeight() - 2 * radius);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        try {
            Circle c = new Circle(radius);
            drawCircle(c, x, y, color);
            System.out.println(c);
        } catch (NegativeRadiusException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addRectangle() {
        double width = 20 + rand.nextDouble() * 80; // случайная ширина
        double height = 20 + rand.nextDouble() * 80; // случайная высота
        double x = rand.nextDouble() * (canvas.getWidth() - width);
        double y = rand.nextDouble() * (canvas.getHeight() - height);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        try {
            Rectangle r = new Rectangle(width, height);
            drawRectangle(r, x, y, color);
            System.out.println(r);
        } catch (NegativeSizeException e) {
            e.printStackTrace();
        }
    }

    private void drawCircle(Circle c, double x, double y, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(x, y, c.area() > 0 ? Math.sqrt(c.area() / Math.PI) * 2 : 0, 
                         c.area() > 0 ? Math.sqrt(c.area() / Math.PI) * 2 : 0);
    }

    private void drawRectangle(Rectangle r, double x, double y, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(x, y, r.area() / r.perimeter() * 2, r.perimeter() / 2 - r.area() / r.perimeter());
    }
}

