
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

public class Character {
   private Rectangle Rectangle;
   private CanvasWindow canvas;
   private double x = 30;
   private double y = 440;
   private double speed = 5;
   private double rateX = 0;
   private double rateY = 0;
   private boolean onGround = true;
   private boolean leftMovement = false;
   private boolean rightMovement = false;

   private static final double JUMP_POWER = -400;
   private static final double GRAVITY = 800;
   private static final double FlOOR_Y = 439.5;
   private static final double MOVE_SPEED = 180;
   private static final double FRICTION = 0.95;


    public Character(CanvasWindow canvas) {
      this.canvas = canvas;
      this.Rectangle = new Rectangle(x, y, 40, 60);
        
      canvas.onKeyDown(key ->{
        if (key.getKey() == Key.LEFT_ARROW){
            leftMovement = true;
        }
        if (key.getKey()== Key.RIGHT_ARROW){
            rightMovement = true;
        }
        if (key.getKey() == Key.UP_ARROW && onGround){
            rateY = JUMP_POWER;
            onGround = false;
        }

      });

      canvas.onKeyUp(key -> {
        if (key.getKey() == Key.LEFT_ARROW){
            leftMovement = false;
        } 
        if(key.getKey() == Key.RIGHT_ARROW){
            rightMovement = false;
        }
    });

       canvas.animate(dt -> {
        if (leftMovement){
            rateX = -MOVE_SPEED;
        } else if (rightMovement){
            rateX = MOVE_SPEED;
        } else {
            rateX *= FRICTION;
        }
        


            rateY += GRAVITY * dt;
            y += rateY * dt;
            x += rateX * dt;

            if (y >= FlOOR_Y){
                y = FlOOR_Y;
                rateY = 0;
                onGround = true;
            }
            Rectangle.setPosition(x, y);
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