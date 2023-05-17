package com.example.demo1;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class ColisionHandler {





    public boolean collisionDetection(ArrayList<Rectangle> obstacles , ImageView bird){
        for (Rectangle rectangle: obstacles) {
            if(rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())){
                return true;
            }
        }
        return  false;
    }
}