package atlg4.ultimate.g47923.exception;

/**
 * Thrown when the integrity of a grid is not respected.
 *
 * @author Logan Farci (47923)
 */
public class GridException extends UltimateTicTacToeException {

    /**
     * Constructs an instance of <code>GridException</code> with the specified
     * detail message.
     *
     * @param message the detail message.
     */
    public GridException(String message) {
        super(message);
    }
}
