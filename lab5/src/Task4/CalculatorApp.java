package Task4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private TextField input;
    private double currentValue = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    @Override
    public void start(Stage stage) {
        input = new TextField();
        input.setEditable(false);
        input.setPrefHeight(50);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));

        String[][] buttons = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", ".", "=", "+"},
                {"C"}
        };

        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                String text = buttons[r][c];
                Button btn = new Button(text);
                btn.setPrefSize(60, 40);
                btn.setOnAction(e -> handleButton(text));
                grid.add(btn, c, r);
            }
        }

        VBox root = new VBox(10, input, grid);
        root.setPadding(new Insets(10));
        stage.setScene(new Scene(root));
        stage.setTitle("Калькулятор");
        stage.show();
    }

    private void handleButton(String text) {
        if (text.matches("[0-9\\.]")) {
            if (startNewNumber) {
                input.setText(text);
                startNewNumber = false;
            } else {
                input.setText(input.getText() + text);
            }
        } else if (text.matches("[+\\-*/]")) {
            compute();
            operator = text;
            startNewNumber = true;
        } else if (text.equals("=")) {
            compute();
            operator = "";
            startNewNumber = true;
        } else if (text.equals("C")) {
            input.clear();
            currentValue = 0;
            operator = "";
            startNewNumber = true;
        }
    }

    private void compute() {
        try {
            double value = input.getText().isEmpty() ? 0 : Double.parseDouble(input.getText());
            switch (operator) {
                case "+": currentValue += value; break;
                case "-": currentValue -= value; break;
                case "*": currentValue *= value; break;
                case "/":
                    if (value == 0) {
                        input.setText("Ошибка: деление на 0");
                        operator = "";
                        startNewNumber = true;
                        return;
                    }
                    currentValue /= value;
                    break;
                default: currentValue = value; break;
            }
            input.setText(String.valueOf(currentValue));
        } catch (NumberFormatException e) {
            input.setText("Ошибка ввода");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
