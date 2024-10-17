package org.example.java_fx;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    private boolean toggleDirection = true;

    @Override
    public void start(Stage primaryStage) {
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();

        Button toggleButton = new Button("→");

        toggleButton.setOnAction(e -> {
            if (toggleDirection) {
                textField2.setText(textField1.getText());
                textField1.clear();
                toggleButton.setText("←");
            } else {
                textField1.setText(textField2.getText());
                textField2.clear();
                toggleButton.setText("→");
            }
            toggleDirection = !toggleDirection;
        });

        // настройка GridPane - контейнер компоновки, размещ. эл.-ты в виде табл.
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // эл-ты по центру
        grid.setAlignment(Pos.CENTER);

        // Добавление элементов в сетку
        grid.add(textField1, 0, 0);
        grid.add(toggleButton, 1, 0);
        grid.add(textField2, 2, 0);

        // кнопка по центру
        GridPane.setHalignment(toggleButton, HPos.CENTER);

        grid.setStyle("-fx-background-color: lavender;");

        // созд. сцены
        Scene scene = new Scene(grid, 400, 400);

        // найстройка сцены
        primaryStage.setScene(scene);
        primaryStage.setTitle("люти перевертыш");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
