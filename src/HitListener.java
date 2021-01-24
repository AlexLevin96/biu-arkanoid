/**
 * HitListener interface.
 *
 * @author 323535419
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit The object was hit
     * @param hitter The object that hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}