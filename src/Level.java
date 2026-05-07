// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class provides the enemy logic for the enemies in the game.
import java.awt.Color;
import java.util.List;
import edu.macalester.graphics.CanvasWindow;

/**
 * Generates a level.
 */
public class Level {
    private static final int GROUND = 500;
    private static final int ENEMY_Y = GROUND - 85;
    private static final Color BRICK = new Color(180, 100, 40);
    private static final Color PIPE = new Color(50, 180, 50);
    private static final Color PIPE_TOP = new Color(30, 160, 30);

    private List<Obstacles> obstacles;
    private List<Enemy> enemies;
    private CanvasWindow canvas;

    /**
     * Creates the level that would contain the aspects for the game.
     * 
     * @param canvas    is a variable that creates the canvas for the game.
     * @param obstacles is a variable that creates the acutal obstacles for the game.
     * @param enemies   is a variable that creates the actual enemies for the game.
     */
    public Level(CanvasWindow canvas, List<Obstacles> obstacles, List<Enemy> enemies) {
        this.canvas = canvas;
        this.obstacles = obstacles;
        this.enemies = enemies;
        buildLevel();
    }

    /**
     * It add the elements like the brick,pipe and enemies to the level for the game.
     */
    private void buildLevel() {
        pipe(250, GROUND - 60, 60);
        brick(380, GROUND - 160, 120, 20);
        enemy(310, ENEMY_Y, 260, 370);

        pipe(680, GROUND - 60, 60);
        brick(820, GROUND - 180, 120, 20);
        enemy(750, ENEMY_Y, 690, 810);

        pipe(1150, GROUND - 70, 70);
        brick(1300, GROUND - 60, 60, 20);
        brick(1360, GROUND - 120, 60, 20);
        brick(1420, GROUND - 180, 60, 20);
        brick(1480, GROUND - 240, 60, 20);

        pipe(1650, GROUND - 60, 60);
        pipe(1900, GROUND - 70, 70);
        brick(1780, GROUND - 180, 100, 20);

        enemy(1850, ENEMY_Y, 1790, 1890);
        pipe(2080, GROUND - 60, 60);
        brick(2220, GROUND - 180, 120, 20);

        pipe(2400, GROUND - 70, 70);
        enemy(2320, ENEMY_Y, 2260, 2390);
        pipe(2550, GROUND - 60, 60);


        brick(2660, GROUND - 60, 60, 20);
        brick(2720, GROUND - 120, 60, 20);
        brick(2780, GROUND - 180, 60, 20);
        brick(2840, GROUND - 240, 60, 20);

        enemy(2760, ENEMY_Y, 2700, 2840);

        pipe(2980, GROUND - 60, 60);
        brick(3100, GROUND - 160, 120, 20);
        brick(3200, GROUND - 240, 120, 20);

        pipe(3300, GROUND - 70, 70);

        brick(3420, GROUND - 60, 60, 20);
        brick(3480, GROUND - 120, 60, 20);
        brick(3540, GROUND - 180, 60, 20);
        brick(3600, GROUND - 240, 60, 20);

        enemy(3500, ENEMY_Y, 3440, 3600);
        pipe(3680, GROUND - 60, 60);
    }

    /**
     * It generates the pipes's position and characterics in the game
     * 
     * @param x      is the variable where the pipe position is on the X-Coordinate of the canvas
     * @param y      is the variable where the pipe position is on the Y-Coordinate of the canvas
     * @param height is the variable that tells how tall is the pipe for the game
     */
    private void pipe(double x, double y, double height) {
        Obstacles body = new Obstacles(canvas, x, y, 60, height, PIPE);
        obstacles.add(body);
        Obstacles top = new Obstacles(canvas, x, y - 15, 60, 15, PIPE_TOP);
        obstacles.add(top);
    }

    /**
     * It generates the brick's position and characterics in the game
     * 
     * @param x      is the variable where the brick position is on the X-Coordinate of the canvas.
     * @param y      is the variable where the brick position is on the Y-Coordinate of the canvas.
     * @param width  is the variable that tells how wide is the brick for the game level.
     * @param height is the variable that tells how tall is the brick for the game level.
     */
    private void brick(double x, double y, double width, double height) {
        Obstacles obs = new Obstacles(canvas, x, y, width, height, BRICK);
        obstacles.add(obs);
    }

    /**
     * It generates the enemies's position and movement in the game.
     * 
     * @param x         is the variable where the enemy position is on the X-Coordinate of the canvas.
     * @param y         is the variable where the enemy position is on the Y-Coordinate of the canvas.
     * @param moveLeft  is the variable that tell how far the enemy moves left in the game.
     * @param moveRight is the variable that tell how far the enemy moves right in the game.
     */
    private void enemy(double x, double y, double moveLeft, double moveRight) {
        Enemy e = new Enemy(canvas, x, y, moveLeft, moveRight, obstacles);
        enemies.add(e);
    }
}