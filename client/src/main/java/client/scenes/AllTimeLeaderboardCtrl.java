package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AllTimeLeaderboardCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    private Button backFromLeaderboard;
    @FXML
    private TableView<Player> tableView = new TableView<>();
    @FXML
    private TableColumn<Player, String> playerName = new TableColumn<>();
    @FXML
    private TableColumn<Player, Integer> score = new TableColumn<>();
    @FXML
    private TableColumn<Player, String> place = new TableColumn<>();

    @Inject
    public AllTimeLeaderboardCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    public void initialize() {
        List<Player> players = this.server.getPlayers();
        players.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        playerName.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        score.setCellValueFactory(new PropertyValueFactory<Player, Integer>("points"));
        ObservableList<Player> observableList = FXCollections.observableList(players);
        tableView.setItems(observableList);
    }


    public void goBack() {
        mainCtrl.showSplash();
    }

}
