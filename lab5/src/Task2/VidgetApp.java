package Task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VidgetApp extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("text (label)");
        TextField textField = new TextField("text field");
        Button button = new Button("button");

        CheckBox showLabel = new CheckBox("show label");
        CheckBox showText = new CheckBox("show text field");
        CheckBox showButton = new CheckBox("show button");

        showLabel.setSelected(true);
        showText.setSelected(true);
        showButton.setSelected(true);

        showLabel.setOnAction(e -> label.setVisible(showLabel.isSelected()));
        showText.setOnAction(e -> textField.setVisible(showText.isSelected()));
        showButton.setOnAction(e -> button.setVisible(showButton.isSelected()));


        VBox widgets = new VBox(10, label, textField, button);
        VBox controls = new VBox(10, showLabel, showText, showButton);

        HBox root = new HBox(30, widgets, controls);
        root.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(root));
        stage.setTitle("Vidget App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
