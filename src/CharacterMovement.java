import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.Key;

public class CharacterMovement {
      private int speed = 10;
    private Point directionX;
    private Point directionY;
    private static final Key UP_ARROW = null;
    private static final Key DOWN_ARROW = null;
    private static final Key RIGHT_ARROW = null;
    private static final Key LEFT_ARROW = null;
     public static final double GRAVITY = -9.8;

    private void moveRight(){
           int speed = 5;


     }
     
     private void moveLeft(){

     }
     
     private void jump(){
        
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
