package org.example.java_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TextFlag extends Application {

    private RadioButton color1Opt1, color1Opt2, color1Opt3, color1Opt4, color1Opt5;
    private RadioButton color2Opt1, color2Opt2, color2Opt3, color2Opt4, color2Opt5;
    private RadioButton color3Opt1, color3Opt2, color3Opt3, color3Opt4, color3Opt5;
    private TextFlow resultText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("флажочек");

        // выбор для 1ой полосы
        ToggleGroup group1 = new ToggleGroup();
        color1Opt1 = createColorRadioButton("оранжевый", group1);
        color1Opt2 = createColorRadioButton("сиреневый", group1);
        color1Opt3 = createColorRadioButton("изумрудный", group1);
        color1Opt4 = createColorRadioButton("красный", group1);
        color1Opt5 = createColorRadioButton("синий", group1);
        color1Opt3.setSelected(true);

        // выбор для 2ой полосы
        ToggleGroup group2 = new ToggleGroup();
        color2Opt1 = createColorRadioButton("оранжевый", group2);
        color2Opt2 = createColorRadioButton("сиреневый", group2);
        color2Opt3 = createColorRadioButton("изумрудный", group2);
        color2Opt4 = createColorRadioButton("красный", group2);
        color2Opt5 = createColorRadioButton("синий", group2);
        color2Opt3.setSelected(true);

        // выбор для 3ьей полосы
        ToggleGroup group3 = new ToggleGroup();
        color3Opt1 = createColorRadioButton("оранжевый", group3);
        color3Opt2 = createColorRadioButton("сиреневый", group3);
        color3Opt3 = createColorRadioButton("изумрудный", group3);
        color3Opt4 = createColorRadioButton("красный", group3);
        color3Opt5 = createColorRadioButton("синий", group3);
        color3Opt5.setSelected(true);

        Button drawButton = new Button("нарисовать");
        drawButton.setOnAction(e -> drawFlag());

        // результат
        resultText = new TextFlow();
        resultText.setPrefSize(300, 50);

        // сетка для располож-я эл-ов
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        // добавл. компоненты в сетку
        root.add(new Label("полоса №1:"), 0, 0);
        root.add(color1Opt1, 1, 0);
        root.add(color1Opt2, 2, 0);
        root.add(color1Opt3, 3, 0);
        root.add(color1Opt4, 4, 0);
        root.add(color1Opt5, 5, 0);

        root.add(new Label("полоса №2:"), 0, 1);
        root.add(color2Opt1, 1, 1);
        root.add(color2Opt2, 2, 1);
        root.add(color2Opt3, 3, 1);
        root.add(color2Opt4, 4, 1);
        root.add(color2Opt5, 5, 1);

        root.add(new Label("полоса №3:"), 0, 2);
        root.add(color3Opt1, 1, 2);
        root.add(color3Opt2, 2, 2);
        root.add(color3Opt3, 3, 2);
        root.add(color3Opt4, 4, 2);
        root.add(color3Opt5, 5, 2);

        VBox vbox = new VBox(10, root, drawButton, resultText);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);   // измен. размер окна нельзя
        primaryStage.show();
    }

    // Метод для создания RadioButton с цветом
    private RadioButton createColorRadioButton(String colorName, ToggleGroup group) {
        RadioButton button = new RadioButton(colorName);
        button.setToggleGroup(group);
        return button;
    }

    // Метод для отрисовки флага
    private void drawFlag() {
        String color1 = getColor(color1Opt1, color1Opt2, color1Opt3, color1Opt4, color1Opt5);
        String color2 = getColor(color2Opt1, color2Opt2, color2Opt3, color2Opt4, color2Opt5);
        String color3 = getColor(color3Opt1, color3Opt2, color3Opt3, color3Opt4, color3Opt5);

        resultText.getChildren().clear();
        resultText.getChildren().addAll(
                createColoredText("полоса №1: ", color1),
                createColoredText("полоса №2: ", color2),
                createColoredText("полоса №3: ", color3)
        );
    }

    // получ. цвета от выбора
    private String getColor(RadioButton opt1, RadioButton opt2, RadioButton opt3, RadioButton opt4, RadioButton opt5) {
        if (opt1.isSelected()) {
            return opt1.getText();
        } else if (opt2.isSelected()) {
            return opt2.getText();
        } else if (opt3.isSelected()) {
            return opt3.getText();
        } else if (opt4.isSelected()) {
            return opt4.getText();
        } else {
            return opt5.getText();
        }
    }

    // созд. текстовых эл-ов с цветом
    private Text createColoredText(String text, String color) {
        Text coloredText = new Text(text + color + "\n");
        coloredText.setFill(Color.web(getColorCode(color)));
        coloredText.setStyle("-fx-font-weight: bold;");
        return coloredText;
    }

    // HEX-код
    private String getColorCode(String colorName) {
        return switch (colorName) {
            case "оранжевый" -> "#ec7c26";
            case "сиреневый" -> "#b980d1";
            case "изумрудный" -> "#007539";
            case "красный" -> "#ff0000";
            case "синий" -> "#0000ff";
            default -> "#000000";
        };
    }
}