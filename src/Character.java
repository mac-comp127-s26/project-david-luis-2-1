
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
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
    private List<Obstacles> obstacles;

    private static final double JUMP_POWER = -400;
    private static final double GRAVITY = 800;
    private static final double FlOOR_Y = 439.5;
    private static final double MOVE_SPEED = 180;


    public Character(CanvasWindow canvas, List<Obstacles> obstacles) {
        this.obstacles = obstacles;
        this.canvas = canvas;
        this.Rectangle = new Rectangle(x, y, 40, 60);

        canvas.onKeyDown(key -> {
            if (key.getKey() == Key.LEFT_ARROW) {
                leftMovement = true;
            }
            if (key.getKey() == Key.RIGHT_ARROW) {
                rightMovement = true;
            }
            if (key.getKey() == Key.UP_ARROW && onGround) {
                rateY = JUMP_POWER;
                onGround = false;
            }

        });

        canvas.onKeyUp(key -> {
            if (key.getKey() == Key.LEFT_ARROW) {
                leftMovement = false;
            }
            if (key.getKey() == Key.RIGHT_ARROW) {
                rightMovement = false;
            }
        });

        canvas.animate(dt -> {
            if (leftMovement) {
                rateX = -MOVE_SPEED;
            } else if (rightMovement) {
                rateX = MOVE_SPEED;
            } else {
                rateX *= 0;
            }


            rateY += GRAVITY * dt;
            y += rateY * dt;
            x += rateX * dt;

            checkCollision(canvas, obstacles);

            if (y >= FlOOR_Y) {
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void checkCollision(CanvasWindow canvas, List<Obstacles> obstacles) {
        GraphicsObject top = canvas.getElementAt(x + 20, y);
        GraphicsObject bottom = canvas.getElementAt(x + 20, y + 60);
        GraphicsObject left = canvas.getElementAt(x, y + 30);
        GraphicsObject right = canvas.getElementAt(x + 40, y + 30);

        for (Obstacles obst : obstacles) {
            if (bottom == obst.getRect() && rateY >= 0) {
                y = obst.getY() - 60;
                rateY = 0;
                onGround = true;
            }
            if (right == obst.getRect()) {
                x = obst.getX() - 40;
                rateX = 0;
            }
            if (left == obst.getRect()) {
                x = obst.getX() + obst.getWidth();
                rateX = 0;
            }
            if (top == obst.getRect() && rateY < 0) {
                x = obst.getX() + obst.getWidth();
                rateY = 0;
            }
        }
    }
}