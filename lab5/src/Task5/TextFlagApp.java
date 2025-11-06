package Task5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TextFlagApp extends Application {

    private final ToggleGroup topGroup = new ToggleGroup();
    private final ToggleGroup middleGroup = new ToggleGroup();
    private final ToggleGroup bottomGroup = new ToggleGroup();
    private final Label resultLabel = new Label();

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(15));

        root.getChildren().addAll(
                createRow("Верхняя полоса", topGroup),
                createRow("Средняя полоса", middleGroup),
                createRow("Нижняя полоса", bottomGroup)
        );

        Button drawButton = new Button("Нарисовать");
        drawButton.setOnAction(e -> drawFlag());
        root.getChildren().addAll(drawButton, resultLabel);

        Scene scene = new Scene(root, 350, 300);
        stage.setScene(scene);
        stage.setTitle("Текстовый флаг");
        stage.setResizable(false);
        stage.show();
    }

    private HBox createRow(String label, ToggleGroup group) {
        HBox row = new HBox(10);
        Label rowLabel = new Label(label);
        RadioButton red = new RadioButton("Красный");
        RadioButton green = new RadioButton("Зелёный");
        RadioButton blue = new RadioButton("Синий");
        RadioButton white = new RadioButton("Белый");
        red.setToggleGroup(group);
        green.setToggleGroup(group);
        blue.setToggleGroup(group);
        white.setToggleGroup(group);
        row.getChildren().addAll(rowLabel, red, green, blue, white);
        return row;
    }

    private void drawFlag() {
        String top = getSelected(topGroup);
        String mid = getSelected(middleGroup);
        String bot = getSelected(bottomGroup);

        if (top == null || mid == null || bot == null) {
            resultLabel.setText("Выберите все цвета!");
            return;
        }

        resultLabel.setText("Флаг: " + top + ", " + mid + ", " + bot);
    }

    private String getSelected(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        return selected == null ? null : selected.getText();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
