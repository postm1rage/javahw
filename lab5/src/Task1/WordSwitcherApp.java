package Task1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordSwitcherApp extends Application {

    private boolean forward = true;

    @Override
    public void start(Stage primaryStage) {

        TextField field1 = new TextField();
        TextField field2 = new TextField();

        Button switchButton = new Button("→");

        switchButton.setOnAction(e -> {
            if (forward) {
                field2.setText(field1.getText());
                field1.clear();
                switchButton.setText("-->");
            } else {
                field1.setText(field2.getText());
                field2.clear();
                switchButton.setText("<--");
            }
            forward = !forward;
        });

        HBox fieldsBox = new HBox(10, field1, switchButton, field2);
        VBox root = new VBox(20, fieldsBox);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root);
        primaryStage.setTitle("Word Switcher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
