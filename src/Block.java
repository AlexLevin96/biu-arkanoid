import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class defines a Block object.
 *
 * @author 323535419
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private int hits;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor function.
     *
     * @param rect  The block's rectangle
     * @param color the block's color
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * The function returns the collision rectangle of the block.
     *
     * @return Collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * The function returns the number of hits.
     *
     * @return Number of hits
     */
    public int getHits() {
        return this.hits;
    }

    /**
     * The function recieves a number and sets the number of hits.
     *
     * @param h Number of hits
     */
    public void setHits(int h) {
        this.hits = h;
    }

    /**
     * The function changes the velocity according to a given collision point.
     *
     * @param hitter The object that hit the block
     * @param collisionPoint  The given collision point
     * @param currentVelocity The given velocity
     * @return The new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        Point lowerLeft = new Point(x, y + this.rect.getHeight());
        Point upperRight = new Point(x + this.rect.getWidth(), y);
        Point lowerRight = new Point(x + this.rect.getWidth(), y + this.rect.getHeight());
        Line leftBorder = new Line(this.rect.getUpperLeft(), lowerLeft);
        Line rightBorder = new Line(upperRight, lowerRight);
        Line upperBorder = new Line(this.rect.getUpperLeft(), upperRight);
        Line lowerBorder = new Line(lowerLeft, lowerRight);
        // Top or bottom border
        if (lowerBorder.ifContainsPoint(lowerBorder, collisionPoint.getX(), collisionPoint.getY()) != null
                || upperBorder.ifContainsPoint(upperBorder, collisionPoint.getX(), collisionPoint.getY()) != null) {
            if (leftBorder.ifContainsPoint(leftBorder, collisionPoint.getX(), collisionPoint.getY()) != null
                    || rightBorder.ifContainsPoint(rightBorder, collisionPoint.getX(), collisionPoint.getY()) != null) {
                this.hits = this.hits - 1;
                this.notifyHit(hitter);
                return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            }
            this.hits = this.hits - 1;
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
            // Left or right border
        } else if (leftBorder.ifContainsPoint(leftBorder, collisionPoint.getX(), collisionPoint.getY()) != null
                || rightBorder.ifContainsPoint(rightBorder, collisionPoint.getX(), collisionPoint.getY()) != null) {
            this.hits = this.hits - 1;
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }

    /**
     * The function draws the block on a given surface.
     *
     * @param surface The given surface
     */
    public void drawOn(DrawSurface surface) {
        String s = String.valueOf(this.hits);
        if (this.hits <= 0) {
            s = "X";
        }
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawText((int) this.rect.getUpperLeft().getX() + (int) (this.rect.getWidth() / 2),
                (int) this.rect.getUpperLeft().getY() + (int) (this.rect.getHeight() / 2 + 7), s, 15);
    }

    /**
     * The function notifies the block that time has passed, currently it does nothing.
     */
    public void timePassed() {

    }

    /**
     * The function adds the block to the game.
     *
     * @param game The given game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * The function removes the block from the game.
     *
     * @param g The given game
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * The function adds a listener to hit events.
     *
     * @param hl The listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * The function removes a listener from a list of listeners to hit events.
     *
     * @param hl The listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The function notifies all listeners that there was a hit.
     *
     * @param hitter The hitter
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function returns the block's hit points.
     *
     * @return Hit points
     */
    public int getHitPoints() {
        return this.getHits();
    }

}
