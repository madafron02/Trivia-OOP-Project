package client.scenes;

import client.utils.ServerUtils;
import commons.Player;
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
    private double counter;
    private boolean isSelected;
    private final double diff = 1.0 / 150.0;

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
        this.counter = 0;
        this.isSelected = false;
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
                    counter++;
                    if((isSelected == true&& mainCtrl.isSingleMode())
                            || progressBar.getProgress() <= 0.01||
                            (!mainCtrl.isSingleMode()&&
                                    server.readyForNextRound(mainCtrl.getGame().getId()))) {
                        if(readyForNext == true) {
                            if(progressBar.getProgress() <= 0.01){
                                countdown.cancel();
                                isSelected = false;
                                mainCtrl.setUpRound();
                            }
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
                            counter = 120;
                            readyForNext = true;
                        }
                    }
                    int passed = (int) ((counter * 100)/1000);
                    progressLabel.setText(String.valueOf(15-passed));
                    progressBar.setProgress((150-counter) * diff);
                });
            }
        };

        countdown.schedule(timerTask, 0, 100);
    }
    /**
     * if mark the current round as selected
     * if it is mutiplayer mode, also update the status to server
     */
    public void markAsChosen(){
        isSelected = true;
        mainCtrl.resetNoMove();
        if(!mainCtrl.isSingleMode()){
            mainCtrl.getPlayer().setStatus(Player.StatusType.READY);
            server.updatePlayer(mainCtrl.getGame().getId(),mainCtrl.getPlayer());
        }
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
        markAsChosen();
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
        mainCtrl.setUp();
        mainCtrl.getPlayer().setStatus(Player.StatusType.ABORTED);
        server.updatePlayer(mainCtrl.getGame().getId(),mainCtrl.getPlayer());
        mainCtrl.showSplash();
    }
}
