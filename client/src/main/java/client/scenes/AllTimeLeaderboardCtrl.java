package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Controller for the leaderboard
 */
public class AllTimeLeaderboardCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    private Label leaderboardLabel;
    @FXML
    private Button backFromLeaderboard;
    @FXML
    private TableView<Player> tableView = new TableView<>();
    @FXML
    private TableColumn<Player, String> playerName = new TableColumn<>();
    @FXML
    private TableColumn<Player, Integer> score = new TableColumn<>();
    @FXML
    private TableColumn<Player, Integer> place = new TableColumn<>();

    /**
     * Constructor injection for the all-time leaderboard
     * @param server - serverUtils object with methods to add/get players to/from the server
     * @param mainCtrl - main control class
     */
    @Inject
    public AllTimeLeaderboardCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    /**
     * This method is automatically invoked when the scene is loaded.
     * This method gets the players from the server, sorts them and fill the tableView columns
     * using corresponding methods from javaFx.
     * setCellValueFactory uses the getters from the Player class to automatically fill in all
     * of the columns after passing the list of players to the tableView.
     */
    public void initialize() {
        List<Player> players = this.server.getPlayers();
        players.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        for (Player player : players) {
            player.setPlace(players.indexOf(player) + 1);
        }
        this.playerName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        this.place.setCellValueFactory(new PropertyValueFactory<Player, Integer>("place"));
        this.score.setCellValueFactory(new PropertyValueFactory<Player, Integer>("points"));
        ObservableList<Player> observableList = FXCollections.observableList(players);
        tableView.setItems(observableList);
    }

    /**
     * Go back button
     */
    public void goBack() {

        mainCtrl.showSplash();
    }

}
