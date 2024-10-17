package org.example.java_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Restaurant extends Application {

    // map для хран. выбранных блюд и их цен
    private final Map<String, Double> menu = new HashMap<>();
    // map для хран-я кол-ва выбранных блюд
    private final Map<String, Integer> order = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        menu.put("Суши Филадельфия", 500.0);
        menu.put("Том Ям", 650.0);
        menu.put("Милкшейк", 250.0);
        menu.put("Фо Бо", 550.0);
        menu.put("Салат Цезарь", 550.0);
        menu.put("Латте", 170.0);

        // созд. интерфейса
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label menuLabel = new Label("Меню");
        menuLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        layout.getChildren().add(menuLabel);

        // добавл. эл-ов меню
        for (String dish : menu.keySet()) {
            CheckBox dishCheckBox = new CheckBox(dish);
            Label priceLabel = new Label(String.format("%.2f рублей", menu.get(dish)));
            Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
            quantitySpinner.setDisable(true);

            dishCheckBox.setOnAction(e -> {
                if (dishCheckBox.isSelected()) {
                    order.put(dish, quantitySpinner.getValue());
                    quantitySpinner.setDisable(false);
                } else {
                    order.remove(dish);
                    quantitySpinner.setDisable(true);
                }
            });

            quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (dishCheckBox.isSelected()) {
                    order.put(dish, newVal);
                }
            });

            // размещ. эл-ов в интерфейсе
            GridPane dishPane = new GridPane();
            dishPane.setHgap(10);
            dishPane.add(dishCheckBox, 0, 0);
            dishPane.add(priceLabel, 1, 0);
            dishPane.add(new Label("Количество: "), 2, 0);
            dishPane.add(quantitySpinner, 3, 0);

            layout.getChildren().add(dishPane);
        }

        Button orderButton = new Button("Оформить заказ");
        orderButton.setOnAction(e -> showReceipt());

        layout.getChildren().add(orderButton);

        Scene scene = new Scene(layout, 600, 600);
        scene.getRoot().setStyle("-fx-background-color: #f5770a;");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ресторан");
        primaryStage.show();
    }

    // формиров. и отображ. чека
    private void showReceipt() {
        Stage receiptStage = new Stage();
        receiptStage.initModality(Modality.APPLICATION_MODAL);
        receiptStage.setTitle("Чек");

        VBox receiptLayout = new VBox(10);
        receiptLayout.setPadding(new Insets(15));

        double totalPrice = 0;

        for (String dish : order.keySet()) {
            int quantity = order.get(dish);
            double price = menu.get(dish) * quantity;
            totalPrice += price;

            Label dishLabel = new Label(dish);
            dishLabel.setStyle("-fx-font-weight: bold;");

            receiptLayout.getChildren().add(new Label(
                    String.format("%s: %d x %.2f рублей = %.2f рублей",
                            dishLabel.getText(), quantity, menu.get(dish), price)
            ));
        }

        Label totalLabel = new Label("Итоговая сумма: " + String.format("%.2f рублей", totalPrice));
        totalLabel.setStyle("-fx-font-weight: bold;");
        receiptLayout.getChildren().add(new Label(""));
        receiptLayout.getChildren().add(totalLabel);

        Scene receiptScene = new Scene(receiptLayout, 300, 200);
        receiptScene.getRoot().setStyle("-fx-background-color: #f5770a;");

        receiptStage.setScene(receiptScene);
        receiptStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


