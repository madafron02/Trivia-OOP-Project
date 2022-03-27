package client.scenes;

import client.utils.ServerUtils;
import commons.Game;
import commons.Player;
import commons.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class MoreEnergyQCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private int currentRoundNumber = 0;
    private int totalRounds = 20;
    private Player player;
    private Question question;
    private Game game;
    private Timer countdown = new Timer();
    private final double diff = 1.0 / 15.0;


    @FXML
    private Label answerTitleA;
    @FXML
    private Label answerTitleB;
    @FXML
    private Label answerTitleC;
    @FXML
    private Button choice1;
    @FXML
    private Button choice2;
    @FXML
    private Button choice3;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private Label roundNumber;
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
        game = mainCtrl.getGame();
        question = server.requireQuestion(game.getId(), currentRoundNumber);
        mainCtrl.setPrimaryStageTitle("Round " + currentRoundNumber);
        roundNumber.setText("Question: " + currentRoundNumber);
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
            setUpRound();
            setTimer();
        }
    }
}
