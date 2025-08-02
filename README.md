
<div align="center">
  <h1>🐍🎲 Snake and Ladder - A JavaFX Desktop Game</h1>
  <img src="https://img.shields.io/badge/Java-17%2B-blue" alt="Java Version">
  <img src="https://img.shields.io/badge/JavaFX-17%2B-green" alt="JavaFX Version">
</div>

---

## 🕹️ Overview

A modern, professional implementation of the classic Snake and Ladder board game, built with Java and the JavaFX framework. This project showcases a clean, maintainable codebase structured around the Model-View-Controller (MVC) architectural pattern, complete with multiple game modes and polished animations.

---

## ✨ Features

- **Dynamic Game Modes:**
  - Player vs. Computer: Test your luck against a simple AI opponent.
  - Multiplayer: Play with 2, 3, or 4 human players locally.
- **Visual Dice Animation:** An animated die rolls on the screen for each turn, providing clear visual feedback instead of just text.
- **Engaging UI:** A visually appealing and intuitive game board with distinct colors for up to 4 players.
- **Smooth Animations:** Player tokens glide smoothly across the board for a polished user experience.
- **Complete Game Loop:** A "Play Again" option allows for continuous gameplay without restarting the application.
- **Clean Architecture:** Built with the industry-standard Model-View-Controller (MVC) pattern to separate game logic from the user interface, making the code easy to read, maintain, and extend.

---

## 🛠️ Tech Stack

- **Language:** Java 17+
- **Framework:** JavaFX 17+ (for the graphical user interface)
- **Build Tool:** Maven (for managing dependencies and building the project)

---

## 🏛️ Architecture: Model-View-Controller (MVC)

This project is built using the MVC design pattern to ensure a clean separation of concerns.

- **Model (`model` package):** The "brain" of the application. It contains all the game's data (player positions, turn state, AI status) and rules (dice rolls, win conditions, snake/ladder locations). It has no knowledge of the user interface.
- **View (`view` package):** The "face" of the application. It is responsible for everything the user sees, including the start screen, the game board, player tokens, the visual die, and buttons. It simply displays the data provided by the Model.
- **Controller (`controller` package):** The "bridge" between the Model and the View. It listens for user input (like button clicks), tells the Model to update its state, and then tells the View how to reflect those changes. It also contains the logic for triggering the AI's turn.

---

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- **Java Development Kit (JDK):** Version 17 or newer.
- **JavaFX SDK:** Version 17 or newer. You can download it from the [GluonHQ website](https://gluonhq.com/products/javafx/). Make sure to unzip it to a known location on your computer.
- **An IDE:** IntelliJ IDEA is highly recommended.

### Setup and Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/exceptional007/Snake---Ladder-JavaFX.git
   cd "Snake and Ladder"
   ```
2. **Open the project in IntelliJ IDEA:**
   - Open IntelliJ and select `File -> Open...`.
   - Navigate to the cloned repository folder and open it.
   - IntelliJ will automatically detect it as a Maven project and set it up.
3. **Configure the JavaFX SDK:**
   - **Add as a Library:** Go to `File -> Project Structure -> Libraries`. Click the + sign, select Java, and navigate to the `lib` folder inside your unzipped JavaFX SDK directory.
   - **Add VM Options:** Go to `Run -> Edit Configurations...`. In the VM options field, add the following line, replacing `/path/to/your/javafx-sdk/lib` with the actual path on your machine:
     ```
     --module-path /path/to/your/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.media
     ```

---

## ▶️ How to Run

1. Navigate to the main application file: `src/main/java/com/example/snakeandladder/Main.java`.
2. Right-click on the `Main.java` file.
3. Select **Run 'Main.main()'**.

The application will launch, presenting you with the start screen to select your game mode.

---

## 🗂️ Project Structure

```
src/
  main/
    java/
      com/example/snakeandladder/
        controller/      # Game logic controllers
        model/           # Game data models
        sound/           # Sound management
        view/            # UI components
    resources/
      com/example/snakeandladder/
        hello-view.fxml  # FXML layout
```

---

## 📸 Screenshots

<!--
Add screenshots here:
<img src="screenshots/gameplay.png" width="600" alt="Gameplay Screenshot">
-->

---

## 💡 Future Improvements

- **Sound Effects:** Add audio feedback for dice rolls, player movement, and landing on snakes or ladders.
- **Enhanced AI:** Improve the computer opponent's logic (though in a game of pure chance, this is more for show).
- **Customizable Board:** Allow users to select different board themes or colors.
- **Network Multiplayer:** Extend the game to allow players to compete over a network.

---

## 🤝 Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements and new features.

---

## 📄 License

This project is licensed under the MIT License.

---

<div align="center">
  <b>Enjoy playing Snake and Ladder! 🐍🎲</b>
</div>
