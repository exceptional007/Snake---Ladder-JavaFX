package com.example.snakeandladder.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameModel {

    public final int numPlayers;
    public static final int WINNING_POSITION = 100;
    private final boolean[] isComputerPlayer;

    private final int[] playerPositions;
    private int currentPlayerIndex;
    private boolean isGameWon;
    private final Random random;
    private final Map<Integer, Integer> snakesAndLadders;

    public GameModel(int numPlayers, boolean[] isComputerPlayer) {
        this.numPlayers = numPlayers;
        this.isComputerPlayer = isComputerPlayer;
        this.playerPositions = new int[numPlayers];
        this.random = new Random();
        this.snakesAndLadders = new HashMap<>();

        initializeSnakesAndLadders();
        resetGame();
    }

    public boolean isCurrentPlayerComputer() {
        return isComputerPlayer[currentPlayerIndex];
    }

    public void resetGame() {
        for (int i = 0; i < numPlayers; i++) {
            playerPositions[i] = 1;
        }
        currentPlayerIndex = 0;
        isGameWon = false;
    }

    public void moveToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
    }

    public int rollDice() {
        return random.nextInt(6) + 1;
    }

    public void updatePlayerPosition(int playerIndex, int newPosition) {
        this.playerPositions[playerIndex] = newPosition;
        if (newPosition == WINNING_POSITION) {
            this.playerPositions[playerIndex] = WINNING_POSITION;
            this.isGameWon = true;
        }
    }

    public int getPlayerPosition(int playerIndex) {
        return playerPositions[playerIndex];
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public Integer getSnakeOrLadderDest(int position) {
        return snakesAndLadders.get(position);
    }

    public Map<Integer, Integer> getSnakesAndLaddersMap() {
        return snakesAndLadders;
    }

    private void initializeSnakesAndLadders() {
        // Ladders
        snakesAndLadders.put(4, 14);
        snakesAndLadders.put(9, 31);
        snakesAndLadders.put(20, 38);
        snakesAndLadders.put(28, 84);
        snakesAndLadders.put(40, 59);
        snakesAndLadders.put(63, 81);
        snakesAndLadders.put(71, 91);
        // Snakes
        snakesAndLadders.put(17, 7);
        snakesAndLadders.put(54, 34);
        snakesAndLadders.put(62, 19);
        snakesAndLadders.put(64, 60);
        snakesAndLadders.put(87, 24);
        snakesAndLadders.put(93, 89);
        snakesAndLadders.put(95, 69);
        snakesAndLadders.put(98, 79);
    }
}
