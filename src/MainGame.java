// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class creates the game of Carti 2D: TUNG MADNESS.
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.CanvasWindow;

/**
 * The game of Carti 2D: TUNG MADNESS.
 */
public class MainGame {
    private static final int CANVAS_HEIGHT = 600;
    private static final int CANVAS_WIDTH = 1750;
    private static final int LEVEL_WIDTH = 4250;
    private Character character;
    private Background background;
    private CanvasWindow canvasWindow;
    private List<Obstacles> obstacles = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private double cameraX = 0;
    private FinishLine finishLine;

    /**
     * Creates the game itself and the animation for the camera movement as well.
     */
    public MainGame() {
        canvasWindow = new CanvasWindow("Carti 2D: TUNG MADNESS", CANVAS_WIDTH, CANVAS_HEIGHT);
        background = new Background(canvasWindow);
        Level level = new Level(canvasWindow, obstacles, enemies); // Note: Need this line in order to make the level
                                                                   // for the game

        for (Obstacles obstacle : obstacles) {
            obstacle.addToCanvas();
        }

        for (Enemy ememy : enemies) {
            ememy.addToCanvas();
        }

        finishLine = new FinishLine(canvasWindow, background);
        character = new Character(canvasWindow, obstacles, finishLine);
        character.addToCanvas(canvasWindow);

        new EnemyLogic(canvasWindow, character, background, enemies, null);

        canvasWindow.animate(dt -> {
            double targetCameraX = character.getNaturalX() - 300;
            targetCameraX = Math.max(0, targetCameraX);
            targetCameraX = Math.min(LEVEL_WIDTH - CANVAS_WIDTH, targetCameraX);
            cameraX = targetCameraX;
            character.setCameraX(targetCameraX);
            background.setOffsetX(-cameraX);

            for (Obstacles obs : obstacles) {
                obs.setOffsetX(-cameraX);
            }
            for (Enemy enm : enemies) {
                enm.setOffsetX(-cameraX);
            }
            finishLine.setOffsetX(-cameraX);
            finishLine.checkFlagCollision(character);
        });
    }

    /**
     * It adds the animation where the enemies stay whenever the character touches it sideways.
     */
    private void setupGame() {
        canvasWindow.removeAll();
        obstacles.clear();
        enemies.clear();
        cameraX = 0;
        background = new Background(canvasWindow);

        for (Obstacles obstacle : obstacles) {
            obstacle.addToCanvas();
        }

        for (Enemy ememy : enemies) {
            ememy.addToCanvas();
        }

        finishLine = new FinishLine(canvasWindow, background);
        character = new Character(canvasWindow, obstacles, finishLine);
        character.addToCanvas(canvasWindow);

        new EnemyLogic(canvasWindow, character, background, enemies, () -> setupGame());
    }

    public static void main(String[] args) {
        new MainGame();
    }
}