package lab6;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AppController {

    @FXML
    private Canvas canvas;

    @FXML
    public void initialize() {
        // Задаём размер холста равный размеру окна при старте
        canvas.setWidth(600);
        canvas.setHeight(400);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    private void handleButton1() {
        System.out.println("Нажата кнопка 1");
    }

    @FXML
    private void handleButton2() {
        System.out.println("Нажата кнопка 2");
    }
}
