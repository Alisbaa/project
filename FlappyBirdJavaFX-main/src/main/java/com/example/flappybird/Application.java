package com.example.flappybird;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.input.MouseEvent;


public class Application extends javafx.application.Application {
    public static Stage stage;
  

    @Override
    public void start(Stage thestage) throws IOException {
        stage = thestage;

    ShowBaseWindow();
}

public void ShowBaseWindow() {

    try {
        FXMLLoader fxmlLoaderMenu = new FXMLLoader(Application.class.getResource("menu.fxml"));
     
        Scene sceneMenu = new Scene(fxmlLoaderMenu.load());

        sceneMenu.getRoot().requestFocus();
     
        
        stage.setTitle("Flappy Bird!");
        stage.setScene(sceneMenu);
        stage.setFullScreen(true);
        stage.show();
    }
    catch (IOException e) {
        e.printStackTrace(); }

}
public void playgame(MouseEvent event) throws IOException {
    FXMLLoader fxmlLoaderGame = new FXMLLoader(Application.class.getResource("view.fxml"));
    Scene sceneGame = new Scene(fxmlLoaderGame.load());
    sceneGame.getRoot().requestFocus();
    sceneGame.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    stage.setScene(sceneGame);
    stage.setFullScreen(true);
    stage.show();
}
    public static void main(String[] args) {
        launch();
    }
    
}