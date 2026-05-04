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
        flag = new Image(canvas.getWidth() - 150, 420, "flag.png");
        flag.setMaxWidth(50);
        flag.setMaxHeight(80);
        canvas.add(flag);
    }

    public void checkFlagCollision (Character character){
        if (!gameWon && flag.getBounds().intersects(character.getRectangle().getBounds())) {
            gameWon = true;
            levelComplete();
            winScreen();
        }
    }

    public void levelComplete() {
        int score = background.getTime() * 50;
        background.updateLives(score);
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
