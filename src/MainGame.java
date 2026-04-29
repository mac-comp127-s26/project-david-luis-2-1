 import java.util.ResourceBundle.Control;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
public class MainGame {
  
     private CanvasWindow canvas;
     private int Lives = 3;

     private void runningGame(){
          if (Lives > 0){
               runningGame();
          }
          else{
               resetGame();
          }
     }

     private void resetGame(){
          if (Lives == 0){
               System.out.println("Game is over");
               resetGame();
          }
     }
     private void createBackground(){
          createBackground();
     }



}

/*
Methods:
runGame
ResetGame
Movement
Jump 
Crouch


*/
// runGame


