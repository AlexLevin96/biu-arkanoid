/**
 * The class defines a ScoreTrackingListener object.
 *
 * @author 323535419
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor function.
     *
     * @param scoreCounter The score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * The function adds to the score after a hit event.
     *
     * @param beingHit The object that was hit
     * @param hitter The object that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() >= 0) {
            this.currentScore.increase(5);
        }
    }
}