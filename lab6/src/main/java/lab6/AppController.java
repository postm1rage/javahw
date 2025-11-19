package lab6;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lab6.model.DrawableFigure;
import lab6.model.FigureManager;
import lab6.ui.FigureRenderer;
import lab6.util.AnimationHelper;
import lab6.geometry2d.Circle;
import lab6.geometry2d.Rectangle;
import lab6.geometry2d.exceptions.NegativeRadiusException;
import lab6.geometry2d.exceptions.NegativeSizeException;

import java.util.Random;

public class AppController {
    private Image textureImage;

    @FXML private Canvas canvas;
    @FXML private StackPane canvasPane;
    @FXML private Button btnAddCircle, btnAddRect, btnClear;
    @FXML private Label lblStatus, lblCounts;

    private final FigureManager manager = new FigureManager();
    private FigureRenderer renderer;
    private double offsetX, offsetY;
    private final Random rand = new Random();

    @FXML
    public void initialize() {
        renderer = new FigureRenderer(canvas, manager);
        textureImage = new Image(getClass().getResourceAsStream("/image.png"));

        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());

        canvas.setOnMousePressed(e -> {
            for (int i = manager.getFigures().size()-1; i>=0; i--) {
                DrawableFigure f = manager.getFigures().get(i);
                if (f.contains(e.getX(), e.getY())) {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        manager.setSelectedFigure(f);
                        offsetX = e.getX() - f.getX();
                        offsetY = e.getY() - f.getY();
                        manager.getFigures().remove(f);
                        manager.getFigures().add(f);
                        updateStatus();
                        renderer.redraw();
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        AnimationHelper.animateColorChange(f, renderer);
                    }
                    return;
                }
            }
            manager.setSelectedFigure(null);
            updateStatus();
            renderer.redraw();
        });

        canvas.setOnMouseDragged(e -> {
            DrawableFigure f = manager.getSelectedFigure();
            if (f != null && e.getButton() == MouseButton.PRIMARY) {
                f.setX(e.getX() - offsetX);
                f.setY(e.getY() - offsetY);
                renderer.redraw();
            }
        });

        canvas.setOnMouseReleased(e -> {
            manager.setSelectedFigure(null);
            updateStatus();
        });

        renderer.redraw();
        updateCounts();
    }

    @FXML
    private void addCircle() {
        double radius = 20 + rand.nextDouble()*60;
        double x = rand.nextDouble() * Math.max(1, canvas.getWidth() - 2*radius);
        double y = rand.nextDouble() * Math.max(1, canvas.getHeight() - 2*radius);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        try {
            Circle c = new Circle(radius);
            DrawableFigure df = new DrawableFigure(c, x, y, color);

            if (rand.nextDouble() < 0.01) {   // 1%
                df.setTexture(textureImage);
            }

            manager.add(df);
            updateCounts();
            renderer.redraw();
        } catch (NegativeRadiusException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void addRectangle() {
        double width = 30 + rand.nextDouble()*100;
        double height = 20 + rand.nextDouble()*80;
        double x = rand.nextDouble() * Math.max(1, canvas.getWidth() - width);
        double y = rand.nextDouble() * Math.max(1, canvas.getHeight() - height);
        Color color = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        try {
            Rectangle r = new Rectangle(width, height);
            DrawableFigure df = new DrawableFigure(r, x, y, color);
            manager.add(df);
            updateCounts();
            renderer.redraw();
        } catch (NegativeSizeException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clearAll() {
        manager.clear();
        updateCounts();
        updateStatus();
        renderer.redraw();
    }

    private void updateStatus() {
        DrawableFigure f = manager.getSelectedFigure();
        if (f == null) {
            lblStatus.setText("Ничего не выбрано");
        } else {
            if (f.getFigure() instanceof Circle c) {
                lblStatus.setText(String.format("Circle — x: %.1f y: %.1f r: %.1f", f.getX(), f.getY(), c.getRadius()));
            } else if (f.getFigure() instanceof Rectangle r) {
                lblStatus.setText(String.format("Rectangle — x: %.1f y: %.1f w: %.1f h: %.1f", f.getX(), f.getY(), r.getWidth(), r.getHeight()));
            }
        }
    }

    private void updateCounts() {
        lblCounts.setText(String.format("Circles: %d Rectangles: %d Total: %d",
                manager.countCircles(), manager.countRectangles(), manager.getFigures().size()));
    }
}
