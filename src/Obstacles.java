// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class creates the obstacles for the game.
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

/**
 * Create a obstacle for the game.
 */
public class Obstacles {
    private Rectangle rect;
    private CanvasWindow canvas;
    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * Create the obstacle for the game.
     */
    public Obstacles(CanvasWindow canvas, double x, double y, double width, double height, Color color) {
        this.canvas = canvas;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(x, y, width, height);
        rect.setFillColor(color);
        rect.setFilled(true);

    }

    public void addToCanvas() {
        canvas.add(rect);
    }

    public Rectangle getRect() {
        return rect;
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
     * It creates the camera's movement to follow the character in the game using the obstacles class.
     * 
     * @param offsetX is a variable that helps follow the character horizontally forward or backward.
     */
    public void setOffsetX(double offsetX) {
        rect.setPosition(x + offsetX, y);
    }
}