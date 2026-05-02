import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class MainGame {
     private static final int CANVAS_HEIGHT = 600;
     private static final int CANVAS_WIDTH = 600;
     private Character character;
     private Background background;
     private CanvasWindow canvasWindow;
     private List <Obstacles> obstacles = new ArrayList<>();
     private int score = 0;
     private int lives = 3;

     public MainGame(){
          canvasWindow = new CanvasWindow("HI", CANVAS_WIDTH, CANVAS_HEIGHT);
          background = new Background(canvasWindow);
        Obstacles firstPlatform = new Obstacles(canvasWindow, 150, 350, 120, 20, Color.ORANGE);
        Obstacles secondPlatform = new Obstacles(canvasWindow, 380, 280, 120, 20, Color.ORANGE);
        Obstacles firstWall = new Obstacles(canvasWindow, 300, 420, 20, 80, Color.GRAY);
        Obstacles secondWall = new Obstacles(canvasWindow, 500, 420, 20, 80, Color.GRAY);
          
          
        obstacles.add(firstPlatform);
        obstacles.add(secondPlatform);
        obstacles.add(firstWall);
        obstacles.add(secondWall);

        for (Obstacles obstacle : obstacles){
            obstacle.addToCanvas();
        }
        
         character = new Character(canvasWindow, obstacles);
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