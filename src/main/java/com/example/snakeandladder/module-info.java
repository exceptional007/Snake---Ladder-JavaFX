module com.example.snakeandladder {
    // Grant access to JavaFX graphics components
    requires javafx.controls;
    // Additional
    requires javafx.fxml;
    requires javafx.web;

   // requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;

    // Grant access to JavaFX media components for sound
    requires javafx.media;

   // opens com.example.snakeandladder to javafx.fxml;

    // Make our main package available to JavaFX to launch the app
    exports com.example.snakeandladder;
    exports com.example.snakeandladder.controller;
    exports com.example.snakeandladder.model;
    exports com.example.snakeandladder.view;

    // EXPORTS THE NEW SOUND PACKAGE
   // exports com.example.snakeandladder.sound;
}