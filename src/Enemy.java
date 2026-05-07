// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class creates the enemy for the game.
import java.util.List;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

/**
 * Creates a enemy for the game.
 */
public class Enemy {
    private Image currentSprite;
    private CanvasWindow canvas;
    private double x;
    private double y;
    private double width = 80;
    private double height = 110;
    private double speed = 120;
    private double direction = 1;
    private boolean active = true;

    /**
     * Creates the enemy and it's animation movement for the game
     * 
     * @param canvas    is a variable that creates the canvas for the game.
     * @param x         is a variable that creates the X-Coordinate for the enemy's position in game.
     * @param y         is a variable that creates the Y-Coordinate for the enemy's position in game.
     * @param moveLeft  is the variable that tell how far the individual enemy moves left in the game.
     * @param moveRight is the variable is the variable that tell how far the individual enemy moves
     *                  right in the game.
     * @param obstacles is the varibale that uses/interacts the obstacles in the game.
     */
    public Enemy(CanvasWindow canvas, double x, double y, double moveLeft, double moveRight,
        List<Obstacles> obstacles) {
        this.canvas = canvas;
        this.x = x;
        this.y = y;

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

    /**
     * It provides the updated sprite and it's animation for the enemy whenever it turns right or it
     * turns left in the game
     * 
     * @param newSprite is a variable to that holds the new sprite for the enemy in the game.
     */
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

    /**
     * It removes the current Sprite that is on the enemy whenever it makes a new turn.
     */
    public void remove() {
        active = false;
        canvas.remove(currentSprite);
    }

    public boolean isActive() {
        return active;
    }

    /**
     * It creates the camera's movement to follow the character in the game using the Enemy class
     * 
     * @param offsetX is a variable that helps follow the character horizontally forward or backward.
     */
    public void setOffsetX(double offsetX) {
        currentSprite.setPosition(this.x + offsetX, this.y);
    }

}
