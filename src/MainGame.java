import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class MainGame {
    private static final int CANVAS_HEIGHT = 600;
    private static final int CANVAS_WIDTH = 1700;
    private static final int LEVEL_WIDTH = 4300;
    private Character character;
    private Background background;
    private CanvasWindow canvasWindow;
    private List<Obstacles> obstacles = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private double cameraX = 0;
    private EnemyLogic enemyLogic;
    private FinishLine finishLine;

    public MainGame() {
        canvasWindow = new CanvasWindow("Carti Platformer", CANVAS_WIDTH, CANVAS_HEIGHT);
        background = new Background(canvasWindow);

        Level level = new Level(canvasWindow, obstacles, enemies);

        for (Obstacles obstacle : obstacles) {
            obstacle.addToCanvas();
        }

        for (Enemy ememy : enemies) {
            ememy.addToCanvas();
        }

        finishLine = new FinishLine(canvasWindow, background);
        character = new Character(canvasWindow, obstacles,finishLine);
        character.addToCanvas(canvasWindow);

        enemyLogic = new EnemyLogic(canvasWindow, character, background, enemies);

        canvasWindow.animate(dt -> {
            double targetCameraX = character.getNaturalX() - 300;
              targetCameraX = Math.max(0, targetCameraX);
            targetCameraX = Math.min(LEVEL_WIDTH - CANVAS_WIDTH, targetCameraX);
            cameraX = targetCameraX;
            character.setCameraX(targetCameraX);
            background.setOffsetX(-cameraX);

        for (Obstacles obs : obstacles){
            obs.setOffsetX(-cameraX);
        }
        for (Enemy enm : enemies){
            enm.setOffsetX(-cameraX);
        }
        finishLine.setOffsetX(-cameraX);
        finishLine.checkFlagCollision(character);
        });


    }

    public static void main(String[] args) {
        new MainGame();
    }


}