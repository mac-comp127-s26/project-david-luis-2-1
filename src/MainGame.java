 import java.util.ResourceBundle.Control;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
public class MainGame {
     private CanvasWindow canvasWindow;

     CanvasWindow canvas = new CanvasWindow("MyPlatformer", 800, 600);
     Background bg = new Background(canvas);
     createBackground();

     // private int Lives = 3;

     // private void runGame(){
     //      if (Lives > 0){
     //           runGame();
     //      }
     //      else{
     //           resetGame();
     //      }
     // }

     private void resetGame(){
          if (Lives == 0){
               System.out.println("Game is over");
               resetGame();
          }
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



}
