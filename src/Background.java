
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

public class Background{
    private static final int GROUND_HEIGHT = 100;
    public static final Color SKY_COLOR = new Color(188, 217, 255);
    public static final Color GROUND_COLOR = new Color(122, 181, 107);
    private CanvasWindow canvas;

    private int score = 0;
    private int time = 230;
    private int lives = 3;

    private GraphicsText scoreText;
    private GraphicsText timeText;
    private GraphicsText livesText;

    public Background(CanvasWindow canvas){
        this.canvas = canvas;
        createBackground();
        createHUD();
        runTimer();
    }
   
    private void createHUD() {
        scoreText = new GraphicsText("Score: " + score, 10,25);
        scoreText.setFontSize(20);
        canvas.add(scoreText);

        timeText = new GraphicsText("Time: " + time, canvas.getWidth() / 2 - 40, 25);
        timeText.setFontSize(20);
        canvas.add(timeText);

        livesText = new GraphicsText("Lives x" + lives, canvas.getWidth() - 110,25);
        livesText.setFontSize(20);
        canvas.add(livesText);
    }

    private void runTimer() {
        final double [] elapsed = {0};
        canvas.animate ((dt) -> {
            elapsed[0] += dt;
            if (elapsed [0] >= 1.0 && time > 0){
                time--;
                elapsed[0] = 0;
                timeText.setText("Time: " + time);
            }
        });
    }

    public void updateScore(int newScore) {
        score = newScore;
        scoreText.setText("Score: " + score);
    }

    public void updateLives (int newLives){
        lives = newLives;
        livesText.setText("Lives x" + lives);
    }

    public void createBackground() {
        Rectangle sky = new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight());
        sky.setFillColor(SKY_COLOR);
        sky.setFilled(true);
        canvas.add(sky);

        Rectangle ground = new Rectangle(
            0, canvas.getHeight() - GROUND_HEIGHT,
            canvas.getWidth(), GROUND_HEIGHT);
        ground.setFilled(true);
        ground.setFillColor(GROUND_COLOR);
        ground.setStroked(false);
        canvas.add(ground);
    }

    public int getTime(){
        return time;
    }
}