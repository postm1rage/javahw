package lab6;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lab6.geometry2d.Circle;
import lab6.geometry2d.Rectangle;
import lab6.geometry2d.exceptions.NegativeRadiusException;
import lab6.geometry2d.exceptions.NegativeSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppController {

    @FXML private Canvas canvas;
    @FXML private StackPane canvasPane;

    @FXML private Button btnAddCircle;
    @FXML private Button btnAddRect;
    @FXML private Button btnClear;

    @FXML private Label lblStatus;
    @FXML private Label lblCounts; // счётчик фигур

    private final Random rand = new Random();
    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX, offsetY;

    @FXML
    public void initialize() {
        // bind canvas to its container so it resizes with window
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());

        // mouse handlers
        canvas.setOnMousePressed(e -> {
            // iterate from topmost to bottommost
            for (int i = figures.size() - 1; i >= 0; i--) {
                DrawableFigure f = figures.get(i);
                if (f.contains(e.getX(), e.getY())) {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        // pick and start drag
                        selectedFigure = f;
                        offsetX = e.getX() - f.x;
                        offsetY = e.getY() - f.y;
                        // bring to front (top of list)
                        figures.remove(f);
                        figures.add(f);
                        updateStatus();
                        redraw();
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        // animate color transition
                        animateColorChange(f);
                    }
                    return; // handled
                }
            }
            // click on empty space: deselect
            selectedFigure = null;
            updateStatus();
            redraw();
        });

        canvas.setOnMouseDragged(e -> {
            if (selectedFigure != null && e.getButton() == MouseButton.PRIMARY) {
                selectedFigure.x = e.getX() - offsetX;
                selectedFigure.y = e.getY() - offsetY;
                ensureInsideCanvas(selectedFigure);
                updateStatus();
                redraw();
            }
        });

        canvas.setOnMouseReleased(e -> {
            selectedFigure = null;
            updateStatus();
        });

        // initial draw
        redraw();
        updateCounts();
    }

    // --- button actions ---
    @FXML
    private void addCircle() {
        double radius = 20 + rand.nextDouble() * 60; // 20..80
        double x = rand.nextDouble() * Math.max(1, canvas.getWidth() - 2 * radius);
        double y = rand.nextDouble() * Math.max(1, canvas.getHeight() - 2 * radius);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
        try {
            Circle c = new Circle(radius);
            DrawableFigure df = new DrawableFigure(c, x, y, color);
            figures.add(df);
            updateCounts();
            redrawWithAppear(df);
        } catch (NegativeRadiusException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void addRectangle() {
        double width = 30 + rand.nextDouble() * 100;  // 30..130
        double height = 20 + rand.nextDouble() * 80;  // 20..100
        double x = rand.nextDouble() * Math.max(1, canvas.getWidth() - width);
        double y = rand.nextDouble() * Math.max(1, canvas.getHeight() - height);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
        try {
            Rectangle r = new Rectangle(width, height);
            DrawableFigure df = new DrawableFigure(r, x, y, color);
            figures.add(df);
            updateCounts();
            redrawWithAppear(df);
        } catch (NegativeSizeException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clearAll() {
        figures.clear();
        selectedFigure = null;
        updateCounts();
        updateStatus();
        redraw();
    }

    // --- drawing ---
    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // background
        gc.setFill(Color.web("#f7f7f8"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // draw figures in order
        for (DrawableFigure f : figures) {
            drawFigure(gc, f);
        }
    }

    private void drawFigure(GraphicsContext gc, DrawableFigure f) {
        // shadow for depth
        gc.setGlobalAlpha(1.0);
        double shadowOffset = 4;
        gc.setFill(Color.rgb(0, 0, 0, 0.12));
        if (f.figure instanceof Circle c) {
            double r = c.getRadius(); // Circle must have getRadius (we assume added)
            gc.fillOval(f.x + shadowOffset, f.y + shadowOffset, r * 2, r * 2);
            gc.setFill(f.color);
            gc.fillOval(f.x, f.y, r * 2, r * 2);
        } else if (f.figure instanceof Rectangle r) {
            gc.fillRect(f.x + shadowOffset, f.y + shadowOffset, r.getWidth(), r.getHeight());
            gc.setFill(f.color);
            gc.fillRect(f.x, f.y, r.getWidth(), r.getHeight());
        }

        // if selected, draw highlight border
        if (f == selectedFigure) {
            gc.setLineWidth(2.0);
            gc.setStroke(Color.web("#FFD54F")); // amber highlight
            if (f.figure instanceof Circle c) {
                double r = c.getRadius();
                gc.strokeOval(f.x - 3, f.y - 3, r * 2 + 6, r * 2 + 6);
            } else if (f.figure instanceof Rectangle r) {
                gc.strokeRect(f.x - 3, f.y - 3, r.getWidth() + 6, r.getHeight() + 6);
            }
        }
    }

    // animate "appear" (scale from 0.1 to 1.0)
    private void redrawWithAppear(DrawableFigure df) {
        // simple animation via Timeline changing an opacityScale property
        DoubleProperty scale = new SimpleDoubleProperty(0.1);
        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(scale, 0.1)),
                new KeyFrame(Duration.millis(220), new KeyValue(scale, 1.0))
        );
        tl.currentTimeProperty().addListener((obs, oldT, newT) -> {
            // draw with scale
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.web("#f7f7f8"));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            for (DrawableFigure f : figures) {
                if (f == df) {
                    // temporarily draw scaled
                    gc.setGlobalAlpha(scale.get());
                    drawFigureScaled(gc, f, scale.get());
                    gc.setGlobalAlpha(1.0);
                } else {
                    drawFigure(gc, f);
                }
            }
        });
        tl.setOnFinished(e -> {
            redraw();
        });
        tl.play();
    }

    private void drawFigureScaled(GraphicsContext gc, DrawableFigure f, double scale) {
        if (f.figure instanceof Circle c) {
            double r = c.getRadius() * scale;
            gc.setFill(f.color);
            gc.fillOval(f.x + (1 - scale) * r, f.y + (1 - scale) * r, r * 2, r * 2);
        } else if (f.figure instanceof Rectangle r) {
            double w = r.getWidth() * scale;
            double h = r.getHeight() * scale;
            gc.setFill(f.color);
            gc.fillRect(f.x + (1 - scale) * w / 2, f.y + (1 - scale) * h / 2, w, h);
        }
    }

    // animate smooth color transition for RIGHT click
    private void animateColorChange(DrawableFigure f) {
        Color start = f.color;
        Color target = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        DoubleProperty rr = new SimpleDoubleProperty(start.getRed());
        DoubleProperty gg = new SimpleDoubleProperty(start.getGreen());
        DoubleProperty bb = new SimpleDoubleProperty(start.getBlue());

        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(rr, start.getRed()),
                        new KeyValue(gg, start.getGreen()),
                        new KeyValue(bb, start.getBlue())
                ),
                new KeyFrame(Duration.millis(350),
                        new KeyValue(rr, target.getRed()),
                        new KeyValue(gg, target.getGreen()),
                        new KeyValue(bb, target.getBlue())
                )
        );

        tl.currentTimeProperty().addListener((obs, oldT, newT) -> {
            f.color = Color.color(rr.get(), gg.get(), bb.get());
            redraw();
        });

        tl.play();
    }

    // ensure figure not leaves canvas bounds (keeps on screen)
    private void ensureInsideCanvas(DrawableFigure f) {
        if (f.figure instanceof Circle c) {
            double r = c.getRadius();
            f.x = Math.max(0, Math.min(f.x, canvas.getWidth() - 2 * r));
            f.y = Math.max(0, Math.min(f.y, canvas.getHeight() - 2 * r));
        } else if (f.figure instanceof Rectangle r) {
            f.x = Math.max(0, Math.min(f.x, canvas.getWidth() - r.getWidth()));
            f.y = Math.max(0, Math.min(f.y, canvas.getHeight() - r.getHeight()));
        }
    }

    // update status bar text
    private void updateStatus() {
        if (selectedFigure == null) {
            lblStatus.setText("Ничего не выбрано");
        } else {
            if (selectedFigure.figure instanceof Circle c) {
                double r = c.getRadius();
                lblStatus.setText(String.format("Circle — x: %.1f y: %.1f  r: %.1f", selectedFigure.x, selectedFigure.y, r));
            } else if (selectedFigure.figure instanceof Rectangle r) {
                lblStatus.setText(String.format("Rectangle — x: %.1f y: %.1f  w: %.1f h: %.1f",
                        selectedFigure.x, selectedFigure.y, r.getWidth(), r.getHeight()));
            } else {
                lblStatus.setText("Selected: unknown");
            }
        }
    }

    private void updateCounts() {
        long circles = figures.stream().filter(df -> df.figure instanceof Circle).count();
        long rects = figures.stream().filter(df -> df.figure instanceof Rectangle).count();
        lblCounts.setText(String.format("Circles: %d    Rectangles: %d    Total: %d", circles, rects, figures.size()));
    }

    // --- helper class ---
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
                double radius = c.getRadius();
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
