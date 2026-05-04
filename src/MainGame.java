import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class MainGame {
    private static final int CANVAS_HEIGHT = 600;
    private static final int CANVAS_WIDTH = 2000;
    private Character character;
    private Background background;
    private CanvasWindow canvasWindow;
    private List<Obstacles> obstacles = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private EnemyLogic enemyLogic;

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