package client.scenes;

import client.utils.ServerUtils;
import commons.Game;
import commons.Player;
import commons.Question;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.inject.Inject;
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
    private Boolean isCorrect;
    private Game game;
    private Timer countdown = new Timer();
    private final double diff = 1.0 / 15.0;

    private Image starImage =
            new Image("file:/Users/a123/repository-template/client/src/main/resources/Images/starEmoji.png");
    private Image heartImage =
            new Image("file:/Users/a123/repository-template/client/src/main/resources/Images/heartEmoji.png");
    private Image hundredImage =
            new Image("file:/Users/a123/repository-template/client/src/main/resources/Images/100emoji.png");
    private Image dizzyImage =
            new Image("file:/Users/a123/repository-template/client/src/main/resources/Images/dizzyEmoji.png");

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
    private Button starButton;
    @FXML
    private Button dizzyButton;
    @FXML
    private Button heartButton;
    @FXML
    private Button hundredButton;
    @FXML
    private ImageView starEmoji;
    @FXML
    private ImageView dizzyEmoji;
    @FXML
    private ImageView hundredEmoji;
    @FXML
    private ImageView heartEmoji;
    @FXML
    private Button leaveTheGame;
    @FXML
    private ListView<String> emojiChat = new ListView<>();


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
        player = game.getPlayers().get(0);
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
     * If the state of the progress bar approaches 0, it checks which screen to show
     * (correct/wrong) and raises a flag to indicate that the next round must be set up
     * after 5 seconds (so the player has time to observe what he has done).
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
                                mainCtrl.getCorrect().setScore(player.getPoints());
                                mainCtrl.showCorrect();
                            } else {
                                String correct = question.getAnswers().get(
                                        Integer.parseInt(question.getCorrectAnswer()) - 1);
                                mainCtrl.getWrong().setCorrectAnswer(correct);
                                mainCtrl.getWrong().setScore(player.getPoints());
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
            mainCtrl.getWinners().setFirstPoints(player.getPoints());
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
            player.setPoints(100);
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
            player.setPoints(100);
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
            player.setPoints(100);
        }
    }

    /**
     * Settings for the animation, makes it last for 1 second
     * and go back (2 seconds in total) by scaling it 1.3 times
     * relative to X and Y axis.
     * @param scale Scaling animation.
     */
    public void setScale(ScaleTransition scale) {
        scale.setDuration(Duration.millis(1000));
        scale.setToX(1.3);
        scale.setToY(1.3);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
    }

    /**
     * Creates the animation object and depending on the emoji
     * which was pressed enables this emoji once again only when animation
     * is finished.
     * This way animation will go without any unexpected behaviors
     * and will prevent spamming.
     * @param emojiType String identifying which emoji was pressed.
     */
    public void emojiHandler(String emojiType) {
        ScaleTransition scale = new ScaleTransition();
        setScale(scale);
        Timer timer = new Timer();
        switch (emojiType) {
            case "star":
                scale.setNode(starEmoji);
                TimerTask taskStar = new TimerTask() {
                    @Override
                    public void run() {
                        starButton.setDisable(false);
                    }
                };
                timer.schedule(taskStar, 2001);
                break;
            case "heart":
                scale.setNode(heartEmoji);
                TimerTask taskHeart = new TimerTask() {
                    @Override
                    public void run() {
                        heartButton.setDisable(false);
                    }
                };
                timer.schedule(taskHeart, 2001);
                break;
            case "hundred":
                scale.setNode(hundredEmoji);
                TimerTask taskHundred = new TimerTask() {
                    @Override
                    public void run() {
                        hundredButton.setDisable(false);
                    }
                };
                timer.schedule(taskHundred, 2001);
                break;
            case "dizzy":
                scale.setNode(dizzyEmoji);
                TimerTask taskDizzy = new TimerTask() {
                    @Override
                    public void run() {
                        dizzyButton.setDisable(false);
                    }
                };
                timer.schedule(taskDizzy, 2001);
                break;
            default:
                break;
        }
        scale.play();
    }
    public void setEmojiInsertion() {
        this.emojiChat.setCellFactory(listView -> new ListCell<String>() {
            private final ImageView imageView = new ImageView();
            @Override
            public void updateItem(String text, boolean empty) {
                super.updateItem(text, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                }
                else {
                    ImageView imageView = new ImageView();
                    if (text.contains("heart")) {
                        imageView.setImage(heartImage);
                        imageView.setFitHeight(40);
                        imageView.setFitWidth(40);
                    } else if (text.contains("hundred")) {
                        imageView.setImage(hundredImage);
                        imageView.setFitHeight(40);
                        imageView.setFitWidth(40);
                    } else if (text.contains("dizzy")) {
                        imageView.setImage(dizzyImage);
                        imageView.setFitHeight(40);
                        imageView.setFitWidth(40);
                    } else {
                        imageView.setImage(starImage);
                        imageView.setFitHeight(40);
                        imageView.setFitWidth(40);
                    }
                    String[] split = text.split(" - ");
                    text = split[0];
                    setText(text);
                    setGraphic(imageView);
                }
            }
        });
    }

    /**
     * Calls emojiHandler method passing the string depending
     * on the emoji pressed and disables this emoji until
     * the animation specified in emojiHandler ends.
     */
    public void heartOnClick() {
        setEmojiInsertion();
        ObservableList<String> list = emojiChat.getItems();
        list.add("Round " + this.currentRoundNumber + ": Player " +
                this.player.getName() + " - heart");
        emojiChat.setItems(list);
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.heartButton.setDisable(true);
        emojiHandler("heart");

    }
    public void starOnClick() {
        setEmojiInsertion();
        ObservableList<String> list = emojiChat.getItems();
        list.add("Round " + this.currentRoundNumber + ": Player " +
                this.player.getName() + " - star");
       // emojiChat.getItems().add("Round " + this.currentRoundNumber + ": Player " +
                //this.player.getName() + " reacted - star");
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.starButton.setDisable(true);
        emojiHandler("star");
    }
    public void hundredOnClick() {
        setEmojiInsertion();
        //emojiChat.getItems().add("Round " + this.currentRoundNumber + ": Player " +
               // this.player.getName() + " reacted - hundred");
        ObservableList<String> list = emojiChat.getItems();
        list.add("Round " + this.currentRoundNumber + ": Player " +
                this.player.getName() + " - hundred");
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.hundredButton.setDisable(true);
        emojiHandler("hundred");
    }
    public void dizzyOnClick() {
        setEmojiInsertion();
        //emojiChat.getItems().add("Round " + this.currentRoundNumber + ": Player " +
                //this.player.getName() + " reacted - dizzy");
        ObservableList<String> list = emojiChat.getItems();
        list.add("Round " + this.currentRoundNumber + ": Player " +
                this.player.getName() + " - dizzy");
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.dizzyButton.setDisable(true);
        emojiHandler("dizzy");
    }
    public void leaveTheGame() {
        countdown.cancel();
        countdown.purge();
        mainCtrl.showSplash();
    }
}
