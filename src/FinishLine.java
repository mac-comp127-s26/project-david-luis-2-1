
// @Authors: Luis Gonzalez-Xochihua and David Acuna
// Overview of Class: This class provides the aspects for the ending of the game.
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;

import javax.sound.sampled.*;
import java.io.File;

/**
 * Creates a finish line for the game
 */
public class FinishLine {
    private Image castle;
    private Image flag;
    private boolean gameWon = false;
    private CanvasWindow canvas;
    private Background background;
    private double x;
    private double y;

    /**
     * Creates the finishline for the game.
     * 
     * @param canvas     is a variable that creates the canvas for the game
     * @param background is a variable that creates and uses the background of the game.
     */
    public FinishLine(CanvasWindow canvas, Background background) {
        this.canvas = canvas;
        this.background = background;
        createFlag();
    }

    /**
     * This creates the flag at the end of the game.
     */
    private void createFlag() {
        x = 3850;
        y = 397;
        flag = new Image(x, y, "Flag.png");
        flag.setMaxWidth(80);
        flag.setMaxHeight(120);
        canvas.add(flag);

        castle = new Image(x + 100, y - 163, "Castle.png");
        castle.setMaxWidth(300);
        castle.setMaxHeight(330);
        canvas.add(castle);
    }

    /**
     * It creates the camera's movement to follow the character in the game using the FinishLine class
     * 
     * @param offsetX is a variable that helps follow the character horizontally forward or backward.
     */
    public void setOffsetX(double offsetX) {
        flag.setPosition(x + offsetX, y);
        castle.setPosition(x + 100 + offsetX, y - 163);
    }

    /**
     * This checks if the character has reached the finish and reached the flag for the game.
     * 
     * @param character is a variable that uses the character of the game.
     */
    public void checkFlagCollision(Character character) {
        if (!gameWon && character.getNaturalX() >= x - 50) {
            gameWon = true;
            levelComplete();
            winScreen();
        }
    }

    /**
     * This is the ability that makes sure that the level is complete and adds up the points from the
     * timer once it's over.
     */
    public void levelComplete() {
        int score = background.getTime() * 50;
        background.updateScore(score);
        background.stopTimer();
    }

    /**
     * This provides the win screen method whenever the character reahces the finishline and it doesn't
     * lose 3 lives.
     */
    public void winScreen() {
        GraphicsText winText = new GraphicsText("You Won!", canvas.getWidth() / 2 - 80, canvas.getHeight() / 2);
        winText.setFontSize(50);
        canvas.add(winText);
        playWinMusic();
    }

    public boolean gameWin() {
        return gameWon;
    }

    /**
     * it provides the music at the end whenever you get to the ending of the level of the game.
     */
    private void playWinMusic() {
        try {
            File musicFile = new File("res/win_music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Could not play music: " + e.getMessage());
        }
    }
}