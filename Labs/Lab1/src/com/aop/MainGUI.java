package com.aop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainGUI extends Application {

    private Button addBtn;

    private VBox vBox;

    @Override
    public void start(Stage primaryStage)
            throws Exception {
        primaryStage.setTitle("AOP");
        StackPane layout = new StackPane();
        addBtn = new Button();
        addBtn.setText("Add");
        vBox = new VBox();

        initTxtFields();


        vBox.getChildren().add(addBtn);
        layout.getChildren().add(addBtn);


        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initTxtFields() {
        TextField idText = new TextField("Id");
        TextField brandText = new TextField("Brand");
        TextField modelText = new TextField("Model");
        TextField priceText = new TextField("Price");
        vBox.getChildren().add(idText);
        vBox.getChildren().add(brandText);
        vBox.getChildren().add(modelText);
        vBox.getChildren().add(priceText);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
