import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;

import javax.sound.sampled.*;
import java.io.File;

public class FinishLine {
    private Image castle;
    private Image flag;
    private boolean gameWon = false;
    private CanvasWindow canvas;
    private Background background;
    private double x;
    private double y;

    public FinishLine (CanvasWindow canvas, Background background){
        this.canvas = canvas;
        this.background = background;
        createFlag();
    }

    private void createFlag(){
        x = 1700;
        y = 397;
        flag = new Image(x,y, "Flag.png");
        flag.setMaxWidth(80);
        flag.setMaxHeight(120);
        canvas.add(flag);

        castle = new Image(x + 100, y - 163, "Castle.png");
        castle.setMaxWidth(300);
        castle.setMaxHeight(330);
        canvas.add(castle);
    }

    public void setOffsetX (double offsetX){
        flag.setPosition(x + offsetX , y);
        castle.setPosition(x + 100 + offsetX, y - 163);
    }

    public void checkFlagCollision(Character character) {
    if (!gameWon && character.getNaturalX() >= x -50){
        gameWon = true;
        levelComplete();
        winScreen();
    }
}

    public void levelComplete() {
        int score = background.getTime() * 50;
        background.updateScore(score);
        background.stopTimer();
    }

    public void winScreen(){
        GraphicsText winText = new GraphicsText("You Won!", canvas.getWidth() / 2 - 80, canvas.getHeight() / 2);
        winText.setFontSize(50);
        canvas.add(winText);
        playWinMusic();
    }

    public boolean gameWin(){
        return gameWon;
    }

    //early test for music
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

