package Assignments;

/**
 * File: csci1302/PAssign07
 * Author: Rachael Caropreso
 * Created on: April 4, 2024
 * Modified: April 5, 2024
 * Description: Create a phone number entry pad
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PAssign07 extends Application {
    private TextField tfPhoneNumber = new TextField();
    private Button btnAsterisk = new Button("*");
    private Button btnPound = new Button("#");
    private Button btnClear = new Button("Clear");
    private Button btnBackspace = new Button("←");

    @Override
    public void start(Stage primaryStage) {
        // Creating UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(5));

        // Adding components to the layout
        gridPane.add(new Label("Phone Number:"), 0, 0, 4, 1); // Spanning 4 columns
        gridPane.add(tfPhoneNumber, 0, 1, 4, 1); // Spanning 4 columns

        // Add number buttons
        int buttonRow = 2;
        int buttonCol = 0;
        for (int i = 1; i <= 9; i++) {
            Button button = new Button(Integer.toString(i));
            button.setOnAction(e -> {
                tfPhoneNumber.appendText(button.getText());
            });
            gridPane.add(button, buttonCol, buttonRow);
            buttonCol++;
            if (buttonCol == 3) {
                buttonCol = 0;
                buttonRow++;
            }
        }

        // Add zero button
        Button zeroButton = new Button("0");
        zeroButton.setOnAction(e -> {
            tfPhoneNumber.appendText(zeroButton.getText());
        });
        gridPane.add(zeroButton, 1, 5);

        // Add asterisk, pound, clear, and backspace buttons
        Button[] specialButtons = {btnAsterisk, btnPound, btnClear, btnBackspace};
        for (Button button : specialButtons) {
            if (button == btnClear) {
                button.setOnAction(e -> tfPhoneNumber.clear());
            } else if (button == btnBackspace) {
                button.setOnAction(e -> {
                    String text = tfPhoneNumber.getText();
                    if (!text.isEmpty()) {
                        tfPhoneNumber.setText(text.substring(0, text.length() - 1));
                    }
                });
            } else {
                button.setOnAction(e -> tfPhoneNumber.appendText(button.getText()));
            }
        }
        gridPane.add(btnAsterisk, 0, 5);
        gridPane.add(btnPound, 2, 5);
        gridPane.add(btnClear, 3, 2);
        gridPane.add(btnBackspace, 3, 3);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfPhoneNumber.setAlignment(Pos.BOTTOM_RIGHT);
        tfPhoneNumber.setEditable(false); // Disable editing of the phone number field

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Phone Number Entry Pad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
