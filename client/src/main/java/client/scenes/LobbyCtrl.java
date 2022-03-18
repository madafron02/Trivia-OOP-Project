package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javax.inject.Inject;

public class LobbyCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    private Button startGame;

    @FXML
    private ListView<Player> playerList;

    @Inject
    public LobbyCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }
}
