package com.sda.levi.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainJavaFx extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        System.out.println("Starting App");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First Java FX Window");
        primaryStage.setHeight(400);
        primaryStage.setWidth(800);
        primaryStage.setX(400);
        primaryStage.setY(300);

        // Stage -> Scene -> Container -> Control

        //Control
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");

        Text welcomeTitle = new Text("Welcome to my App");
        welcomeTitle.setFont(Font.font("Veranda",30));
        welcomeTitle.setFill(Paint.valueOf("#2455d1"));

        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        CheckBox checkBox = new CheckBox("Remember me");
        Button button = new Button("Login");


        //Containers
//        Group group = new Group();
//        group.getChildren().addAll(welcomeTitle,text2);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(usernameLabel,usernameTextField);
        hBox.setAlignment(Pos.CENTER);

        HBox hBoxPassword = new HBox();
        hBoxPassword.getChildren().addAll(passwordLabel,passwordField);
        hBoxPassword.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(0,0,50,30));
        vBox.getChildren().addAll(welcomeTitle, hBox,hBoxPassword, checkBox,button);

        /*
        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0, 0);
        gridPane.add(hBoxPassword, 0, 1);
        gridPane.add(button, 0, 2);
        gridPane.add(welcomeTitle, 1, 1);
        gridPane.add(text2, 0, 1);
        gridPane.getChildren().addAll(label,label2,welcomeTitle,text2);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(200);
        gridPane.setPadding(new Insets(0,0,50,30));
        */

        button.setOnMouseClicked(event -> {
            System.out.println("Trying to login");
        });

        usernameTextField.setOnKeyPressed(event -> {
            System.out.print(event.getText());
        });

        System.out.println("Current password: ");
        passwordField.setOnKeyTyped(event -> {
            StringBuilder sb = new StringBuilder();
            for( int i=0; i<passwordField.getText().length(); i++) {
                sb.append("*");
            }

            passwordField.setText(sb.toString());
            System.out.print(event.getCharacter());
        });


        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void stop() throws Exception {
        System.out.println("Stopping App");
    }

}