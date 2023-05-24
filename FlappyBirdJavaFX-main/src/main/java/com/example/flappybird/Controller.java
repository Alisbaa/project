package com.example.flappybird;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    
    AnimationTimer gameLoop;
    
  
    @FXML
    private Text over;
    @FXML
    private Text finalscore;


    @FXML
    private AnchorPane plane;

    @FXML
    private Rectangle bird;

    @FXML
    private Text score;

    private double accelerationTime = 0;
    private int gameTime = 0;
    private int scoreCounter = 0;

    Application app = new Application();
    

    public static double obstacleAcceleration = 0;
    private Bird birdComponent;
    private ObstaclesHandler obstaclesHandler;


    @FXML
    private Button playagain;


    ArrayList<Rectangle> obstacles = new ArrayList<>();
    
    
   
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        int jumpHeight = 75;
        birdComponent = new Bird(bird, jumpHeight);
        double planeHeight = 1000; //600
        double planeWidth = 1000; //400
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


    //Called every game frame
    private void update() {
        playagain.setVisible(false);
        finalscore.setVisible(false);
        gameTime++;
        accelerationTime++;
        double yDelta = 0.02;
        birdComponent.moveBirdY(yDelta * accelerationTime);

        if(pointChecker(obstacles, bird)){
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
            obstacleAcceleration+=0.2;
        }

        obstaclesHandler.moveObstacles(obstacles);
        if(gameTime % 500 == 0){
            obstacles.addAll(obstaclesHandler.createObstacles());
        }

        if(birdComponent.isBirdDead(obstacles, plane)){
           //GameOver();
          
          gameLoop.stop();
          resetGame();
          app.ShowBaseWindow();
        }
        
    }

    //Everything called once, at the game start
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
        playagain.setVisible(true);
        finalscore.setVisible(true);
        finalscore.setText(String.valueOf("Your score: "+scoreCounter));
        plane.getChildren().remove(score);
       
    }

    public void resetGame(){
        //plane.getChildren().remove(finalscore);
      //  plane.getChildren().remove(over);
       // plane.getChildren().remove(playagain);
        bird.setY(0);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        accelerationTime = 0;
        scoreCounter = 0;
        obstacleAcceleration = 0;
        score.setText(String.valueOf(scoreCounter));
        
    }

    public void reset(){
        resetGame();
   
        
    }


    private boolean pointChecker(ArrayList<Rectangle> obstacles, Rectangle bird){
        for (Rectangle obstacle: obstacles) {
            int birdPositionX = (int) (bird.getLayoutX() + bird.getX());
            if(((int)(obstacle.getLayoutX() + obstacle.getX()) == birdPositionX)){
                return true;
            }
        }
        return false;
    }
}
