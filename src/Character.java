import java.security.Key;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

// use as reference: https://stackoverflow.com/questions/18384707/sprite-movement-in-java

public class Character {
   private GraphicsGroup character; 
   private int speed = 10;
   private Point directionX;
   private Point directionY;
   private static final Key UP_ARROW = null;
   private static final Key DOWN_ARROW = null;
   private static final Key RIGHT_ARROW = null;
   private static final Key LEFT_ARROW = null;
   private int GRAVITY = 10;
   

    private void moveRight(){
           int speed = 5;

     }
     private void moveLeft(){

     }
     
     private void jump(){
        double 
     }

     private void crouch(){
     
     }

   private void getKey(){
      if (UP_ARROW != null){
         jump();         
      }
      if (DOWN_ARROW != null){
         crouch();
      }
      if (RIGHT_ARROW != null){
         moveRight();
      }
      if (LEFT_ARROW != null){
         moveLeft();
      }
   }
}

