package atlg4.ultimate.g47923.view;

/**
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
     * Shows the menu window. It allows the users the browse all the features.
     */
    void showGameMenuWindow();

    /**
     * Shows the window of the game. It allows the users to play the game.
     */
    void showGameWindow();

}