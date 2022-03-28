package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.inject.Inject;

public class WinnersCtrl {

    private final MainCtrl mainCtrl;

    @FXML
    private Button backToMenu;

    @FXML
    private Label firstPoints;

    @Inject
    public WinnersCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void goToSplash() {
        mainCtrl.showSplash();
    }

    public void setFirstPoints(int score) {
        firstPoints.setText(String.valueOf(score));
    }
}
