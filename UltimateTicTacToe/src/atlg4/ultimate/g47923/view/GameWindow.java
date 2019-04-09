package atlg4.ultimate.g47923.view;

import atlg4.composant.g47923.MyTicTacToe;
import atlg4.ultimate.g47923.dto.MoveDTO;
import atlg4.ultimate.g47923.dto.PositionDTO;
import atlg4.ultimate.g47923.exception.UltimateTicTacToeException;
import atlg4.ultimate.g47923.model.Game;
import atlg4.ultimate.g47923.model.Marker;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static java.util.Objects.requireNonNull;
import static javafx.scene.layout.GridPane.getRowIndex;
import static javafx.scene.layout.GridPane.getColumnIndex;

/**
 * Is the game screen.
 *
 * @author Logan Farci (47923)
 */
public class GameWindow extends VBox implements Initializable, Observer {

    private final String CROSS_IMG_PATH = "/images/cross.png";
    private final String CIRCLE_IMG_PATH = "/images/circle.png";
    private static final String FXML_PATH = "/fxml/GameScreen.fxml";

    private Game game;
    private Stage stage;

    @FXML
    private GridPane board;

    /**
     * Constructs this MyTicTacToe with 9 empty cells. The cell are initially
     * not clickable.
     *
     * @param game
     * @param stage
     */
    public GameWindow(Game game, Stage stage) throws IOException {
        this.game = requireNonNull(game, "Constructing a GameWindow with a null "
                + "game.");
        this.stage = requireNonNull(stage, "Constructing a GameWindow with a null "
                + "stage.");
        load();
    }

    private void load() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException exception) {
            throw new IOException(FXML_PATH + " cannot be loaded!", exception);
        }
    }

    /**
     * Shows this game screen.
     */
    public void show() {
        stage.show();
    }

    private void addHandler(MyTicTacToe t, final int row, final int column) {
        t.addEventHandlerAt(row, column, new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    PositionDTO m = new PositionDTO(getRowIndex(t), getColumnIndex(t));
                    PositionDTO c = new PositionDTO(row, column);
                    game.select(m, c);
                    game.play();
                    game.nextPlayer();
                } catch (UltimateTicTacToeException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Attention!");
                    a.setContentText(e.getMessage());
                    a.showAndWait();
                }
            }
        });
    }

    private void addHandlers(MyTicTacToe t) {
        for (int row = 0; row < MyTicTacToe.SIZE; row++) {
            for (int column = 0; column < MyTicTacToe.SIZE; column++) {
                addHandler(t, row, column);
            }
        }
    }

    private void addHandlers() {
        for (Node child : board.getChildren()) {
            MyTicTacToe tictactoe = (MyTicTacToe) child;
            addHandlers(tictactoe);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addHandlers();
    }

    private void updateBoard() {
        MoveDTO move = game.getLastMove();
        PositionDTO m = move.getMiniTicTacToePosition();
        PositionDTO c = move.getCellPosition();
        Marker marker = move.getAuthor().getMarker();
        Image img = new Image(marker == Marker.X ? CROSS_IMG_PATH : CIRCLE_IMG_PATH);
        for (Node node : board.getChildren()) {
            MyTicTacToe t = (MyTicTacToe) node;
            if (getRowIndex(node) == m.getRow()
                    && getColumnIndex(node) == m.getColumn()) {
                t.setMarker(c.getRow(), c.getColumn(), img);
            }
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        updateBoard();
    }

}
