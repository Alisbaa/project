package com.example.flappybird;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderGame = new FXMLLoader(Application.class.getResource("view.fxml"));
        
        Scene sceneGame = new Scene(fxmlLoaderGame.load());
       

        sceneGame.getRoot().requestFocus();
        sceneGame.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
       
        stage.setTitle("Flappy Bird!");
        stage.setScene(sceneGame);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}