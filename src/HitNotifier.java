/**
 * HitNotifier interface.
 *
 * @author 323535419
 */
public interface HitNotifier {
    /**
     * The function adds a listener to hit events.
     *
     * @param hl The listener
     */
    void addHitListener(HitListener hl);

    /**
     * The function removes a listener from a list of listeners to hit events.
     *
     * @param hl The listener
     */
    void removeHitListener(HitListener hl);
}