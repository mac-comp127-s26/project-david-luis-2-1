// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class creates the background for the game.
import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

/**
 * Generates a background for the game
 */
public class Background {
    private static final int GROUND_HEIGHT = 100;
    public static final Color SKY_COLOR = new Color(188, 217, 255);
    public static final Color GROUND_COLOR = new Color(122, 181, 107);
    private Rectangle sky;
    private Rectangle ground;
    private CanvasWindow canvas;
    private GraphicsText scoreText;
    private GraphicsText timeText;
    private GraphicsText livesText;
    private int score = 0;
    private int time = 230;
    private int lives = 3;

    /**
     * It stops the timer whenever the character finishes the level.
     */
    public void stopTimer() {
        time = 0;
        timeText.setText("Time: " + time);
    }

    /**
     * It intializes the background with HUD for the life, score, and timer systems to appear on the
     * game.
     * 
     * @param canvas is a variable that creates the canvas for the game
     */
    public Background(CanvasWindow canvas) {
        this.canvas = canvas;
        createBackground();
        createHUD();
        runTimer();
    }

    /**
     * It creates the hud for the score, lives and timer to appear on the game.
     */
    private void createHUD() {
        scoreText = new GraphicsText("Score: " + score, 10, 25);
        scoreText.setFontSize(20);
        canvas.add(scoreText);

        timeText = new GraphicsText("Time: " + time, canvas.getWidth() / 2 - 40, 25);
        timeText.setFontSize(20);
        canvas.add(timeText);

        livesText = new GraphicsText("Lives x" + lives, canvas.getWidth() - 110, 25);
        livesText.setFontSize(20);
        canvas.add(livesText);
    }

    /**
     * It's how the timer it's run and animation of the actual timer being runned. Additionally, It adds
     * whatever time is left to the score once it's over when the character finishes the level.
     */
    private void runTimer() {
        final double[] elapsed = { 0 };
        canvas.animate((dt) -> {
            elapsed[0] += dt;
            if (elapsed[0] >= 1.0 && time > 0) {
                time--;
                elapsed[0] = 0;
                timeText.setText("Time: " + time);
            }
        });
    }

    /**
     * It updates a new score whenever does an action to add to thier score in middle of the level like
     * elimating an enemy as an example. As well, at the end wheneever the character reaches and all the
     * elements like timer adds to it, it updates the entire score for the game.
     * 
     * @param newScore is a variable that gets a new score itself whenever an action occurs to get a new
     *                 score for the game.
     */
    public void updateScore(int newScore) {
        score = newScore;
        scoreText.setText("Score: " + score);
    }

    /**
     * It updates the life whenever the character touches the enemies by the side in which it would
     * decrease a life. In which, it decreases the life counter in the game
     * 
     * @param newLives is a variable that get a new live itself whenever the action occurs to affect a
     *                 life for the character.
     */
    public void updateLives(int newLives) {
        lives = newLives;
        livesText.setText("Lives x" + lives);
    }

    /**
     * It creates the background itself for the game.
     */
    public void createBackground() {
        sky = new Rectangle(0, 0, 4250, canvas.getHeight());
        sky.setFillColor(SKY_COLOR);
        sky.setFilled(true);
        canvas.add(sky);
        ground = new Rectangle(
            0, canvas.getHeight() - GROUND_HEIGHT,
            4250, GROUND_HEIGHT);
        ground.setFilled(true);
        ground.setFillColor(GROUND_COLOR);
        ground.setStroked(false);
        canvas.add(ground);
    }

    public int getTime() {
        return time;
    }

    /**
     * It creates the camera's movement to follow the character in the game using the ackground class
     * 
     * @param offsetX is a variable that helps follow the character horizontally forward or backward.
     */
    public void setOffsetX(double offsetX) {
        sky.setPosition(0, 0);
        ground.setPosition(offsetX, canvas.getHeight() - GROUND_HEIGHT);
    }

}