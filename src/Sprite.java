import biuoop.DrawSurface;

/**
 * Sprite interface.
 *
 * @author 323535419
 */
public interface Sprite {
    /**
     * The function draws the sprite on a given surface.
     *
     * @param surface The drawing surface
     */
    void drawOn(DrawSurface surface);

    /**
     * The function notifies the sprite that time has passed.
     */
    void timePassed();
}