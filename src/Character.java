// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class creates the character for the game.
import java.util.List;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

/**
 * Creates a character for the game
 */
public class Character {
    private static final double JUMP_POWER = -450;
    private static final double GRAVITY = 800;
    private static final double FlOOR_Y = 385;
    private static final double MOVE_SPEED = 180;

    private Image currentCharacter;
    private Image walkRight;
    private Image walkLeft;
    private Image jump;
    private CanvasWindow canvas;
    private double x = 30;
    private double y = 385;
    private double rateX = 0;
    private double rateY = 0;
    private double cameraX = 0;
    private boolean onGround = true;
    private boolean leftMovement = false;
    private boolean rightMovement = false;

    /**
     * Creates the character for the game itself and it's animation movement.
     * 
     * @param canvas     is a variable that creates the canvas for the game.
     * @param obstacles  is the varibale that uses/interacts the obstacles in the game.
     * @param finishLine is the variable that uses/interacts with the finish line of the ending of the
     *                   level.
     */
    public Character(CanvasWindow canvas, List<Obstacles> obstacles, FinishLine finishLine) {
        this.canvas = canvas;
        walkLeft = new Image("walk_right_frame1.png");
        walkRight = new Image("walk_left_frame1.png");
        jump = new Image("jump.png");
        currentCharacter = walkRight;
        currentCharacter.setPosition(x, y);

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
            if (finishLine.gameWin())
                return;
            if (leftMovement) {
                rateX = -MOVE_SPEED;
            } else if (rightMovement) {
                rateX = MOVE_SPEED;
            } else {
                rateX = 0;
            }
            rateY += GRAVITY * dt;
            y += rateY * dt;
            x += rateX * dt;
            onGround = false;
            checkCollision(canvas, obstacles);
            if (!onGround && y >= FlOOR_Y) {
                y = FlOOR_Y;
                rateY = 0;
                onGround = true;
            }
            if (x < 0) {
                x = 0;
            }
            if (x > 4400) {
                x = 4400;
            }
            updateCharacter();
            currentCharacter.setPosition(x - cameraX, y);
        });
    }

    /**
     * It updates the image of the character whenever it jumps, move left or move right.
     */
    public void updateCharacter() {
        Image newCharacter;
        if (!onGround) {
            newCharacter = jump;
        } else if (leftMovement) {
            newCharacter = walkLeft;
        } else {
            newCharacter = walkRight;
        }

        if (newCharacter != currentCharacter) {
            canvas.remove(currentCharacter);
            canvas.add(newCharacter);
            currentCharacter = newCharacter;
        }

    }

    public void addToCanvas(CanvasWindow canvasWindow) {
        canvasWindow.add(currentCharacter);
    }

    public double getX() {
        return x + 10;
    }

    public double getY() {
        return y;
    }

    public Image getCharacter() {
        return currentCharacter;
    }

    public double getNaturalX() {
        return x;
    }

    /**
     * It sets the camera of the character to start along with the character across the canvas.
     * 
     * @param cameraX is a variable that provides starting X-Coordiante postion for the camera on the
     *                canvas.
     */
    public void setCameraX(double cameraX) {
        this.cameraX = cameraX;
    }

    /**
     * It checks if the character has collided with a obstacles. Then, it eithers stops the character
     * from moving forward, or when the character is jumping on the platform, it provides the landing
     * check and animation to make it seem that it is landing.
     * 
     * @param canvas    is a variable that creates the canvas for the game.
     * @param obstacles is the varibale that uses/interacts the obstacles in the game.
     */
    public void checkCollision(CanvasWindow canvas, List<Obstacles> obstacles) {
        double figureLeft = x + 15;
        double figureRight = x + 65;
        double figureTop = y + 5;
        double figureBottom = y + 127;

        for (Obstacles obst : obstacles) {
            double obsLeft = obst.getX();
            double obsRight = obst.getX() + obst.getWidth();
            double obsTop = obst.getY();
            double obsBottom = obst.getY() + obst.getHeight();

            boolean overlapping = figureRight > obsLeft &&
                figureLeft < obsRight &&
                figureBottom > obsTop &&
                figureTop < obsBottom;

            if (!overlapping)
                continue;

            double overLapTop = figureBottom - obsTop;
            double overLapBottom = obsBottom - figureTop;
            double overLapLeft = figureRight - obsLeft;
            double overLapRight = obsRight - figureLeft;

            if (overLapTop <= 40 && rateY >= 0) {
                y = obsTop - 115;
                rateY = 0;
                onGround = true;
                continue;
            }
            if (overLapBottom <= 20 && rateY < 0) {
                y = obsBottom - 5;
                rateY = 0;
                continue;
            }

            double minOverLap = Math.min(Math.min(overLapTop, overLapBottom), Math.min(overLapLeft, overLapRight));

            if (minOverLap == overLapLeft && rateX > 0) {
                x = obsLeft - 65;
                rateX = 0;
            } else if (minOverLap == overLapRight && rateX < 0) {
                x = obsRight - 15;
                rateX = 0;
            }
        }
    }
}