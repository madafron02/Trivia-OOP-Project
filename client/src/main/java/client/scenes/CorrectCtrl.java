package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CorrectCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private Label score;

    @FXML
    private Label awarded;

    @Inject
    public CorrectCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * Shows current score on screen
     * @param score
     */
    public void setScore(int score) {
        this.score.setText(String.valueOf(score));
    }

    public void setAwarded(int awarded){
        this.awarded.setText("+" + String.valueOf(awarded));
    }
}
