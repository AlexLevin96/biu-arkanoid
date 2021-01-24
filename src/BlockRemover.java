/**
 * The class defines a BlockRemover object.
 *
 * @author 323535419
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor function.
     *
     * @param game The game
     * @param removedBlocks The number of removed blocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * The function removes a block from the game after a hit event.
     *
     * @param beingHit The object that was hit
     * @param hitter The object that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}