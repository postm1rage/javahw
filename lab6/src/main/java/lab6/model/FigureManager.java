package lab6.model;

import java.util.ArrayList;
import java.util.List;
import lab6.geometry2d.Circle;
import lab6.geometry2d.Rectangle;

public class FigureManager {
    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure;

    public List<DrawableFigure> getFigures() { return figures; }
    public DrawableFigure getSelectedFigure() { return selectedFigure; }
    public void setSelectedFigure(DrawableFigure f) { selectedFigure = f; }

    public void add(DrawableFigure df) { figures.add(df); }
    public void remove(DrawableFigure df) { figures.remove(df); }
    public void clear() { figures.clear(); selectedFigure = null; }

    public long countCircles() {
        return figures.stream().filter(f -> f.getFigure() instanceof Circle).count();
    }

    public long countRectangles() {
        return figures.stream().filter(f -> f.getFigure() instanceof Rectangle).count();
    }
}
