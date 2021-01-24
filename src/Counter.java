/**
 * The class defines a Counter object.
 *
 * @author 323535419
 */
public class Counter {
    private int n;

    /**
     * Constructor function.
     */
    public Counter() {
        this.n = 0;
    }

    /**
     * The function adds a given number to the counter.
     *
     * @param num The number
     */
    public void increase(int num) {
        this.n += num;
    }

    /**
     * The function removes a given number from the counter.
     *
     * @param num The number
     */
    public void decrease(int num) {
        this.n -= num;
    }

    /**
     * The function returns the counter's value.
     *
     * @return The counter's value
     */
    public int getValue() {
        return this.n;
    }
}