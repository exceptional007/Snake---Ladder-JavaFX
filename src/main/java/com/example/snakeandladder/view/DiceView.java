package com.example.snakeandladder.view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;


public class DiceView {

    private final StackPane root;
    private final List<Circle> dots;

    public DiceView() {

        root = new StackPane();
        root.setPrefSize(60, 60);


        Rectangle face = new Rectangle(60, 60);
        face.setArcWidth(15);
        face.setArcHeight(15);
        face.setFill(Color.WHITE);
        face.setStroke(Color.BLACK);
        face.setStrokeWidth(2);


        GridPane dotGrid = new GridPane();
        dotGrid.setAlignment(Pos.CENTER);
        dotGrid.setHgap(5);
        dotGrid.setVgap(5);

        dots = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Circle dot = new Circle(5, Color.BLACK);
            dot.setVisible(false);
            dots.add(dot);
            dotGrid.add(dot, i % 3, i / 3);
        }

        root.getChildren().addAll(face, dotGrid);
        setDiceValue(1); // Default to show 1
    }

    /**
     * Updates the die face to show the specified value.
     * @param value The number to display (1-6).
     */
    public void setDiceValue(int value) {
        // Hide all dots first
        dots.forEach(dot -> dot.setVisible(false));

        // A map of which dots to show for each value
        boolean[][] patterns = {
                {}, // 0 (unused)
                {false, false, false, false, true, false, false, false, false}, // 1
                {true, false, false, false, false, false, false, false, true}, // 2
                {true, false, false, false, true, false, false, false, true}, // 3
                {true, false, true, false, false, false, true, false, true}, // 4
                {true, false, true, false, true, false, true, false, true}, // 5
                {true, false, true, true, false, true, true, false, true}  // 6
        };

        if (value > 0 && value <= 6) {
            for (int i = 0; i < 9; i++) {
                if (patterns[value][i]) {
                    dots.get(i).setVisible(true);
                }
            }
        }
    }

    /**
     * Gets the root pane of the DiceView to be added to a scene.
     * @return The StackPane containing the die visuals.
     */
    public StackPane getRoot() {
        return root;
    }
}
