package com.example.snakeandladder;

import com.example.snakeandladder.controller.GameController;
import com.example.snakeandladder.model.GameModel;
import com.example.snakeandladder.view.BoardView;
import com.example.snakeandladder.view.StartScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StartScreen startScreen = new StartScreen();
        Scene startScene = new Scene(startScreen.getRoot());
        primaryStage.setTitle("Snake and Ladder - Setup");
        primaryStage.setScene(startScene);
        primaryStage.show();

        startScreen.getStartGameButton().setOnAction(e -> {
            String selectedMode = startScreen.getSelectedGameMode();
            int playerCount;
            boolean[] isComputer;

            switch (selectedMode) {
                case "Player vs. Computer":
                    playerCount = 2;
                    isComputer = new boolean[]{false, true}; // Player 2 is AI
                    break;
                case "3 Players":
                    playerCount = 3;
                    isComputer = new boolean[]{false, false, false};
                    break;
                case "4 Players":
                    playerCount = 4;
                    isComputer = new boolean[]{false, false, false, false};
                    break;
                case "2 Players":
                default:
                    playerCount = 2;
                    isComputer = new boolean[]{false, false};
                    break;
            }
            launchGame(primaryStage, playerCount, isComputer);
        });
    }

    private void launchGame(Stage primaryStage, int playerCount, boolean[] isComputer) {
        GameModel model = new GameModel(playerCount, isComputer);
        BoardView view = new BoardView(playerCount);
        GameController controller = new GameController(model, view);

        controller.initializeGame();

        Scene gameScene = new Scene(view.getRootPane());
        primaryStage.setTitle("Snake and Ladder Pro");
        primaryStage.setScene(gameScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}