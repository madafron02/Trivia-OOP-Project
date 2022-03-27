package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;

public class WrongCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private Label correctAnswer;

    @Inject
    public WrongCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void setCorrectAnswer(String correct) {
        correctAnswer.setText(correct);
    }
}
