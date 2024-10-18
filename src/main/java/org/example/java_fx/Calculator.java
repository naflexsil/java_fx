package org.example.java_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Calculator extends Application {

    private TextField display;
    private Label historyLabel;
    private double num1 = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("калькуляторчик");

        // для отображ. текущ. ч-а
        display = new TextField();
        display.setEditable(false);
        display.setPrefSize(200, 50);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        display.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        // для отображ. истории
        historyLabel = new Label();
        historyLabel.setPrefWidth(200);
        historyLabel.setAlignment(Pos.CENTER_RIGHT);
        historyLabel.setFont(Font.font("Arial", 14));
        historyLabel.setStyle("-fx-text-fill: white;");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #9966cc;");

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int row = 2;    // нач. с 3го ряда (1ый занят под историю и дисплей)
        int col = 0;

        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPrefSize(50, 50);
            button.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 16px;");
            button.setOnAction(e -> handleButtonClick(label));
            root.add(button, col, row);
            ++col;
            if (col > 3) {
                col = 0;
                ++row;
            }
        }

        // добавл. историю и дисплей
        root.add(historyLabel, 0, 0, 4, 1);
        root.add(display, 0, 1, 4, 1);

        Scene scene = new Scene(root, 250, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // обраб. наж. кнопок
    private void handleButtonClick(String label) {
        if (label.matches("[0-9.]")) {
            if (startNewNumber) {
                display.setText(label);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + label);
            }
        } else if (label.matches("[/*\\-+]")) {
            operator = label;
            num1 = Double.parseDouble(display.getText());
            updateHistory(num1 + " " + operator);
            startNewNumber = true;
        } else if (label.equals("=")) {
            calculateResult();
        }
    }

    // обновл. истории
    private void updateHistory(String text) {
        historyLabel.setText(text);
    }

    private void calculateResult() {
        double num2 = Double.parseDouble(display.getText());
        double result = 0;

        try {
            result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        throw new ArithmeticException("деление на 0");
                    }
                    yield num1 / num2;
                }
                default -> result;
            };
            updateHistory(historyLabel.getText() + " " + num2 + " =");  // обновл. историю с результатом
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } catch (ArithmeticException ex) {
            showAlert();
            display.clear();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ошибка");
        alert.setHeaderText(null);
        alert.setContentText("делить на 0 нельзя!");
        alert.showAndWait();
    }
}