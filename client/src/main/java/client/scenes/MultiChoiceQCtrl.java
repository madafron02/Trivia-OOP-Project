package client.scenes;

import client.utils.ServerUtils;
import commons.Round;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import javassist.Loader;

import javax.inject.Inject;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MultiChoiceQCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private int currentRound = 1;
    private int totalRounds = 20;
    private List<Round> roundList = new ArrayList<>();
    private final double start = 10;
    private IntegerProperty currentTime = new SimpleIntegerProperty((int)start);
    private Timeline timeline;
    //de pus si label

    @FXML
    private TextArea answerA;
    @FXML
    private TextArea answerB;
    @FXML
    private TextArea answerC;
    @FXML
    private TextArea questionText;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;

    @Inject
    public MultiChoiceQCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    public void fillProgressBar() {
        int counter = 0;

        while(counter <= 10) {

            progressBar.setProgress(counter);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            counter += 1;
        }
    }



    public void setQuestion() {
        questionText.setText("Which takes more energy?" + " first");
        answerA.setText("First activity");
        answerB.setText("Second activity");
        answerC.setText("Third activity");

        currentRound++;
    }

    public void setTimer(){
        progressBar.progressProperty().bind(Bindings.divide(currentTime, start));
        progressLabel.textProperty().bind(currentTime.asString());
        currentTime.set((int)start);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(start + 1),
                        new KeyValue(currentTime, 0)));
        timeline.playFromStart();
    }

    public void goToNext() {
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

            currentRound++;
            goToNext();
        }
    }

}
