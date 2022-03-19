package client.scenes;

import client.utils.ServerUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LobbyCtrl {
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

    public void addToList() {
        playerList.getItems().add(server.getPlayers()
                .get(server.getPlayers().size() - 1).getName());
    }

    public void startRounds() throws InterruptedException {
        MultiChoiceQCtrl multiChoiceQCtrl = mainCtrl.getMultiChoiceQCtrl();
        multiChoiceQCtrl.setFirst();
        mainCtrl.showMultiChoiceQ();
    }
}
