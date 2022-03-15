package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import javax.inject.Inject;
import java.awt.*;

public class AllTimeLeaderboardCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @Inject
    public AllTimeLeaderboardCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    @FXML
    private Button backFromLeaderboard;
    @FXML
    private TableView<Player> tableView;

}
