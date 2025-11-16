package lab6.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lab6.model.DrawableFigure;
import lab6.model.FigureManager;
import lab6.geometry2d.Circle;
import lab6.geometry2d.Rectangle;

import java.util.List;

public class FigureRenderer {
    private final Canvas canvas;
    private final FigureManager manager;

    public FigureRenderer(Canvas canvas, FigureManager manager) {
        this.canvas = canvas;
        this.manager = manager;
    }

    public void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#f7f7f8"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        List<DrawableFigure> figures = manager.getFigures();
        for (DrawableFigure f : figures) drawFigure(gc, f);
    }

    private void drawFigure(GraphicsContext gc, DrawableFigure f) {
        gc.setGlobalAlpha(1.0);
        double shadowOffset = 4;

        gc.setFill(Color.rgb(0,0,0,0.12));
        if (f.getFigure() instanceof Circle c) {
            double r = c.getRadius();
            gc.fillOval(f.getX() + shadowOffset, f.getY() + shadowOffset, r*2, r*2);
            gc.setFill(f.getColor());
            gc.fillOval(f.getX(), f.getY(), r*2, r*2);
        } else if (f.getFigure() instanceof Rectangle r) {
            gc.fillRect(f.getX() + shadowOffset, f.getY() + shadowOffset, r.getWidth(), r.getHeight());
            gc.setFill(f.getColor());
            gc.fillRect(f.getX(), f.getY(), r.getWidth(), r.getHeight());
        }

        if (f == manager.getSelectedFigure()) {
            gc.setLineWidth(2.0);
            gc.setStroke(Color.web("#FFD54F"));
            if (f.getFigure() instanceof Circle c) {
                double r = c.getRadius();
                gc.strokeOval(f.getX() - 3, f.getY() - 3, r*2 + 6, r*2 + 6);
            } else if (f.getFigure() instanceof Rectangle r) {
                gc.strokeRect(f.getX() - 3, f.getY() - 3, r.getWidth() + 6, r.getHeight() + 6);
            }
        }
    }
}
