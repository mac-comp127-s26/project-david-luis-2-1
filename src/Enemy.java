import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Enemy {
     private Rectangle rect;
    private CanvasWindow canvas;
    private double x;
    private double y; 
    private double width = 40; 
    private double height = 60;
    private double speed = 100;
    private double direction = 1;
    private double moveLeft;
    private double moveRight;
    private boolean active = true;
    
private static final Color ENEMY_COLOR = new Color(180,50,50);

   public Enemy(CanvasWindow canvas, double x, double y, double moveLeft, double moveRight){
        this.canvas = canvas;
        this.x = x; 
        this. y = y;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.rect = new Rectangle(x, y, width, height);
        rect.setFillColor(ENEMY_COLOR);
        rect.setFilled(true);

    canvas.animate (dt -> {
        if (!active){
            
        }
    }

   }
}
