import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class EnemyLogic {
    private List<Enemy> opponenets;
    private Character character;
    private Background background;
    private CanvasWindow canvas;
    private int lives = 3;
    private int score = 0;
    private boolean hitCooldown = false;
    private double contactTimer = 0;

    private static final double HIT_COOLDOWN_TIME = 1.5;

    public EnemyLogic(CanvasWindow canvas, Character character, Background background, List<Enemy> opponenets) {
        this.canvas = canvas;
        this.character = character;
        this.background = background;
        this.opponenets = opponenets;

        canvas.animate(dt -> {
            if (contactTimer > 0) {
                contactTimer -= dt;
            }
            if (contactTimer < 0) {
                contactTimer = 0;
                hitCooldown = false;
            }
            checkOpponenetCollision();
        });
    }

    private void checkOpponenetCollision() {
        double charLeft   = character.getNaturalX() + 15;
        double charRight  = character.getNaturalX() + 65;
        double charTop    = character.getY() + 40;
        double charBottom = character.getY() + 127;


        for (int i = opponenets.size() - 1; i >= 0; i--) {
            Enemy enemy = opponenets.get(i);
            if (!enemy.isActive())
                continue;
        double enemyLeft   = enemy.getX() + 10;
        double enemyRight  = enemy.getX() + enemy.getWidth() - 10;
        double enemyTop    = enemy.getY() + 10;
        double enemyBottom = enemy.getY() + enemy.getHeight();

        boolean overlapping = charRight  > enemyLeft &&
                              charLeft   < enemyRight &&
                              charBottom > enemyTop &&
                              charTop    < enemyBottom;

        if (!overlapping) continue;

        double overlapTop   = charBottom - enemyTop;
        double overlapLeft  = charRight  - enemyLeft;
        double overlapRight = enemyRight - charLeft;
        if (overlapTop < overlapLeft && overlapTop < overlapRight) {
            enemy.remove();
            score += 100;
            background.updateScore(score);
        } else if (!hitCooldown) {
            enemy.remove();
            loseLife();
            hitCooldown = true;
            contactTimer = HIT_COOLDOWN_TIME;
        }
    }
}

    public void loseLife() {
        if (lives > 0) {
            lives--;
            background.updateLives(lives);
        }
        if (lives == 0) {
            gameOver();
        }
    }

    public void gameOver() {
        System.out.println("Game Over! Final Score: " + score);
        lives = 3;
        score = 0;
        background.updateLives(lives);
        background.updateScore(score);

    }


    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}

