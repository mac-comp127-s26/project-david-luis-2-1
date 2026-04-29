import edu.macalester.graphics.CanvasWindow;

public class MainGame {
     private static final int CANVAS_HEIGHT = 600;
     private static final int CANVAS_WIDTH = 600;
     private Character character;
     private Background background;
     private CanvasWindow canvasWindow;

     public MainGame(){
          canvasWindow = new CanvasWindow("HI", CANVAS_WIDTH, CANVAS_HEIGHT);
          character = new Character(canvasWindow);
          background = new Background(canvasWindow);
          character.addToCanvas(canvasWindow);
     }
  
   public static void main(String[] args) {
        new MainGame();
    }

     


   
   


}








