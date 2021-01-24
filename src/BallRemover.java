/**
 * The class defines a BallRemover object.
 *
 * @author 323535419
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    private Block deathBlock;

    /**
     * Constructor function.
     *
     * @param game The game
     * @param removedBalls The number of removed balls
     * @param deathBlock The death block
     */
    public BallRemover(Game game, Counter removedBalls, Block deathBlock) {
        this.game = game;
        this.remainingBalls = removedBalls;
        this.deathBlock = deathBlock;
    }

    /**
     * The function removes a ball from the game after a hit event.
     *
     * @param beingHit The object that was hit
     * @param hitter The object that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.equals(this.deathBlock)) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }
}