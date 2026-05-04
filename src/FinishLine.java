import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;

public class FinishLine {
    private Image flag;
    private boolean gameWon = false;
    private CanvasWindow canvas;
    private Background background;

    public FinishLine (CanvasWindow canvas, Background background){
        this.canvas = canvas;
        this.background = background;
        createFlag();
    }

    private void createFlag(){
        flag = new Image(canvas.getWidth() - 150, 397, "flag_transparent.png");
        flag.setMaxWidth(80);
        flag.setMaxHeight(120);
        canvas.add(flag);
    }

    public void checkFlagCollision(Character character) {
    if (!gameWon 
        && character.getX() >= flag.getX() - 50
        && character.getX() <= flag.getX() + flag.getWidth() + 50
        && character.getY() >= flag.getY() - 100
        && character.getY() <= flag.getY() + flag.getHeight() + 50) {
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
    }

    public boolean gameWin(){
        return gameWon;
    }
    
}

