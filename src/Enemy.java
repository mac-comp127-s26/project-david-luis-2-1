import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

public class Enemy {
    private Image currentSprite;
    private Image leftSprite;
    private Image rightSprite;
    // private Rectangle rect;
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

    private static final Color ENEMY_COLOR = new Color(180, 50, 50);

    public Enemy(CanvasWindow canvas, double x, double y, double moveLeft, double moveRight) {
        this.canvas = canvas;
        this.x = x;
        this.y = y;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        // this.rect = new Rectangle(x, y, width, height);
        // rect.setFillColor(ENEMY_COLOR);
        // rect.setFilled(true);

        Image spriteLeft = new Image("left_tung.png");
        Image spriteRight = new Image("right_tung.png");

        spriteLeft.setMaxWidth(width);
        spriteLeft.setMaxHeight(height);
        spriteRight.setMaxWidth(width);
        spriteRight.setMaxHeight(height);

        currentSprite = spriteRight;
        currentSprite.setPosition(x, y);


        canvas.animate(dt -> {
            if (!active)
                return;

            this.x += speed * direction * dt;

            if (x >= moveRight) {
                this.x = moveRight;
                direction = -1;
                updateSprite(spriteLeft);
            }
            if (x <= moveLeft) {
                this.x = moveLeft;
                direction = 1;
                updateSprite(spriteRight);
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

}
    
