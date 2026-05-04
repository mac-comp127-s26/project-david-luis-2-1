
import java.util.List;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.events.Key;

public class Character {

    private Image currentCharacter;
    private Image walkRight;
    private Image walkLeft;
    private Image jump;
    private CanvasWindow canvas;
    private double x = 30;
    private double y = 373;
    private double rateX = 0;
    private double rateY = 0;
    private boolean onGround = true;
    private boolean leftMovement = false;
    private boolean rightMovement = false;
    private List<Obstacles> obstacles;

    private static final double JUMP_POWER = -400;
    private static final double GRAVITY = 800;
    private static final double FlOOR_Y = 385;
    private static final double MOVE_SPEED = 180;


    public Character(CanvasWindow canvas, List<Obstacles> obstacles) {
        this.obstacles = obstacles;
        this.canvas = canvas;

        walkLeft = new Image("walk_left_frame1.png");
        walkRight = new Image("walk_right_frame1.png");
        jump = new Image ("jump.png");

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

            if (x < 0){
                x = 0;
            }
            if (x > 1800){
                x = 1800;
            }

            updateCharacter();
            currentCharacter.setPosition(300, y);
        });
    }

    public void updateCharacter(){
        Image newCharacter;
        if (!onGround){
            newCharacter = jump;
        } else if (leftMovement){
            newCharacter = walkLeft;
        } else{
            newCharacter = walkRight;
        }

        if (newCharacter != currentCharacter){
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

    public Image getCharacter(){
        return currentCharacter;
    }

    public double getNaturalX(){
        return x;
    }

    public void checkCollision(CanvasWindow canvas, List<Obstacles> obstacles) {
        GraphicsObject top = canvas.getElementAt(x + 40, y);
        GraphicsObject bottom = canvas.getElementAt(x + 40, y + 127);
        GraphicsObject left = canvas.getElementAt(x + 10, y + 64);
        GraphicsObject right = canvas.getElementAt(x + 70, y + 64);

        for (Obstacles obst : obstacles) {
            if (bottom == obst.getRect() && rateY >= 0) {
                y = obst.getY() - 127;
                rateY = 0;
                onGround = true;
            }
            if (right == obst.getRect()) {
                x = obst.getX() - 80;
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