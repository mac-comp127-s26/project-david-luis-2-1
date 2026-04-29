 import java.util.ResourceBundle.Control;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

public class MainGame {
     private int Lives = 3;

     private void runGame(){
          if (Lives > 0){
               runGame();
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






