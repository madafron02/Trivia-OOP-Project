package client.scenes;

import client.utils.ServerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.inject.Inject;

public class HelpCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private Button startGame;

    @Inject
    public HelpCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }
}
