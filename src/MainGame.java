import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class MainGame {
     private static final int CANVAS_HEIGHT = 600;
     private static final int CANVAS_WIDTH = 600;
     private Character character;
     private Background background;
     private CanvasWindow canvasWindow;

     private int score = 0;
     private int lives = 3;

     public MainGame(){
          canvasWindow = new CanvasWindow("HI", CANVAS_WIDTH, CANVAS_HEIGHT);
          character = new Character(canvasWindow);
          background = new Background(canvasWindow);
          character.addToCanvas(canvasWindow);
     }


//Methods for logic of score and lives

    public void Logic(Background background) {
        this.background = background;
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

    public void levelComplete() {
        score += background.getTime() * 50;
        background.updateScore(score);
    }

    public void gameOver() {
        System.out.println("Game Over! Final Score: " + score);
    }

    public void resetGame() {
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

   public static void main(String[] args) {
        new MainGame();
    }
}