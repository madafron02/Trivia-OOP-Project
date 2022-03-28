package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
import commons.Question;
import commons.Round;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MoreEnergyQCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private int currentRoundNumber = 0;
    private int totalRounds = 20;
    private Player player;
    private Question question;
    private Round round;
    private List<Round> roundList = new ArrayList<>();
    private Timer countdown = new Timer();
    private final int start = 15;
    private final double diff = 1.0 / 15.0;


    @FXML
    private TextField answerTitleA;
    @FXML
    private TextField answerTitleB;
    @FXML
    private TextField answerTitleC;
    @FXML
    private TextField roundNumber;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;

    @Inject
    public MoreEnergyQCtrl(ServerUtils server, MainCtrl mainCtrl) {
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
        countdown = new Timer();
        mainCtrl.getPrimaryStage().setTitle("Round " + currentRoundNumber);
        roundNumber.setText(String.valueOf("Question: " + currentRoundNumber));
        answerTitleA.setText(question.getAnswers().get(0));
        answerTitleB.setText(question.getAnswers().get(1));
        answerTitleC.setText(question.getAnswers().get(2));
    }

    public void setTimer(){
        TimerTask timerTask = new TimerTask() {
            Boolean readyForNext = false;

            @Override
            public void run() {
                javafx.application.Platform.runLater(() -> {
                    if(progressBar.getProgress() <= 0.1) {
                        if(readyForNext == true) {
                            countdown.cancel();
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

        countdown.schedule(timerTask, 0, 1000);
    }

    public void setQuestion() {
        if(currentRoundNumber >= totalRounds) {
            mainCtrl.showWinners();
        } else {


            /**
             * TO DO grab random activity names and random images for the answer
             */

            //List<String> activities = new ArrayList<>();
            //activities.add("1");
            //activities.add("2");
            //activities.add("3");
            //question = new Question("Which takes more energy?",
            //      activities);



            setUpRound();
            setTimer();
        }
    }
}
