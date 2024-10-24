package org.example.java_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HideAndSeek extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("пипупипопипо");
        ColorPicker colorPicker = new ColorPicker();
        Slider slider = new Slider(0, 100, 50);

        CheckBox buttonCheckBox = new CheckBox("спрятать / показать кнопку");
        CheckBox colorPickerCheckBox = new CheckBox("спрятать / показать выбор цвета");
        CheckBox sliderCheckBox = new CheckBox("спрятать / показать ползунок");

        // установка обработчиков для чекбоксов. Виджет по умолч. виден
        buttonCheckBox.setSelected(true);
        buttonCheckBox.setOnAction(e -> button.setVisible(buttonCheckBox.isSelected()));

        colorPickerCheckBox.setSelected(true);
        colorPickerCheckBox.setOnAction(e -> colorPicker.setVisible(colorPickerCheckBox.isSelected()));

        sliderCheckBox.setSelected(true);
        sliderCheckBox.setOnAction(e -> slider.setVisible(sliderCheckBox.isSelected()));

        // размещ. виджеты в VBox
        VBox layout = new VBox(10);     // вертик. располож. эл-ов с отступом 10 пикс.
        layout.setPadding(new Insets(15));      // внутр. отступы от границ окна
        layout.getChildren().addAll(buttonCheckBox, button, colorPickerCheckBox, colorPicker, sliderCheckBox, slider);

        // созд. сцену
        Scene scene = new Scene(layout, 300, 250);

        // настройка окна
        primaryStage.setScene(scene);
        primaryStage.setTitle("Прячемся");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}