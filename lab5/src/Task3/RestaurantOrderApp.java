package Task3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RestaurantOrderApp extends Application {

    @Override
    public void start(Stage stage) {
        String[] dishes = {"Пицца", "Паста", "Салат", "Суп"};
        double[] prices = {500, 400, 250, 300};

        CheckBox[] checkBoxes = new CheckBox[dishes.length];
        Spinner<Integer>[] spinners = new Spinner[dishes.length];

        VBox itemsBox = new VBox(10);
        for (int i = 0; i < dishes.length; i++) {
            CheckBox cb = new CheckBox(dishes[i] + " — " + prices[i] + "₽");
            Spinner<Integer> sp = new Spinner<>(1, 10, 1);
            sp.setDisable(true);

            int index = i;
            cb.setOnAction(e -> sp.setDisable(!cb.isSelected()));

            checkBoxes[i] = cb;
            spinners[i] = sp;

            HBox row = new HBox(10, cb, sp);
            itemsBox.getChildren().add(row);
        }

        Button calcBtn = new Button("Посчитать чек");
        TextArea receipt = new TextArea();
        receipt.setEditable(false);

        calcBtn.setOnAction(e -> {
            double total = 0;
            StringBuilder sb = new StringBuilder("Чек:\n\n");
            for (int i = 0; i < dishes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    int count = spinners[i].getValue();
                    double cost = count * prices[i];
                    total += cost;
                    sb.append(String.format("%s × %d = %.2f₽\n", dishes[i], count, cost));
                }
            }
            sb.append("\nИтого: ").append(String.format("%.2f₽", total));
            receipt.setText(sb.toString());
        });

        VBox root = new VBox(15, itemsBox, calcBtn, receipt);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("Заказ в ресторане");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
