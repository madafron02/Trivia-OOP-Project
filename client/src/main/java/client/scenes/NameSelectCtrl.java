package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class NameSelectCtrl {

    private final MainCtrl mainCtrl;
    private final ServerUtils server;

    @Inject
    public NameSelectCtrl(MainCtrl mainCtrl, ServerUtils server) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    @FXML
    private TextArea nameInput;

    @FXML
    private Button toHelp;

    @FXML
    private Button checkSave;

    @FXML
    private Button toLobby;

    public void goToLobby() {
        if(checkSaveName()) {
            addPlayer();
            mainCtrl.showLobby();
        }
    }

    public void goToHelp() {
        mainCtrl.showHelp();
    }

    public void addPlayer() {
        Player player = new Player(nameInput.getText());
        server.addPlayer(player);
    }

    public Boolean checkSaveName() {
        // change when we do multiplayer:
        // check if someone already took this name
        return true;
    }
}
