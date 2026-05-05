import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;

public class Level {
    private static final int GROUND = 500;
    private static final Color BRICK = new Color (180,100,40);
    private static final Color PIPE = new Color(50,180,50);
    private static final Color PIPE_TOP  = new Color (30,160,30);

    private List <Obstacles> obstacles;
    private List <Enemy> enemies;
    private CanvasWindow canvas;

    public Level(CanvasWindow canvas, List<Obstacles> obstacles, List<Enemy> enemies){
        this.canvas = canvas;
        this.obstacles = obstacles;
        this.enemies = enemies;
        buildLevel();

 }
    private void buildLevel() {

        pipe(250, GROUND - 60, 60);  

        brick(380, GROUND - 160, 120, 20); 

        enemy(310, GROUND - 120, 260, 370);

   
        pipe(680, GROUND - 60, 60);       

        brick(820, GROUND - 180, 120, 20); 

        enemy(750, GROUND - 120, 690, 810);
        enemy(900, GROUND - 120, 830, 950);

    
        pipe(1150, GROUND - 70, 70);      

        
        brick(1300, GROUND - 60,  60, 20);
        brick(1360, GROUND - 120, 60, 20);
        brick(1420, GROUND - 180, 60, 20);
        brick(1480, GROUND - 240, 60, 20);

        enemy(1220, GROUND - 120, 1160, 1290);
        enemy(1420, GROUND - 120, 1360, 1480);

     
        pipe(1650, GROUND - 60, 60);       
        pipe(1900, GROUND - 70, 70);        

        brick(1780, GROUND - 180, 100, 20); 

        enemy(1720, GROUND - 120, 1660, 1770);
        enemy(1850, GROUND - 120, 1790, 1890);

     
        pipe(2080, GROUND - 60, 60);       

        brick(2220, GROUND - 180, 120, 20);

        pipe(2400, GROUND - 70, 70);        

        enemy(2150, GROUND - 120, 2090, 2210);
        enemy(2320, GROUND - 120, 2260, 2390);

      
        pipe(2550, GROUND - 60, 60);        

   
        brick(2660, GROUND - 60, 60, 20);
        brick(2720, GROUND - 120, 60, 20);
        brick(2780, GROUND - 180, 60, 20);
        brick(2840, GROUND - 240,  60, 20);

        enemy(2610, GROUND - 120, 2560, 2650);
        enemy(2760, GROUND - 120, 2700, 2840);

        pipe(2980, GROUND - 60, 60);

        brick(3100, GROUND - 160, 120, 20);
        brick(3200, GROUND - 240, 120, 20);

        pipe(3300, GROUND - 70, 70);

        brick(3420, GROUND - 60,  60, 20);
        brick(3480, GROUND - 120, 60, 20);
        brick(3540, GROUND - 180, 60, 20);
        brick(3600, GROUND - 240, 60, 20);

        pipe(3680, GROUND - 60, 60);

        enemy(3680, GROUND - 120, 3660, 3710); 
        enemy(3760, GROUND - 120, 3730, 3790); 
    }

private void pipe(double x, double y , double height) {
        Obstacles body = new Obstacles(canvas, x, y, 60, height, PIPE);
        obstacles.add(body);
        Obstacles top = new Obstacles(canvas, x, y - 15, 60, 15, PIPE_TOP);
        obstacles.add(top);
}

private void brick(double x , double y , double width, double height){
    Obstacles obs = new Obstacles(canvas, x, y, width, height, BRICK);
    obstacles.add(obs);
}

private void enemy(double x , double y , double moveLeft, double moveRight){
    Enemy e  = new Enemy(canvas, x, y, moveLeft, moveRight);
    enemies.add(e);
}

}
