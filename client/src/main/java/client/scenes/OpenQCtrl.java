package client.scenes;

import client.utils.ServerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;


public class OpenQCtrl {
    private final MainCtrl mainCtrl;
    private final ServerUtils server;

    private Boolean isCorrect;
    private Timer countdown;
    private final double diff = 1.0 / 15.0;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    @FXML
    private Label activity;

    @FXML
    private Label roundNumber;

    @FXML
    private TextField guess;

    @FXML
    private Button saveAnswer;

    @Inject
    public OpenQCtrl(MainCtrl mainCtrl, ServerUtils server) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    /**
     * Sets the elements required by "Multiple choice question":
     * -> calls the setQuestion method to check if it's the last one;
     * -> resets the time progress properties (countdown, progressBar and progressLabel)
     * to their starting state because it's the beginning of a new round;
     * -> eliminates the "correct" state from last round;
     * -> shows the activity description and the options (how much energy this
     * activity might consume);
     * -> starts the new timer for this round by calling the setTimer method;
     */
    public void setUpOpen() {
        setQuestion();

        progressLabel.setText("16");
        progressBar.setProgress(1);
        isCorrect = false;
        countdown = new Timer();
        roundNumber.setText("Question: " + mainCtrl.getCurrentRoundNumber());
        activity.setText(mainCtrl.getQuestion().getDescription());

        // disable text field and button when button is pressed, enable back afterwards
        // when timer is up check answer if save button is pressed

        setTimer();
    }

    /**
     * If the player has reached the last round, it shows the all-time leaderboard, otherwise
     * it enables back the buttons for the next round and resets the choice text field.
     */
    public void setQuestion() {
            saveAnswer.setDisable(false);
            guess.setDisable(false);
            guess.clear();
    }

    /**
     * Starts the timer for each round helped by a timer task with a 1 second period
     * after which the progress bar and the label update.
     * If the state of the progress bar approaches 0:
     * -> if the "readyForNext" flag is raised, it cancels the timer and asks the main
     * controller to set the next round;
     * -> otherwise it checks whether the answer given by the player is correct
     * (more precisely within a certain interval close to the actual answer), updates
     * the score, shows the corresponding screen (correct/wrong) for 5 seconds
     * and raises a flag to indicate that the next round must be set up;
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
                            mainCtrl.setUpRound();
                        } else {
                            if(isCorrect) {
                                mainCtrl.getCorrect().setScore(mainCtrl.getPlayer().getPoints());
                                mainCtrl.showCorrect();
                            } else {
                                String correct = mainCtrl.getQuestion().getCorrectAnswer();
                                mainCtrl.getWrong().setCorrectAnswer(correct);
                                mainCtrl.getWrong().setScore(mainCtrl.getPlayer().getPoints());
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
     * If the player presses the "save" button:
     * -> checks if that represents a close enough answer, raises a flag for that;
     * -> adds points and disables the text field and button until the end of the
     * round.
     */
    public void check() {
        saveAnswer.setDisable(true);
        guess.setDisable(true);

        String exact = mainCtrl.selectRightAnswer(mainCtrl.getQuestion());
        if(guess.getText().equals("")) return;
        try{
            String playerGuess = guess.getText();
            double playerGuessValue = Double.parseDouble(playerGuess);

            double lowerLimit = 0.8 * Double.parseDouble(exact);
            double upperLimit = 1.2 * Double.parseDouble(exact);

            if(isBetween(lowerLimit, upperLimit, playerGuessValue)){
                isCorrect = true;
                mainCtrl.getPlayer().setPoints(100);
            }
        } catch(NumberFormatException e) {
            System.out.println("The player did not choose a number :(");
        }
    }

    /**
     * Shows if the input value stands inside a certain interval
     * @param low lower limit
     * @param high upper limit
     * @param input the value to be checked
     * @return true if input is between low and high
     */
    public static boolean isBetween(double low, double high, double input) {
        if(input >= low && input <= high) return true;
        return false;
    }

    /**
     * Adds "leave the game" button to the question screen
     */
    public void leaveTheGame() {
        countdown.cancel();
        countdown.purge();
        mainCtrl.showSplash();
    }
}
