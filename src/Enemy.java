import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;


public class Enemy {
    private Image currentSprite;
    private Image leftSprite;
    private Image rightSprite;
   
    private CanvasWindow canvas;
    private double x;
    private double y;
    private double width = 80;
    private double height = 110;
    private double speed = 120;
    private double direction = 1;
    private double moveLeft;
    private double moveRight;
    private boolean active = true;
    private List<Obstacles> obstacles;

    private static final Color ENEMY_COLOR = new Color(180, 50, 50);

    public Enemy(CanvasWindow canvas, double x, double y, double moveLeft, double moveRight, List<Obstacles> obstacles) {
        this.obstacles = obstacles;
        this.canvas = canvas;
        this.x = x;
        this.y = y;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;


        Image spriteLeft = new Image("right_tung.png");
        Image spriteRight = new Image("left_tung.png");

        spriteLeft.setMaxWidth(80);
        spriteLeft.setMaxHeight(110);
        spriteRight.setMaxWidth(80);
        spriteRight.setMaxHeight(110);

        currentSprite = spriteRight;
        currentSprite.setPosition(x, y);


        canvas.animate(dt -> {
            if (!active)
                return;

            this.x += speed * direction * dt;

        for (Obstacles obst : obstacles) {
        double obsLeft = obst.getX();
        double obsRight = obst.getX() + obst.getWidth();
        double obsTop = obst.getY();
        double obsBottom = obst.getY() + obst.getHeight();

        
        
       boolean hittingRight = direction == 1 &&
                               this.x + width >= obsLeft &&
                               this.x + width <= obsLeft + 10 &&
                               this.y + height > obsTop &&
                               this.y < obsBottom;

          boolean hittingLeft = direction == -1 &&
                              this.x <= obsRight &&
                              this.x >= obsRight - 10 &&
                              this.y + height > obsTop &&
                              this.y < obsBottom;
    if (hittingRight) {
            this.x = obsLeft - width;
            direction = -1;
            updateSprite(spriteLeft);
        } else if (hittingLeft) {
            this.x = obsRight;
            direction = 1;
            updateSprite(spriteRight);
        }
    }


            currentSprite.setPosition(this.x, this.y);
        });
    }

    private void updateSprite(Image newSprite) {
        if (newSprite != currentSprite) {
            canvas.remove(currentSprite);
            canvas.add(newSprite);
            currentSprite = newSprite;
        }
    }

    public void addToCanvas() {
        canvas.add(currentSprite);
    }

    public Image getSprite() {
        return currentSprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void remove() {
        active = false;
        canvas.remove(currentSprite);
    }

    public boolean isActive() {
        return active;
    }
    public void setOffsetX(double offsetX){
        currentSprite.setPosition(this.x + offsetX, this.y);
    }

}
    