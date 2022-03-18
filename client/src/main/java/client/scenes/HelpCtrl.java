package client.scenes;

import client.utils.ServerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.inject.Inject;

public class HelpCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private Button goBack;

    @Inject
    public HelpCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void goBackFromHelp() {
        mainCtrl.showNameSelect();
    }
}
