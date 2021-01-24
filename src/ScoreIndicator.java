import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class defines a ScoreIndicator object.
 *
 * @author 323535419
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Construction function.
     *
     * @param scoreCounter The score counter
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * The function draws the sprite on a given surface.
     *
     * @param surface The drawing surface
     */
    public void drawOn(DrawSurface surface) {
        String s = "Score: " + String.valueOf(this.scoreCounter.getValue());
        surface.setColor(Color.white);
        surface.fillRectangle(0, 0, 800, 15);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(0, 0, 800, 15);
        surface.drawText(375, 10, s, 12);
    }

    /**
     * The function notifies the sprite that time has passed.
     */
    public void timePassed() {

    }
}
