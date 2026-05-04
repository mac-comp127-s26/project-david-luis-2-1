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
        GraphicsObject bottom = canvas.getElementAt(character.getX() + 20, character.getY() + 60);
        GraphicsObject left = canvas.getElementAt(character.getX(), character.getY() + 30);
        GraphicsObject right = canvas.getElementAt(character.getX() + 40, character.getY() + 30);


        List<Enemy> removed = new ArrayList<>();


        for (int i = opponenets.size() - 1; i >= 0; i--) {
            Enemy enemy = opponenets.get(i);
            if (!enemy.isActive())
                continue;

            if (bottom == enemy.getSprite()) {
                enemy.remove();
                score += 100;
                background.updateScore(score);
            }
            if (!hitCooldown && (left == enemy.getSprite() || right == enemy.getSprite())) {
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

