package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


//import java.awt.*;

public class SplashCtrl {

    private final MainCtrl mainCtrl;

    @Inject
    public SplashCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    private Button singleplayer;

    @FXML
    private Button multiplayer;

    @FXML
    private Button leaderboard;

    public void goToSingle() {
        mainCtrl.showSingle();
    }

    public void goToMulti() {
        mainCtrl.showSplash();
    }

    public void goToLeader() {
        mainCtrl.showSplash();
    }
}
