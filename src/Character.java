import java.security.Key;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

// use as reference: https://stackoverflow.com/questions/18384707/sprite-movement-in-java

public class Character {
   public Rectangle placeHolder = new Rectangle(10, 10, 10, 10);
   private CanvasWindow canvas;
   private double charXCoordinate;
   private double charYCoordinate;

   public void makeCharacter(){
    canvas.add(placeHolder);
   }
   private double getXCoordinate(){
      return charXCoordinate;
   }
   private double getYCoordinate(){
      return charYCoordinate;
   }
   


   

}

