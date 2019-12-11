package com.sda.levi.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class MiniCalculator extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        System.out.println("Starting MiniCalculator...");
    }

    @Override
    public void start(Stage calculatorStage) throws Exception {
        calculatorStage.setTitle("MiniCalculator");
        calculatorStage.setHeight(230);
        calculatorStage.setWidth(176);
        calculatorStage.setX(150);
        calculatorStage.setY(300);

        Text welcomeTitle = new Text("Two-number op. MiniCalculator1.0");
        welcomeTitle.setFont(Font.font("Veranda", 10));
        welcomeTitle.setFill(Paint.valueOf("#2455d1"));

        TextField calculatorTextField = new TextField();

        Button[] buttons = new Button[10];
        for (int i = 0; i < 10; i++) {
            Button button = new Button(String.format("  %s  ", i));
            buttons[i] = button;
        }
        Button buttonClear = new Button("  C  ");
        Button buttonBackspace = new Button(" Del");
        Button buttonDot = new Button("   .  ");
        Button buttonDivide = new Button("  ÷ ");
        Button buttonMultiply = new Button("  x  ");
        Button buttonSubtract = new Button("  -  ");
        Button buttonAdd = new Button("  + ");
        Button buttonEquals = new Button("  = ");

        HBox hBox = new HBox();
        hBox.getChildren().add(calculatorTextField);
        hBox.setAlignment(Pos.TOP_LEFT);

        GridPane gridPane = new GridPane();
        gridPane.add(buttons[7], 0, 1);
        gridPane.add(buttons[8], 1, 1);
        gridPane.add(buttons[9], 2, 1);
        gridPane.add(buttonClear, 1, 0);
        gridPane.add(buttons[4], 0, 2);
        gridPane.add(buttons[5], 1, 2);
        gridPane.add(buttons[6], 2, 2);
        gridPane.add(buttonSubtract, 3, 2);
        gridPane.add(buttons[1], 0, 3);
        gridPane.add(buttons[2], 1, 3);
        gridPane.add(buttons[3], 2, 3);
        gridPane.add(buttonAdd, 3, 3);
        gridPane.add(buttonDot, 2, 4);
        gridPane.add(buttons[0], 1, 4);
        gridPane.add(buttonBackspace, 2, 0);
        gridPane.add(buttonEquals, 3, 4);
        gridPane.add(buttonDivide, 3, 0);
        gridPane.add(buttonMultiply, 3, 1);
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5, 0, 0, 5));
        vBox.getChildren().addAll(welcomeTitle, hBox, gridPane);

        calculatorTextField.setOnKeyPressed(event -> {
            System.out.print(event.getText());
        });

        for (int i = 0; i < 10; i++) {
            final int j = i;
            buttons[i].setOnMouseClicked(event -> {
                System.out.print(j);
                calculatorTextField.appendText(String.valueOf(j));
            });
        }

        buttonClear.setOnMouseClicked(event -> {
            calculatorTextField.clear();
            System.out.println();
        });

        buttonBackspace.setOnMouseClicked(event -> {
            int length = calculatorTextField.getText().length();
            if (length > 0) calculatorTextField.deleteText(length - 1, length);
        });

        buttonDot.setOnMouseClicked(event -> {
            calculatorTextField.appendText(".");
            System.out.print(".");
        });

        buttonDivide.setOnMouseClicked(event -> {
            calculatorTextField.appendText("/");
            System.out.print("/");
        });

        buttonMultiply.setOnMouseClicked(event -> {
            calculatorTextField.appendText("*");
            System.out.print("*");
        });

        buttonSubtract.setOnMouseClicked(event -> {
            calculatorTextField.appendText("-");
            System.out.print("-");
        });

        buttonAdd.setOnMouseClicked(event -> {
            calculatorTextField.appendText("+");
            System.out.print("+");
        });

        EventHandler<ActionEvent> equalsEvent = event ->
        {
            String initialText = calculatorTextField.getText();
            String[] operandStrings = initialText.split("[\\/\\*\\-\\+]");

            try {

                double[] operands = {Double.parseDouble(operandStrings[0]), Double.parseDouble(operandStrings[1])};
                double calculated = 0;

                //operates with TWO numbers so far
                if (initialText.contains("/")) calculated = operands[0] / operands[1];
                else if (initialText.contains("*")) calculated = operands[0] * operands[1];
                else if (initialText.contains("-")) calculated = operands[0] - operands[1];
                else if (initialText.contains("+")) calculated = operands[0] + operands[1];

                DecimalFormat df4 = new DecimalFormat("#.####");
                String result = String.valueOf(df4.format(calculated));
                if (result.contains(".0")) result = result.substring(0, result.length() - 2);

                calculatorTextField.setText(result);
                calculatorTextField.positionCaret(calculatorTextField.getText().length());

                System.out.println("\n" + calculatorTextField.getText());

            } catch (NumberFormatException e) {
                calculatorTextField.appendText("☻format!");
                System.out.println("\nWrong number format or more than 1 operator!");
            }
        };

        buttonEquals.setOnAction(equalsEvent);

        Scene scene = new Scene(vBox);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                buttonEquals.fire();
            }
        });

        calculatorStage.setScene(scene);
        calculatorStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stopping MiniCalculator.");
    }

}