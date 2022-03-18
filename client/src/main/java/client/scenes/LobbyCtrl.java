package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LobbyCtrl implements Initializable {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    private Button startGame;

    @FXML
    private ListView<String> playerList = new ListView<>();

    @Inject
    public LobbyCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerList.getItems().add(server.getPlayers()
                .get(server.getPlayers().size() - 1).getName());
    }
}
