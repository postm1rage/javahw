package lab6.util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lab6.model.DrawableFigure;
import lab6.ui.FigureRenderer;

import java.util.Random;

public class AnimationHelper {
    private static final Random rand = new Random();

    public static void animateColorChange(DrawableFigure f, FigureRenderer renderer) {
        Color start = f.getColor();
        Color target = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

        DoubleProperty rr = new javafx.beans.property.SimpleDoubleProperty(start.getRed());
        DoubleProperty gg = new javafx.beans.property.SimpleDoubleProperty(start.getGreen());
        DoubleProperty bb = new javafx.beans.property.SimpleDoubleProperty(start.getBlue());

        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(rr, start.getRed()),
                        new KeyValue(gg, start.getGreen()),
                        new KeyValue(bb, start.getBlue())),
                new KeyFrame(Duration.millis(350),
                        new KeyValue(rr, target.getRed()),
                        new KeyValue(gg, target.getGreen()),
                        new KeyValue(bb, target.getBlue()))
        );

        tl.currentTimeProperty().addListener((obs, oldT, newT) -> {
            f.setColor(Color.color(rr.get(), gg.get(), bb.get()));
            renderer.redraw();
        });

        tl.play();
    }
}
