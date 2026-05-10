// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class provides the enemy logic for the enemies in the game.
import java.util.List;
import edu.macalester.graphics.CanvasWindow;

/**
 * Creates the enemy logic for the enemy.
 */
public class EnemyLogic {
    private static final double HIT_COOLDOWN_TIME = 1.5;

    private Runnable onGameOver;
    private Character character;
    private Background background;
    private int lives = 3;
    private int score = 0;
    private boolean hitCooldown = false;
    private double contactTimer = 0;
    private List<Enemy> opponenets;

    /**
     * It establishes the actual enemy logic for the enemy and the animation for the timer.
     * 
     * @param canvas     is a variable that creates the canvas for the game.
     * @param character  is variable that uses the character itself in the game.
     * @param background is a variable that creates the background for the game.
     * @param opponenets is a variable to uses the opponenets/enemies that are in game itself.
     * @param onGameOver is a variable which tells whenever the game is over.
     */
    public EnemyLogic(CanvasWindow canvas, Character character, Background background, List<Enemy> opponenets,
        Runnable onGameOver) {
        this.onGameOver = onGameOver;
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

    /**
     * It check if the character in the game has touched the enemy in the game.
     * 
     * From there, if the character is touched from the top, you get 100 points added to the score and
     * enemy disappear. If the character it's touched from the side from the left or right, the
     * character loses a life and it's on cooldown from enemies touching the character and the enemy
     * doesn't disappear.
     * 
     * It provides the logic for the character and enemy interactions.
     */
    private void checkOpponenetCollision() {
        double charLeft = character.getNaturalX() + 15;
        double charRight = character.getNaturalX() + 65;
        double charTop = character.getY() + 40;
        double charBottom = character.getY() + 127;

        for (int i = opponenets.size() - 1; i >= 0; i--) {
            Enemy enemy = opponenets.get(i);
            if (!enemy.isActive())
                continue;

            double enemyLeft = enemy.getX() + 10;
            double enemyRight = enemy.getX() + enemy.getWidth() - 10;
            double enemyTop = enemy.getY() + 10;
            double enemyBottom = enemy.getY() + enemy.getHeight();
            boolean overlapping = charRight > enemyLeft &&
                charLeft < enemyRight &&
                charBottom > enemyTop &&
                charTop < enemyBottom;

            if (!overlapping)
                continue;

            double overlapTop = charBottom - enemyTop;
            double overlapLeft = charRight - enemyLeft;
            double overlapRight = enemyRight - charLeft;

            if (overlapTop < overlapLeft && overlapTop < overlapRight) {
                enemy.remove();
                score += 100;
                background.updateScore(score);
            } else if (!hitCooldown) {
                loseLife();
                hitCooldown = true;
                contactTimer = HIT_COOLDOWN_TIME;
            }
        }
    }

    /**
     * This provides the logic of the lives system of whenever the character touches the enemy, a loses
     * a life. Whenever the character loses 3 lives, the game ends and it closes.
     */
    public void loseLife() {
        if (lives > 0) {
            lives--;
            background.updateLives(lives);
        }
        if (lives == 0) {
            onGameOver.run();
        }
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}