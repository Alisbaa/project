package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private ImageView bird;

    @FXML
    private Text score;
    @FXML
    private Text over;
    @FXML
    private Text finalscore;


    private double accelerationTime = 0;
    private int gameTime = 0;
    private int scoreCounter = 0;

    private Bird birdComponent;
    private ObstaclesHandler obstaclesHandler;
    @FXML
private Button playagain;

    ArrayList<Rectangle> obstacles = new ArrayList<>();

    @FXML
    private void OnMouseClicked(MouseEvent mouseEvent){

        resetGame();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int jumpHeight = 75;
        birdComponent = new Bird(bird, jumpHeight);
        double planeHeight = 1000;
        double planeWidth = 1300;
        obstaclesHandler = new ObstaclesHandler(plane, planeHeight, planeWidth);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };

        load();

        gameLoop.start();
    }

    @FXML
    void pressed(KeyEvent event) {
        if(event.getCode() == KeyCode.SPACE){
            birdComponent.fly();
            accelerationTime = 0;
        }
    }



    private void update() {
        finalscore.setVisible(false);
        gameTime++;
        accelerationTime++;
        double yDelta = 0.02;
        birdComponent.moveBirdY(yDelta * accelerationTime);

        if(pointChecker(obstacles, bird)){
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }
        obstaclesHandler.moveObstacles(obstacles);
        if(gameTime % 200 == 0){
            obstacles.addAll(obstaclesHandler.createObstacles());
        }

        if(birdComponent.isBirdDead(obstacles, plane)){
            GameOver();


        }

    }


    private void load(){
        obstacles.addAll(obstaclesHandler.createObstacles());
    }
public void GameOver(){
    over.setText("GAME OVER!");
    over.setFill(Color.RED);
    plane.getChildren().removeAll(obstacles);
    obstacles.clear();
    playagain.setLayoutY(500);
    playagain.setLayoutX(650);
    finalscore.setVisible(true);
    finalscore.setText(String.valueOf("Your score: "+scoreCounter));
    plane.getChildren().remove(score);

}
@FXML
    private void resetGame(){
        plane.getChildren().remove(finalscore);
        plane.getChildren().remove(over);
        plane.getChildren().remove(playagain);
        bird.setY(0);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        accelerationTime = 0;
        scoreCounter = 0;
        score.setText(String.valueOf(scoreCounter));
        load();




    }









    private boolean pointChecker(ArrayList<Rectangle> obstacles, ImageView bird){
        for (Rectangle obstacle: obstacles) {
            int birdPositionX = (int) (bird.getLayoutX() + bird.getX());
            if(((int)(obstacle.getLayoutX() + obstacle.getX()) == birdPositionX)){
                return true;
            }
        }
        return false;
    }



}