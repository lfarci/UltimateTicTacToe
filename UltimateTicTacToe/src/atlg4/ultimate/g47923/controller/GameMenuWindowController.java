package atlg4.ultimate.g47923.controller;

import atlg4.ultimate.g47923.dto.UserDTO;
import atlg4.ultimate.g47923.exception.UltimateTicTacToeDbException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import atlg4.ultimate.g47923.persistence.business.AdminFacade;
import atlg4.ultimate.g47923.view.View;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Logan Farci (47923)
 */
public class GameMenuWindowController implements Initializable {

    private final Game game;
    private final View view;

    @FXML
    private Label playerX;

    @FXML
    private Label playerO;

    @FXML
    private Button join;

    @FXML
    private Button newgame;

    @FXML
    private Button loadgame;

    @FXML
    private Button statistics;

    public GameMenuWindowController(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    private void enable(Button button) {
        button.setDisable(false);
        button.getStyleClass().remove("unavailable");
        button.getStyleClass().add("available");
    }

    private void disable(Button button) {
        button.setDisable(true);
        button.getStyleClass().remove("available");
        button.getStyleClass().add("unavailable");
    }

    private int getRandomNumberInRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private Marker getRandomMarker() {
        int random = getRandomNumberInRange(0, 1);
        return random == 1 ? Marker.X : Marker.O;
    }

    private boolean haveUsersBothBeenSeet() {
        return game.hasUserFor(Marker.X) && game.hasUserFor(Marker.O);
    }

    private UserDTO registerUser(String pseudonym) {
        UserDTO user = null;
        try {
            user = new UserDTO(pseudonym);
            AdminFacade.addUser(user);
            view.showWarning("Welcome " + pseudonym + "!", "You are now registered as "
                    + pseudonym + ", you can use this pseudonym in order"
                    + "to join the game next time!");
        } catch (UltimateTicTacToeDbException cannotAddException) {
            view.showWarning("Error while joining", "Registering the new "
                    + "user was unsucessful, please try again.");
        }
        return user;
    }

    private UserDTO getUser(String pseudonym) {
        UserDTO user = null;
        try {
            user = AdminFacade.findUserByPseudonym(pseudonym);
            if (user == null) {
                user = registerUser(pseudonym);
            } else {
                view.showWarning("Welcome back " + pseudonym + "!", "You are now "
                        + "registered as " + pseudonym + ", you can check your"
                        + "statistics now.");
            }
        } catch (UltimateTicTacToeDbException notFoundException) {
            notFoundException.printStackTrace();
        }
        return user;
    }

    @FXML
    private void join(ActionEvent event) {
        String pseudonym = view.askPseudonym();
        if (pseudonym != null) {
            Marker marker = getRandomMarker();
            if (game.hasUserFor(marker)) {
                marker = marker == Marker.X ? Marker.O : Marker.X;
            }
            game.setUserOf(marker, getUser(pseudonym));
            if (marker == Marker.X) {
                playerX.setText(pseudonym);
            } else {
                playerO.setText(pseudonym);
            }
        }
        if (haveUsersBothBeenSeet()) {
            enable(newgame);
            disable(join);
        }
    }

    @FXML
    private void startNewGame(ActionEvent event) {
        view.showGameWindow();
    }

    @FXML
    private void showStatistics(ActionEvent event) {
        view.showStatistics();
    }

    @FXML
    private void quit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        join.setTooltip(new Tooltip("Join and start a game!"));
        newgame.setDisable(true);
        loadgame.setDisable(true);
    }

}