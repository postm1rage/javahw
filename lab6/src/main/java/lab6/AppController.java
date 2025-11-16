package lab6;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import lab6.geometry2d.Circle;
import lab6.geometry2d.Rectangle;
import lab6.geometry2d.exceptions.NegativeRadiusException;
import lab6.geometry2d.exceptions.NegativeSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppController {

    @FXML
    private Canvas canvas;

    private Random rand = new Random();

    private List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX, offsetY;

    @FXML
    public void initialize() {
        redraw();

        canvas.setOnMousePressed(e -> {
            for (int i = figures.size() - 1; i >= 0; i--) {
                DrawableFigure f = figures.get(i);
                if (f.contains(e.getX(), e.getY())) {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        selectedFigure = f;
                        offsetX = e.getX() - f.x;
                        offsetY = e.getY() - f.y;
                        figures.remove(f);
                        figures.add(f);
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        f.color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
                        redraw();
                    }
                    return;
                }
            }
        });

        canvas.setOnMouseDragged(e -> {
            if (selectedFigure != null) {
                selectedFigure.x = e.getX() - offsetX;
                selectedFigure.y = e.getY() - offsetY;
                redraw();
            }
        });

        canvas.setOnMouseReleased(e -> selectedFigure = null);
    }

    @FXML
    private void addCircle() {
        double radius = 20 + rand.nextDouble() * 40;
        double x = rand.nextDouble() * (canvas.getWidth() - 2 * radius);
        double y = rand.nextDouble() * (canvas.getHeight() - 2 * radius);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        try {
            Circle c = new Circle(radius);
            figures.add(new DrawableFigure(c, x, y, color));
            redraw();
        } catch (NegativeRadiusException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addRectangle() {
        double width = 20 + rand.nextDouble() * 80;
        double height = 20 + rand.nextDouble() * 80;
        double x = rand.nextDouble() * (canvas.getWidth() - width);
        double y = rand.nextDouble() * (canvas.getHeight() - height);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        try {
            Rectangle r = new Rectangle(width, height);
            figures.add(new DrawableFigure(r, x, y, color));
            redraw();
        } catch (NegativeSizeException e) {
            e.printStackTrace();
        }
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (DrawableFigure f : figures) {
            gc.setFill(f.color);
            if (f.figure instanceof Circle c) {
                double radius = Math.sqrt(c.area() / Math.PI);
                gc.fillOval(f.x, f.y, radius * 2, radius * 2);
            } else if (f.figure instanceof Rectangle r) {
                gc.fillRect(f.x, f.y, r.getWidth(), r.getHeight());
            }
        }
    }

    private static class DrawableFigure {
        lab6.geometry2d.Figure figure;
        double x, y;
        Color color;

        DrawableFigure(lab6.geometry2d.Figure figure, double x, double y, Color color) {
            this.figure = figure;
            this.x = x;
            this.y = y;
            this.color = color;
        }

        boolean contains(double px, double py) {
            if (figure instanceof Circle c) {
                double radius = Math.sqrt(c.area() / Math.PI);
                double centerX = x + radius;
                double centerY = y + radius;
                double dx = px - centerX;
                double dy = py - centerY;
                return dx * dx + dy * dy <= radius * radius;
            } else if (figure instanceof Rectangle r) {
                return px >= x && px <= x + r.getWidth() && py >= y && py <= y + r.getHeight();
            }
            return false;
        }
    }
}
