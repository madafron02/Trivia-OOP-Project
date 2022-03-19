package client.scenes;

import client.utils.ServerUtils;
import commons.Question;
import commons.Round;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MultiChoiceQCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private int currentRound = 1;
    private int totalRounds = 20;
    private int secondsPerRound = 10;
    private List<Round> roundList = new ArrayList<>();

    @FXML
    private TextArea answerA;
    @FXML
    private TextArea answerB;
    @FXML
    private TextArea answerC;
    @FXML
    private TextArea questionText;

    @Inject
    public MultiChoiceQCtrl(ServerUtils server, MainCtrl mainCtrl) throws InterruptedException {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    public void setFirst() throws InterruptedException {
        questionText.setText("Which takes more energy?" + " first");
        answerA.setText("First activity");
        answerB.setText("Second activity");
        answerC.setText("Third activity");

        Thread.sleep(3000);
        currentRound++;
    }

    public void goToNext() throws InterruptedException {
        if(currentRound > totalRounds) {
            mainCtrl.showWinners();
        } else {
            questionText.setText("Which takes more energy? " + currentRound);
            answerA.setText("First activity");
            answerB.setText("Second activity");
            answerC.setText("Third activity");

            //answerA.setText(roundList.get(currentRound).getQuestion().getAnswers().get(0));
            //answerB.setText(roundList.get(currentRound).getQuestion().getAnswers().get(1));
            //answerC.setText(roundList.get(currentRound).getQuestion().getAnswers().get(2));

            //answer text areas must be changed to buttons
            //set of comments above will be right after generating the question + answer list
            //display correct/wrong answer screen after each round
            //add good timer

            Thread.sleep(3000);
            currentRound++;
            goToNext();
        }
    }

}
