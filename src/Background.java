

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Background{
    private static final int GROUND_HEIGHT = 100;
    public static final Color SKY_COLOR = new Color(188, 217, 255);
    public static final Color GROUND_COLOR = new Color(122, 181, 107);
    private CanvasWindow canvas;

    private void createBackground() {
        Rectangle sky = new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight());
        sky.setFillColor(SKY_COLOR);
        sky.setFilled(true);
        canvas.add(sky);

        Rectangle ground = new Rectangle(
            0, canvas.getHeight() - GROUND_HEIGHT,
            canvas.getWidth(), GROUND_HEIGHT);
        ground.setFilled(true);
        ground.setFillColor(GROUND_COLOR);
        ground.setStroked(false);
        canvas.add(ground);
    }
}
