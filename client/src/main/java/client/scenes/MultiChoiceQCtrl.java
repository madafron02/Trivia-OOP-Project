package client.scenes;

import client.utils.ServerUtils;
import javafx.animation.ScaleTransition;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.inject.Inject;
import java.nio.file.FileSystems;
import java.util.Timer;
import java.util.TimerTask;

public class MultiChoiceQCtrl {
    private final MainCtrl mainCtrl;
    private final ServerUtils server;
    private Boolean isSelected;
    private Boolean isCorrect;
    private Timer countdown;
    private double counter;
    private final double diff = 1.0 / 150.0;

    private String imagePath = FileSystems.getDefault()
            .getPath("client/src/main/resources/Images")
            .normalize()
            .toAbsolutePath()
            .toString();

    private final Image starImage =
            new Image("file:" + imagePath + "/starEmoji.png");
    private final Image heartImage =
            new Image("file:" + imagePath + "/heartEmoji.png");
    private final Image hundredImage =
            new Image("file:" + imagePath + "/100emoji.png");
    private final Image dizzyImage =
            new Image("file:" + imagePath + "/dizzyEmoji.png");

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
    private Label activity1;

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
    public MultiChoiceQCtrl(MainCtrl mainCtrl, ServerUtils server) {
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
    public void setUpEnergyGuess() {
        setQuestion();

        progressLabel.setText("16");
        progressBar.setProgress(1);
        isCorrect = false;
        countdown = new Timer();
        roundNumber.setText("Question: " + mainCtrl.getCurrentRoundNumber());
        activity1.setText(mainCtrl.getQuestion().getDescription());
        this.counter = 0;
        this.isSelected = false;

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
            choice1.setDisable(false);
            choice2.setDisable(false);
            choice3.setDisable(false);
            choice1.setStyle("-fx-text-fill: black;");
            choice2.setStyle("-fx-text-fill: black;");
            choice3.setStyle("-fx-text-fill: black;");
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
            public void run() {
                javafx.application.Platform.runLater(() -> {
                    counter++;
                    if ((isSelected == true && mainCtrl.isSingleMode())
                            || progressBar.getProgress() <= 0.01 ||
                            (!mainCtrl.isSingleMode() &&
                                    server.readyForNextRound(mainCtrl.getGame().getId()))) {
                        if (readyForNext == true) {
                            if (progressBar.getProgress() <= 0.01) {
                                if(!isSelected)mainCtrl.setNoMove();
                                else mainCtrl.resetNoMove();
                                countdown.cancel();
                                isSelected = false;
                                mainCtrl.setUpRound();
                            }
                        } else {
                            if (isCorrect) {
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
                            counter = 120;
                            readyForNext = true;
                        }
                    }
                    int passed = (int) ((counter * 100) / 1000);
                    progressLabel.setText(String.valueOf(15 - passed));
                    progressBar.setProgress((150 - counter) * diff);
                });
            }
        };
        countdown.schedule(timerTask, 0, 100);
    }

    /**
     * If the player presses the first button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that,
     * adds points and disables all buttons until the end of the round.
     */
    public void checkFirstM() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice1.setStyle("-fx-text-fill: blue;");

        String correct = mainCtrl.selectRightAnswer(mainCtrl.getQuestion());

        if(correct.equals(String.valueOf(1))){
            isCorrect = true;
            mainCtrl.getPlayer().setPoints(100);
        }
        markAsChosen();
    }

    /**
     * If the player presses the second button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that,
     * adds points and disables all buttons until the end of the round.
     */
    public void checkSecondM() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice2.setStyle("-fx-text-fill: blue;");

        String correct = mainCtrl.selectRightAnswer(mainCtrl.getQuestion());

        if(correct.equals(String.valueOf(2))){
            isCorrect = true;
            mainCtrl.getPlayer().setPoints(100);
        }
        markAsChosen();
    }

    /**
     * If the player presses the third button it colours the text in blue,
     * it checks if that represents the right answer, raises a flag for that,
     * adds points and disables all buttons until the end of the round.
     */
    public void checkThirdM() {
        choice1.setDisable(true);
        choice2.setDisable(true);
        choice3.setDisable(true);
        choice3.setStyle("-fx-text-fill: blue;");

        String correct = mainCtrl.selectRightAnswer(mainCtrl.getQuestion());

        if(correct.equals(String.valueOf(3))){
            isCorrect = true;
            mainCtrl.getPlayer().setPoints(100);
        }
        markAsChosen();
    }

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

    /**
     * Changes in-build to the listview method updateItem so that besides
     * the text it also inserts the image when making changes to it.
     */
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
     * the animation specified in emojiHandler ends. Also puts the focus to the
     * end of the listView so that the player can always see updated reactions.
     */
    public void heartOnClick() {
        setEmojiInsertion();
        emojiChat.getItems().add("Player " +
                mainCtrl.getPlayer().getName() + " on round " +
                mainCtrl.getCurrentRoundNumber() + " - heart");
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.heartButton.setDisable(true);
        emojiHandler("heart");

    }
    public void starOnClick() {
        setEmojiInsertion();
        emojiChat.getItems().add("Player " +
                mainCtrl.getPlayer().getName() + " on round " +
                mainCtrl.getCurrentRoundNumber() + " - star");
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.starButton.setDisable(true);
        emojiHandler("star");
    }
    public void hundredOnClick() {
        setEmojiInsertion();
        emojiChat.getItems().add("Player " +
                mainCtrl.getPlayer().getName() + " on round " +
                mainCtrl.getCurrentRoundNumber() + " - hundred");
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.hundredButton.setDisable(true);
        emojiHandler("hundred");
    }
    public void dizzyOnClick() {
        setEmojiInsertion();
        emojiChat.getItems().add("Player " +
                mainCtrl.getPlayer().getName() + " on round " +
                mainCtrl.getCurrentRoundNumber() + " - dizzy");
        emojiChat.scrollTo(emojiChat.getItems().size() - 1);
        this.dizzyButton.setDisable(true);
        emojiHandler("dizzy");
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
