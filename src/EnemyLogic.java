import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class EnemyLogic {
    private List <Enemy> opponenets;
    private Character character; 
    private Background background;
    private CanvasWindow canvas;
    private int lives = 3;
    private int score = 0;
    private boolean hitCooldown = false;

    public EnemyLogic (CanvasWindow canvas, Character character, Background background, List<Enemy> opponenets){
        this.canvas = canvas;
        this.character = character;
        this.background = background; 
        this.opponenets = opponenets;

        canvas.animate (dt -> {
            checkOpponenetCollision();
        });
}
    private void checkOpponenetCollision(){
        GraphicsObject bottom = canvas.getElementAt(character.getX() + 20 , character.getY() + 60);
        GraphicsObject left = canvas.getElementAt(character.getX(), character.getY() + 30);
        GraphicsObject right = canvas.getElementAt(character.getX() + 40, character.getY() + 30);

        List<Enemy> removed = new ArrayList<>();


        for (int i = opponenets.size() - 1 ; i >= 0 ;i--){
            Enemy enemy = opponenets.get(i);
            if (!enemy.isActive()) continue;
            
            if (bottom == enemy.getRect()){
                enemy.remove();
                score += 100;
                background.updateScore(score);
            }
            if (!hitCooldown && (left == enemy.getRect()) || right == enemy.getRect()){
                loseLife();
                hitCooldown = true;

            canvas.animate(dt ->{
                hitCooldown = false;
            });
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
    }


    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

     public void resetGame() {
        lives = 3;
        score = 0;
        background.updateLives(lives);
        background.updateScore(score);
    }

    }


