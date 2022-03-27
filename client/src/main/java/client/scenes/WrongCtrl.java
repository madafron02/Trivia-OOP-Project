package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;

public class WrongCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private Label correctAnswer;

    @FXML
    private Label score;

    @Inject
    public WrongCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * Sets the correct answer with the given string
     * @param correct
     */
    public void setCorrectAnswer(String correct) {
        correctAnswer.setText(correct);
    }

    /**
     * Shows current score on screen
     * @param score
     */
    public void setScore(int score) {
        this.score.setText(String.valueOf(score));
    }
}
