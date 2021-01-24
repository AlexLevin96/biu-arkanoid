import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The class defines a SpriteCollection object.
 *
 * @author 323535419
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor function.
     */
    public SpriteCollection() {
        this.sprites  = new ArrayList<>();
    }
    /**
     * The function adds a sprite to the collection.
     *
     * @param s The given sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The function removes a sprite from the collection.
     *
     * @param s The sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * The function calls the timePassed function for all of the sprites in the collection.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * The function calls the drawOn(surface) function for all of the sprites in the collection.
     *
     * @param surface the surface.
     */
    public void drawAllOn(DrawSurface surface) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(surface);
        }
    }
}