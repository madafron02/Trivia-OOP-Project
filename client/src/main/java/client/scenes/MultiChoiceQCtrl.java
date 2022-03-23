package client.scenes;
import client.utils.ServerUtils;
import commons.Player;
import commons.Question;
import commons.Round;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;


import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MultiChoiceQCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private int currentRoundNumber = 0;
    private int totalRounds = 20;
    private Player player;
    private Question question;
    private Round round;
    private List<Round> roundList = new ArrayList<>();
    private Timer timer = new Timer();
    private final int start = 15;
    private final double diff = 1.0 / 15.0;

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

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public void setCurrentRoundNumber(int currentRound) {
        this.currentRoundNumber = currentRound;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setUpRound() {
        progressLabel.setText("16");
        progressBar.setProgress(1);
        currentRoundNumber++;
        timer = new Timer();
        mainCtrl.getPrimaryStage().setTitle("Round " + currentRoundNumber);
        questionText.setText(question.getDescription());
        answerA.setText(question.getAnswers().get(0));
        answerB.setText(question.getAnswers().get(1));
        answerC.setText(question.getAnswers().get(2));
    }

    public void setTimer(){
        TimerTask timerTask = new TimerTask() {
            Boolean readyForNext = false;

            @Override
            public void run() {
                javafx.application.Platform.runLater(() -> {
                    if(progressBar.getProgress() <= 0.1) {
                        if(readyForNext == true) {
                            timer.cancel();
                            mainCtrl.primarySetSceneOnly();
                            setQuestion();
                        } else {
                            mainCtrl.showCorrect();
                            progressLabel.setText("5");
                            progressBar.setProgress(0.3);
                            //we will have separate cases here
                            //for correct/wrong
                            readyForNext = true;
                        }
                    }
                    progressLabel.setText(String.valueOf(Integer
                            .parseInt(progressLabel.getText()) - 1));
                    progressBar.setProgress(Double.parseDouble(progressLabel.getText()) * diff);
                });
            }
        };

        timer.schedule(timerTask, 0, 1000);
    }

    public void setQuestion() {
        if(currentRoundNumber >= totalRounds) {
            mainCtrl.showLeadearboard();
        } else {
            //question = qaSelector.getQuestion();
            //will generate new question with "random" answers

            List<String> activities = new ArrayList<>();
            activities.add("this");
            activities.add("that");
            activities.add("the other");
            question = new Question("Which takes more energy?",
                    activities);

            setUpRound();
            setTimer();
        }
    }
}
