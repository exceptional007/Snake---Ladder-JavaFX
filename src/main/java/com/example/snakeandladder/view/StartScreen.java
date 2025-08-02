package com.example.snakeandladder.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class StartScreen {

    private final VBox root;
    private final ChoiceBox<String> gameModeChoiceBox;
    private final Button startGameButton;

    public StartScreen() {
        // Create components
        Label titleLabel = new Label("Snake and Ladder");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        Label choiceLabel = new Label("Select Game Mode:");
        choiceLabel.setFont(Font.font("Arial", 18));

        gameModeChoiceBox = new ChoiceBox<>();
        gameModeChoiceBox.getItems().addAll("Player vs. Computer", "2 Players", "3 Players", "4 Players");
        gameModeChoiceBox.setValue("Player vs. Computer"); // Default value

        startGameButton = new Button("Start Game");
        startGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Create layout
        root = new VBox(20, titleLabel, choiceLabel, gameModeChoiceBox, startGameButton);
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(400, 300);
        root.setStyle("-fx-background-color: #F0F8FF;");
    }

    public VBox getRoot() {
        return root;
    }

    public String getSelectedGameMode() {
        return gameModeChoiceBox.getValue();
    }

    public Button getStartGameButton() {
        return startGameButton;
    }
}
