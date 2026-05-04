import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class MainGame {
    private static final int CANVAS_HEIGHT = 600;
    private static final int CANVAS_WIDTH = 2000;
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

        Obstacles firstPlatform = new Obstacles(canvasWindow, 150, 350, 120, 20, Color.ORANGE);
        Obstacles secondPlatform = new Obstacles(canvasWindow, 380, 280, 120, 20, Color.ORANGE);
        Obstacles firstWall = new Obstacles(canvasWindow, 300, 420, 20, 80, Color.GRAY);
        Obstacles secondWall = new Obstacles(canvasWindow, 500, 420, 20, 80, Color.GRAY);


        obstacles.add(firstPlatform);
        obstacles.add(secondPlatform);
        obstacles.add(firstWall);
        obstacles.add(secondWall);

        for (Obstacles obstacle : obstacles) {
            obstacle.addToCanvas();
        }

        Enemy firstEnemy = new Enemy(canvasWindow, 200, 440, 150, 350);
        Enemy secondEnemy = new Enemy(canvasWindow, 450, 440, 380, 550);

        enemies.add(firstEnemy);
        enemies.add(secondEnemy);

        for (Enemy ememy : enemies) {
            ememy.addToCanvas();


        finishLine = new FinishLine(canvasWindow, background);

        canvasWindow.animate(dt -> {
            double targetCameraX = character.getNaturalX() - 300;
            cameraX = targetCameraX;
            background.setOffsetX(-cameraX);

        for (Obstacles obs : obstacles){
            obs.setOffsetX(-cameraX);
        }
        for (Enemy enm : enemies){
            enm.setOffsetX(-cameraX);
        }
        finishLine.checkFlagCollision(character);
        });
        }


        character = new Character(canvasWindow, obstacles);
        character.addToCanvas(canvasWindow);


        enemyLogic = new EnemyLogic(canvasWindow, character, background, enemies);


    }

    public static void main(String[] args) {
        new MainGame();
    }

    private void levelComplete() {
        int bonus = background.getTime() * 50;

    }



}