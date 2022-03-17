package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

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

    @FXML
    private ImageView imageView;

    public void goToSingle() {
        mainCtrl.showSingle();
    }

    public void goToMultiPlayer() {
        mainCtrl.showSingle();
    }

    public void goToLeader() {
        mainCtrl.showLeadearboard();
    }
}
