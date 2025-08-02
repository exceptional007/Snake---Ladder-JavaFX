package com.example.snakeandladder.controller;

import com.example.snakeandladder.model.GameModel;
import com.example.snakeandladder.view.BoardView;
import com.example.snakeandladder.view.DiceView;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class GameController {
    private final GameModel model;
    private final BoardView view;
    private final Random random = new Random();

    public GameController(GameModel model, BoardView view) {
        this.model = model;
        this.view = view;
        attachEventHandlers();
    }

    public void initializeGame() {
        for (int i = 0; i < model.numPlayers; i++) {
            view.resetPlayerPiecePosition(i);
        }
        if (model.isCurrentPlayerComputer()) {
            triggerComputerTurn();
        }
    }

    private void attachEventHandlers() {
        view.getRollButton().setOnAction(e -> handlePlayerTurn());
        view.getPlayAgainButton().setOnAction(e -> resetGame());
    }

    private void handlePlayerTurn() {
        takeTurn(model.getCurrentPlayerIndex());
    }

    private void triggerComputerTurn() {
        view.setRollButtonDisabled(true);
        view.updateStatusLabel("Player " + (model.getCurrentPlayerIndex() + 1) + " (CPU) is thinking...");

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> takeTurn(model.getCurrentPlayerIndex()));
        pause.play();
    }

    private void takeTurn(int playerIndex) {
        view.setRollButtonDisabled(true);

        int diceRoll = model.rollDice();

        Timeline diceAnimation = createDiceRollAnimation(diceRoll);
        diceAnimation.setOnFinished(e -> {
            movePlayer(playerIndex, diceRoll);
        });
        diceAnimation.play();
    }

    private void movePlayer(int playerIndex, int diceRoll) {
        int currentPos = model.getPlayerPosition(playerIndex);
        int nextPos = currentPos + diceRoll;

        view.updateStatusLabel(String.format("Player %d rolled a %d!", playerIndex + 1, diceRoll));

        SequentialTransition sequence = view.createMoveAnimation(playerIndex, currentPos, Math.min(nextPos, GameModel.WINNING_POSITION));

        Integer specialDest = model.getSnakeOrLadderDest(nextPos);
        if (specialDest != null && nextPos < GameModel.WINNING_POSITION) {
            sequence.getChildren().add(view.createMoveAnimation(playerIndex, nextPos, specialDest));
            nextPos = specialDest;
        }

        model.updatePlayerPosition(playerIndex, nextPos);

        sequence.setOnFinished(event -> {
            if (model.isGameWon()) {
                view.updateStatusLabel(String.format("Player %d wins! Congratulations!", playerIndex + 1));
                view.showPlayAgainButton(true);
            } else {
                model.moveToNextPlayer();
                if (model.isCurrentPlayerComputer()) {
                    triggerComputerTurn();
                } else {
                    view.updateStatusLabel(String.format("Player %d's turn.", model.getCurrentPlayerIndex() + 1));
                    view.setRollButtonDisabled(false);
                }
            }
        });
        sequence.play();
    }

    private Timeline createDiceRollAnimation(int finalValue) {
        DiceView diceView = view.getDiceView();
        Timeline timeline = new Timeline();

        for (int i = 0; i < 10; i++) {
            int randomValue = random.nextInt(6) + 1;
            KeyFrame kf = new KeyFrame(Duration.millis(i * 80), e -> diceView.setDiceValue(randomValue));
            timeline.getKeyFrames().add(kf);
        }

        KeyFrame finalKf = new KeyFrame(Duration.millis(800), e -> diceView.setDiceValue(finalValue));
        timeline.getKeyFrames().add(finalKf);

        return timeline;
    }

    private void resetGame() {
        model.resetGame();
        for (int i = 0; i < model.numPlayers; i++) {
            view.resetPlayerPiecePosition(i);
        }
        view.showPlayAgainButton(false);
        view.getDiceView().setDiceValue(1);

        if (model.isCurrentPlayerComputer()) {
            view.setRollButtonDisabled(true);
            triggerComputerTurn();
        } else {
            view.setRollButtonDisabled(false);
            view.updateStatusLabel("New Game! Player 1, roll the dice.");
        }
    }
}
