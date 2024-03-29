package atlg4.ultimate.g47923.view;

/**
 * The user of this interface can control the display of the <i>Ultimate Tic Tac
 * Toe</i> game. This interface is intended to be implemented as a graphical
 * user interface.
 *
 * @author Logan Farci (47923)
 */
public interface View {

    /**
     * Asks the user a confirmation with the specified message.
     *
     * @param message is the specified message.
     * @return true if the user has confirmed.
     */
    boolean askConfirmation(String message);

    /**
     * Asks the user pseudonym.
     *
     * @return the user pseudonym.
     */
    String askPseudonym();

    /**
     * Asks the user if she/ he wants to replay a game.
     *
     * @return true if the player wants to replay a game.
     */
    boolean askReplay();

    /**
     * Shows the current stage of this view.
     */
    void show();
    
    /**
     * Shows the menu window. It allows the users the browse all the features.
     */
    void showGameMenuWindow();

    /**
     * Shows the window of the game. It allows the users to play the game.
     */
    void showGameWindow();

    /**
     * Shows the statistics of the registered players.
     */
    void showStatistics();

    /**
     * Shows a warning with the detailed header and message.
     *
     * @param header is the detailed header.
     * @param message is the detailed message.
     */
    void showWarning(String header, String message);

    /**
     * Shows an information with the detailed header and message.
     *
     * @param header is the detailed header.
     * @param message is the detailed message.
     */
    void showInformation(String header, String message);

}
