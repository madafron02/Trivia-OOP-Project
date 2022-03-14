package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

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

    public void goToMulti() {
        mainCtrl.showSplash();
    }

    public void goToLeader() {
        mainCtrl.showSplash();
    }
}
