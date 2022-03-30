package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class MultiChoiceQCtrl {
    private final MainCtrl mainCtrl;

    private Boolean isCorrect;
    private Timer countdown;
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
    @FXML
    private Label activity;

    @Inject
    public MultiChoiceQCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
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
    public void setUpEnergyGuess() {
        setQuestion();

        progressLabel.setText("16");
        progressBar.setProgress(1);
        isCorrect = false;
        countdown = new Timer();
        roundNumber.setText("Question: " + mainCtrl.getCurrentRoundNumber());
        activity.setText(mainCtrl.getQuestion().getDescription());

        //Change the text and the image according to the data from the json file

        choice1.setText(mainCtrl.getQuestion().getAnswers().get(0));
        //Image img1 = new Image("activity-bank/" + question.getImgPaths().get(0));
        //image1.setImage(img1);

        choice2.setText(mainCtrl.getQuestion().getAnswers().get(1));
        //Image img2 = new Image("activity-bank/" + question.getImgPaths().get(1));
        //image2.setImage(img2);

        choice3.setText(mainCtrl.getQuestion().getAnswers().get(2));
        //Image img3 = new Image("activity-bank/" + question.getImgPaths().get(2));
        //image3.setImage(img3);

        setTimer();
    }

    /**
     * If the player has reached the last round, it shows the all-time leaderboard, otherwise
     * it enables back the buttons for the next round and resets the text colour of the choices.
     */
    public void setQuestion() {
        if(mainCtrl.getCurrentRoundNumber() >= 20) {
            //mainCtrl.showWinners();
            mainCtrl.showLeadearboard();
        } else {
            choice1.setDisable(false);
            choice2.setDisable(false);
            choice3.setDisable(false);
            choice1.setStyle("-fx-text-fill: black;");
            choice2.setStyle("-fx-text-fill: black;");
            choice3.setStyle("-fx-text-fill: black;");
        }
    }

    /**
     * Starts the timer for each round helped by a timer task with a 1 second period
     * after which the progress bar and the label update.
     * If the state of the progress bar approaches 0:
     * -> if the "readyForNext" flag is raised, it cancels the timer and asks the main
     * controller to set the next round;
     * -> otherwise it checks whether the answer given by the player is correct, updates
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
                                String correct = mainCtrl.getQuestion().getAnswers().get(
                                        Integer.parseInt(mainCtrl.getQuestion()
                                                .getCorrectAnswer()) - 1);
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
     * If the player presses the first button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that,
     * adds points and disables all buttons until the end of the round.
     */
    public void checkFirst() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice1.setStyle("-fx-text-fill: blue;");

        String correct = mainCtrl.selectRightAnswer(mainCtrl.getQuestion());

        if(correct.equals(String.valueOf(1))){
            isCorrect = true;
            mainCtrl.getPlayer().setPoints(100);
        }
    }

    /**
     * If the player presses the second button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that,
     * adds points and disables all buttons until the end of the round.
     */
    public void checkSecond() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice2.setStyle("-fx-text-fill: blue;");

        String correct = mainCtrl.selectRightAnswer(mainCtrl.getQuestion());

        if(correct.equals(String.valueOf(2))){
            isCorrect = true;
            mainCtrl.getPlayer().setPoints(100);
        }
    }

    /**
     * If the player presses the third button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that,
     * adds points and disables all buttons until the end of the round.
     */
    public void checkThird() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice3.setStyle("-fx-text-fill: blue;");

        String correct = mainCtrl.selectRightAnswer(mainCtrl.getQuestion());

        if(correct.equals(String.valueOf(3))){
            isCorrect = true;
            mainCtrl.getPlayer().setPoints(100);
        }
    }
}
