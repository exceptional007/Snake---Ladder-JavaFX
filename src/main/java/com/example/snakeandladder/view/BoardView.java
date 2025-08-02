package com.example.snakeandladder.view;

import com.example.snakeandladder.model.GameModel;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Map;

public class BoardView {
    private static final int TILE_SIZE = 55;
    private static final int BOARD_SIZE = 10;

    private final BorderPane rootPane;
    private final Pane boardOverlayPane;
    private final Label statusLabel;
    private final Button rollButton;
    private final Button playAgainButton;
    private final Circle[] playerPieces;
    private final int numPlayers;
    private final DiceView diceView;

    private static class Point {
        final double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    public BoardView(int numPlayers) {
        this.numPlayers = numPlayers;
        rootPane = new BorderPane();
        rootPane.setPadding(new Insets(10));
        rootPane.setStyle("-fx-background-color: #FFDDC1;");

        Pane boardContainer = createBoard();
        boardOverlayPane = (Pane) boardContainer.getChildren().get(1);
        rootPane.setCenter(boardContainer);

        playerPieces = new Circle[this.numPlayers];
        Color[] playerColors = {Color.DODGERBLUE, Color.DEEPPINK, Color.LIMEGREEN, Color.ORANGE};

        for (int i = 0; i < this.numPlayers; i++) {
            playerPieces[i] = new Circle(TILE_SIZE / 4.0);
            playerPieces[i].setStroke(Color.BLACK);
            playerPieces[i].setStrokeWidth(2);
            playerPieces[i].setFill(playerColors[i % playerColors.length]);
            boardOverlayPane.getChildren().add(playerPieces[i]);
        }

        rollButton = new Button("Roll Dice");
        rollButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        playAgainButton = new Button("Play Again?");
        playAgainButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        playAgainButton.setVisible(false);

        statusLabel = new Label("Welcome! Player 1, please roll the dice.");
        statusLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        diceView = new DiceView();

        HBox buttonBox = new HBox(10, rollButton, playAgainButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox controlPanel = new VBox(15, statusLabel, diceView.getRoot(), buttonBox);
        controlPanel.setAlignment(Pos.CENTER);
        controlPanel.setPadding(new Insets(10, 0, 10, 0));
        rootPane.setBottom(controlPanel);
    }

    public DiceView getDiceView() {
        return diceView;
    }

    public void resetPlayerPiecePosition(int playerIndex) {
        Point startPoint = getCoordinates(1);
        playerPieces[playerIndex].setTranslateX(0);
        playerPieces[playerIndex].setTranslateY(0);

        double xOffset = (playerIndex % 2 == 0) ? -TILE_SIZE / 5.0 : TILE_SIZE / 5.0;
        double yOffset = (playerIndex < 2) ? -TILE_SIZE / 5.0 : TILE_SIZE / 5.0;

        playerPieces[playerIndex].setCenterX(startPoint.x + xOffset);
        playerPieces[playerIndex].setCenterY(startPoint.y + yOffset);
    }

    public SequentialTransition createMoveAnimation(int playerIndex, int fromPos, int toPos) {
        Point endPoint = getCoordinates(toPos);
        Circle piece = playerPieces[playerIndex];

        double xOffset = (playerIndex % 2 == 0) ? -TILE_SIZE / 5.0 : TILE_SIZE / 5.0;
        double yOffset = (playerIndex < 2) ? -TILE_SIZE / 5.0 : TILE_SIZE / 5.0;

        TranslateTransition tt = new TranslateTransition(Duration.millis(800), piece);
        tt.setToX(endPoint.x - piece.getCenterX() + xOffset);
        tt.setToY(endPoint.y - piece.getCenterY() + yOffset);
        return new SequentialTransition(tt);
    }

    public BorderPane getRootPane() { return rootPane; }
    public Button getRollButton() { return rollButton; }
    public Button getPlayAgainButton() { return playAgainButton; }
    public void updateStatusLabel(String text) { statusLabel.setText(text); }
    public void showPlayAgainButton(boolean show) {
        rollButton.setVisible(!show);
        playAgainButton.setVisible(show);
    }
    public void setRollButtonDisabled(boolean disabled) { rollButton.setDisable(disabled); }

    private Pane createBoard() {
        GridPane grid = new GridPane();
        Pane overlay = new Pane();
        overlay.setPickOnBounds(false);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int tileNumber = getTileNumber(row, col);
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill((row + col) % 2 == 0 ? Color.web("#F0D9B5") : Color.web("#B58863"));
                tile.setStroke(Color.DARKSLATEGRAY);
                Text number = new Text(String.valueOf(tileNumber));
                number.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                StackPane stack = new StackPane(tile, number);
                grid.add(stack, col, row);
            }
        }
        drawSnakesAndLadders(overlay);
        return new Pane(grid, overlay);
    }

    private void drawSnakesAndLadders(Pane pane) {
        GameModel tempModel = new GameModel(2, new boolean[]{false, false});
        for (Map.Entry<Integer, Integer> entry : tempModel.getSnakesAndLaddersMap().entrySet()) {
            Point start = getCoordinates(entry.getKey());
            Point end = getCoordinates(entry.getValue());
            Line line = new Line(start.x, start.y, end.x, end.y);
            line.setStrokeWidth(5);
            line.setOpacity(0.8);
            line.setStroke(entry.getKey() < entry.getValue() ? Color.FORESTGREEN : Color.CRIMSON);
            pane.getChildren().add(line);
        }
    }

    private Point getCoordinates(int tileNumber) {
        int adjustedTile = tileNumber - 1;
        int row = adjustedTile / BOARD_SIZE;
        int col = adjustedTile % BOARD_SIZE;

        if (row % 2 != 0) {
            col = BOARD_SIZE - 1 - col;
        }

        double x = col * TILE_SIZE + TILE_SIZE / 2.0;
        double y = (BOARD_SIZE - 1 - row) * TILE_SIZE + TILE_SIZE / 2.0;
        return new Point(x, y);
    }

    private int getTileNumber(int row, int col) {
        if ((BOARD_SIZE - 1 - row) % 2 == 0) {
            return (BOARD_SIZE - 1 - row) * BOARD_SIZE + col + 1;
        }
        return (BOARD_SIZE - 1 - row) * BOARD_SIZE + (BOARD_SIZE - 1 - col) + 1;
    }
}
