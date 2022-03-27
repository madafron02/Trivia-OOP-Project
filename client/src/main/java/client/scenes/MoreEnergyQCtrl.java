package client.scenes;

import client.utils.ServerUtils;
import commons.Game;
import commons.Player;
import commons.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class MoreEnergyQCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    private int currentRoundNumber = 0;
    private int totalRounds = 20;
    private int score = 0;
    private Player player;
    private Question question;
    private Boolean isCorrect;
    private Game game;
    private Timer countdown = new Timer();
    private final double diff = 1.0 / 15.0;

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

    /**
     * Resets the time progress properties to their starting state at the beginning of each round,
     * increases the round number, eliminates the "correct" state from last round,
     * fetches the next question with answers and sets the title of the window
     */
    public void setUpRound() {
        progressLabel.setText("16");
        progressBar.setProgress(1);
        currentRoundNumber++;
        isCorrect = false;
        countdown = new Timer();
        game = mainCtrl.getGame();
        question = server.requireQuestion(game.getId(), currentRoundNumber);
        mainCtrl.setPrimaryStageTitle("Round " + currentRoundNumber);
        roundNumber.setText("Question: " + currentRoundNumber);
        choice1.setText(question.getAnswers().get(0));
        choice2.setText(question.getAnswers().get(1));
        choice3.setText(question.getAnswers().get(2));
        //image1.setImage(new Image(question.getImgPaths().get(0)));
        //image2.setImage(new Image(question.getImgPaths().get(1)));
        //image3.setImage(new Image(question.getImgPaths().get(2)));
    }

    /**
     * Starts the timer for each round helped by a timer task with a 1 second period after which
     * the progress bar and the label over it update.
     * If the state of the progress bar approaches 0, it checks which screen to show (correct/wrong)
     * and raises a flag to indicate that the next round must be set up after 5 seconds (so the player
     * has time to observe what he has done).
     */
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
                            if(isCorrect) {
                                mainCtrl.showCorrect();
                            } else {
                                String correct = question.getAnswers().get(
                                        Integer.parseInt(question.getCorrectAnswer()) - 1);
                                mainCtrl.getWrong().setCorrectAnswer(correct);
                                mainCtrl.showWrong();
                            }
                            progressLabel.setText("5");
                            progressBar.setProgress(0.3);
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

    /**
     * If the player has reached the last round, it shows the winner screen, otherwise
     * it enables back the buttons for the next round, resets the text colour of the choices
     * and calls the methods for the setup.
     */
    public void setQuestion() {
        if(currentRoundNumber >= totalRounds) {
            mainCtrl.showWinners();
        } else {
            choice1.setDisable(false);
            choice2.setDisable(false);
            choice3.setDisable(false);
            choice1.setStyle("-fx-text-fill: black;");
            choice2.setStyle("-fx-text-fill: black;");
            choice3.setStyle("-fx-text-fill: black;");
            setUpRound();
            setTimer();
        }
    }

    /**
     * Fetches the correct answer for a specific question
     * @param question the current question
     * @return the right answer for that question
     */
    public String selectRightAnswer(Question question) {
        String answer = question.getCorrectAnswer();
        return answer;
    }

    /**
     * If the player presses the first button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that
     * and disables all buttons until the end of the round.
     */
    public void checkFirst() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice1.setStyle("-fx-text-fill: blue;");

        String correct = selectRightAnswer(question);

        if(correct.equals(String.valueOf(1))){
            isCorrect = true;
        }
    }

    /**
     * If the player presses the second button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that
     * and disables all buttons until the end of the round.
     */
    public void checkSecond() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice2.setStyle("-fx-text-fill: blue;");

        String correct = selectRightAnswer(question);

        if(correct.equals(String.valueOf(2))){
            isCorrect = true;
        }
    }

    /**
     * If the player presses the third button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that
     * and disables all buttons until the end of the round.
     */
    public void checkThird() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice3.setStyle("-fx-text-fill: blue;");

        String correct = selectRightAnswer(question);

        if(correct.equals(String.valueOf(3))){
            isCorrect = true;
        }
    }
}
