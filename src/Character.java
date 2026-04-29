
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Character {
   private Rectangle Rectangle;
   private CanvasWindow canvas;


    public Character(CanvasWindow canvas) {
      this.canvas = canvas;
      this.Rectangle = new Rectangle(30, 440, 40, 60);
    }

   public void addToCanvas(CanvasWindow canvasWindow) {
        canvasWindow.add(Rectangle);
    }

    public Rectangle getRectangle() {
        return Rectangle;
    } 
    
} 



