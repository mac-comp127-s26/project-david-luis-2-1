
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
public class Character {
   private Rectangle Rectangle;
   private CanvasWindow canvas;
   private double x = 30;
   private double y = 440;
   private double speed = 5;
   private double rateY = 0;
   private boolean onGround = true;
   private static final double JUMP_POWER = -10;

    public Character(CanvasWindow canvas) {
      this.canvas = canvas;
      this.Rectangle = new Rectangle(x, y, 40, 60);
        
      canvas.onKeyDown(key ->{
        if (key.getKey() == Key.LEFT_ARROW){
            x -= speed;
        }
        if (key.getKey()== Key.RIGHT_ARROW){
            x += speed;
        }
        if (key.getKey() == Key.UP_ARROW && onGround){
            rateY = JUMP_POWER;
            onGround = false;
        }
        if (key.getKey() == Key.DOWN_ARROW){
            y += speed;
        }
       Rectangle.setPosition(x, y);
      });
       canvas.animate(dt -> {
            rateY += 0.5;
            y += rateY;

            if (y >= 440){
                y = 440;
                rateY = 0;
                onGround = true;
            }

        });
        Rectangle.setPosition(x, y);  
    }

   public void addToCanvas(CanvasWindow canvasWindow) {
        canvasWindow.add(Rectangle);
    }

    public Rectangle getRectangle() {
        return Rectangle;
    } 
} 