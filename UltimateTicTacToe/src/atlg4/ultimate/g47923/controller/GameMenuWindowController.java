package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import atlg4.ultimate.g47923.persistence.business.UsersFacade;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import static java.util.Objects.requireNonNull;

/**
 * Controls the logic of the game menu window.
 *
 * @author Logan Farci (47923)
 */
public class GameMenuWindowController implements Initializable {

    private final Game game;
    private final View view;

    @FXML
    private Label firstUserPseudonym;

    @FXML
    private Label secondUserPseudonym;

    @FXML
    private Button firstUserDelete;

    @FXML
    private Button secondUserDelete;

    @FXML
    private Button join;

    @FXML
    private Button newgame;

    @FXML
    private Button resume;

    @FXML
    private Button statistics;

    /**
     * Constructs an instance of GameMenuWindowController with the given game
     * and view.
     *
     * @param game is the given game.
     * @param view is the given view.
     */
    public GameMenuWindowController(Game game, View view) {
        this.game = requireNonNull(game, "Trying to construct a "
                + "GameMenuWindowController with a null game");
        this.view = requireNonNull(view, "Trying to construct a "
                + "GameMenuWindowController with a null view");
    }

    @FXML
    private void join(ActionEvent event) {
        String pseudonym = view.askPseudonym();
        try {
            if (pseudonym != null) {
                game.setUser(UsersFacade.getUser(pseudonym));
                view.showInformation("Welcome " + pseudonym + "!", "You are now "
                        + "registered as " + pseudonym + ", your statistics will"
                        + " be updated after you are done playing!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showWarning("Impossible to join!", e.getMessage());
        }
    }

    @FXML
    private void startNewGame(ActionEvent event) {
        view.showGameWindow();
    }

    @FXML
    private void resumeGame(ActionEvent event) {
        view.showGameWindow();
    }

    @FXML
    private void showStatistics(ActionEvent event) {
        view.showStatistics();
    }

    @FXML
    private void quit(ActionEvent event) {
        String message = "Do you really want to quit?";
        if (!game.isOver() && view.askConfirmation(message)) {
            System.exit(0);
        }
    }

    @FXML
    private void deleteFirst(ActionEvent event) {
        try {
            game.removeUserFor(Marker.X);
        } catch (IllegalStateException e) {
            view.showWarning("You cannot do that.", e.getMessage());
        }
    }

    @FXML
    private void deleteSecond(ActionEvent event) {
        try {
            game.removeUserFor(Marker.O);
        } catch (IllegalStateException e) {
            view.showWarning("You cannot do that.", e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        join.setTooltip(new Tooltip("Join and start a game!"));
        newgame.setDisable(true);
        resume.setDisable(true);
    }

}
