package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.inject.Inject;

public class WinnersCtrl {

    private final MainCtrl mainCtrl;

    @FXML
    private Button backToMenu;

    @Inject
    public WinnersCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void goToSplash() {
        mainCtrl.showSplash();
    }
}
