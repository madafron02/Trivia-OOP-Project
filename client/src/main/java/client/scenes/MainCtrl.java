/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package client.scenes;

import client.utils.ServerUtils;
import commons.Game;
import commons.Player;
import commons.Question;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

public class MainCtrl {
    private boolean isSingleMode;
    private Player player;
    private Game game;
    private Stage primaryStage;
    private int currentRoundNumber = 0;
    private Question question;

    private SplashCtrl splashCtrl;
    private Scene opening;

    private MultiChoiceQCtrl multiCtrl;
    private Scene multiChoice;

    private MoreEnergyQCtrl moreECtrl;
    private Scene moreEnergy;

    private OpenQCtrl openQCtrl;
    private Scene openQ;

    private NameSelectCtrl nameSelectCtrl;
    private Scene nameSelect;

    private LobbyCtrl lobbyCtrl;
    private Scene lobby;

    private CorrectCtrl correctCtrl;
    private Scene correct;

    private HelpCtrl helpCtrl;
    private Scene help;

    private WrongCtrl wrongCtrl;
    private Scene wrong;

    private IngameLeaderboardCtrl igLeaderboardCtrl;
    private Scene igLeaderboard;

    private AllTimeLeaderboardCtrl leaderboardCtrl;
    private Scene leaderboard;

    private WinnersCtrl winnersCtrl;
    private Scene winners;

    /**
     * Initializes the primary stage, all the controllers and
     * all the scenes used in this game
     * @param primaryStage
     * @param opening
     * @param nameSelectCtrlParentPair
     * @param lobbyCtrlParentPair
     * @param helpCtrlParentPair
     * @param igLeaderboardPair
     * @param leaderboardPair
     * @param correctCtrlParentPair
     * @param wrongCtrlParentPair
     * @param winnersPair
     * @param multiChoice
     * @param moreEnergy
     */
    public void initializeNew(Stage primaryStage, Pair<SplashCtrl, Parent> opening,
                              Pair<NameSelectCtrl, Parent> nameSelectCtrlParentPair,
                              Pair<LobbyCtrl, Parent> lobbyCtrlParentPair,
                              Pair<HelpCtrl, Parent> helpCtrlParentPair,
                              Pair<IngameLeaderboardCtrl, Parent> igLeaderboardPair,
                              Pair<AllTimeLeaderboardCtrl, Parent> leaderboardPair,
                              Pair<CorrectCtrl, Parent> correctCtrlParentPair,
                              Pair<WrongCtrl, Parent> wrongCtrlParentPair,
                              Pair<WinnersCtrl, Parent> winnersPair,
                              Pair<MultiChoiceQCtrl, Parent> multiChoice,
                              Pair<MoreEnergyQCtrl, Parent> moreEnergy,
                              Pair<OpenQCtrl, Parent> openQ) {
        this.primaryStage = primaryStage;
        this.splashCtrl = opening.getKey();
        this.opening = new Scene(opening.getValue());
        this.lobby = new Scene(lobbyCtrlParentPair.getValue());
        this.lobbyCtrl = lobbyCtrlParentPair.getKey();
        this.correctCtrl = correctCtrlParentPair.getKey();
        this.correct = new Scene(correctCtrlParentPair.getValue());
        this.help = new Scene(helpCtrlParentPair.getValue());
        this.helpCtrl = helpCtrlParentPair.getKey();
        this.wrong = new Scene(wrongCtrlParentPair.getValue());
        this.wrongCtrl = wrongCtrlParentPair.getKey();
        this.igLeaderboard = new Scene(igLeaderboardPair.getValue());
        this.igLeaderboardCtrl = igLeaderboardPair.getKey();
        this.leaderboard = new Scene(leaderboardPair.getValue());
        this.leaderboardCtrl = leaderboardPair.getKey();
        this.winnersCtrl = winnersPair.getKey();
        this.winners = new Scene(winnersPair.getValue());
        this.moreEnergy = new Scene(moreEnergy.getValue());
        this.moreECtrl = moreEnergy.getKey();
        this.multiCtrl = multiChoice.getKey();
        this.multiChoice = new Scene(multiChoice.getValue(), Color.web("#011826"));
        this.openQ = new Scene(openQ.getValue());
        this.openQCtrl = openQ.getKey();
        this.nameSelect = new Scene(nameSelectCtrlParentPair.getValue(), Color.web("#011826"));
        this.nameSelectCtrl = nameSelectCtrlParentPair.getKey();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        showSplash();
        primaryStage.show();
        this.isSingleMode = true;
    }

    /**
     * Gets the controller for the "Wrong answer" scene
     * @return the controller
     */
    public WrongCtrl getWrong() {
        return wrongCtrl;
    }

    /**
     * Gets the controller for the "Correct answer" scene
     * @return the controller
     */
    public CorrectCtrl getCorrect() {
        return correctCtrl;
    }

    /**
     * Gets the controller for the "Winners" scene
     * @return the controller
     */
    public WinnersCtrl getWinners() {
        return winnersCtrl;
    }

    /**
     * Gets the controller for the "Multiple choice question" scene
     * @return the controller
     */
    public MultiChoiceQCtrl getMultiCtrl() {
        return multiCtrl;
    }

    /**
     * Gets the controller for the "More energy question" scene
     * @return the controller
     */
    public MoreEnergyQCtrl getMoreECtrl() {
        return moreECtrl;
    }

    /**
     * Gets the controller for the "Open question" scene
     * @return the controller
     */
    public OpenQCtrl getOpenQCtrl() {
        return openQCtrl;
    }

    /**
     * Gets the "Multiple choice question" scene
     * @return the scene
     */
    public Scene getMultiChoice() {
        return multiChoice;
    }

    /**
     * Gets the "More energy question" scene
     * @return the scene
     */
    public Scene getMoreEnergy() {
        return moreEnergy;
    }

    /**
     * Gets the "Open question" scene
     * @return the scene
     */
    public Scene getOpenQ() {
        return openQ;
    }

    /**
     * Shows if the game type is singleplayer
     * @return true if it is in singleplayer mode
     */
    public boolean isSingleMode() {
        return isSingleMode;
    }

    /**
     * Sets the game type to singleplayer
     * @param type describes the game type (true = singleplayer, false = multiplayer)
     */
    public void setSingleMode(boolean type) {
        isSingleMode = type;
    }

    /**
     * Gets the player that is in the current singleplayer game
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player inside the current singleplayer game
     * @param player the player that needs to be added
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Sets the current game
     * @param game the game that needs to be set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets the game currently connected to the main controller
     * @return the current game
     */
    public Game getGame(){
        return this.game;
    }

    /**
     * Gets the current round number
     * @return an int describing the round number
     */
    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    /**
     * Sets the current round number
     * @param currentRoundNumber number to be changed
     */
    public void setCurrentRoundNumber(int currentRoundNumber) {
        this.currentRoundNumber = currentRoundNumber;
    }

    /**
     * Gets the question that is currently showed to the player
     * @return the current question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Sets the question that the player has to see
     * @param question the question to be shown
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * Shows the splash screen
     */
    public void showSplash() {
        primaryStage.setTitle("Quizzzz!");
        primaryStage.setScene(opening);
        primaryStage.setMinHeight(900);
        primaryStage.setMinWidth(1440);
    }

    /**
     * Shows name selection screen
     */
    public void showNameSelect() {
        primaryStage.setTitle("Name Screen");
        primaryStage.setScene(nameSelect);
    }

    /**
     * Shows the waiting room (lobby) in multiplayer mode
     */
    public void showLobby() {
        primaryStage.setTitle("Waiting room");
        primaryStage.setScene(lobby);
    }

    /**
     * Gets the controller for the lobby
     * @return
     */
    public LobbyCtrl getLobby() {
        return lobbyCtrl;
    }

    /**
     * Shows the "Correct answer" screen
     */
    public void showCorrect() {
        primaryStage.setTitle("Correct Answer!");
        primaryStage.setScene(correct);
    }

    /**
     * Shows the screen with instructions on how to play
     */
    public void showHelp() {
        primaryStage.setTitle("Help");
        primaryStage.setScene(help);
    }

    /**
     * Shows the "Wrong answer" screen
     */
    public void showWrong() {
        primaryStage.setTitle("Wrong answer!");
        primaryStage.setScene(wrong);
    }

    /**
     * Shows in-game leaderboard
     */
    public void showIgLeaderboard() {
        primaryStage.setTitle("In-game leaderboard");
        primaryStage.setScene(igLeaderboard);
    }

    /**
     * Shows all-time leaderboard
     */
    public void showLeadearboard() {
        leaderboardCtrl.initialize();
        primaryStage.setTitle("All-time leaderboard");
        primaryStage.setScene(leaderboard);
    }

    /**
     * Shows winner screen
     */
    public void showWinners() {
        primaryStage.setTitle("Winners");
        primaryStage.setScene(winners);
    }

    /**
     * Shows the multiple choice question type screen
     *
     */
    public void showMultiChoiceQ() {
        primaryStage.setTitle("Questions");
        primaryStage.setScene(multiChoice);
    }

    /**
     * Shows the "Which takes more energy?" question type screen
     */
    public void showMoreEnergyQ() {
        primaryStage.setTitle("Questions");
        primaryStage.setScene(moreEnergy);
    }

    /**
     * Shows the energy estimation type screen
     *
     */
    public void showOpenQ() {
        primaryStage.setTitle("Questions");
        primaryStage.setScene(openQ);
    }

    /**
     * Fetches the correct answer for a specific question
     * @return the right answer for that question
     */
    public String selectRightAnswer(Question question) {
        String answer = question.getCorrectAnswer();
        return answer;
    }

    /**
     * Sets the round details:
     * -> the next question generated by the "requiresQuestion" from ServerUtils
     * for this specific game + round;
     * -> increases the round number;
     * -> gets the current question type, shows the corresponding scene and calls
     * the setup method for that specific scene;
     */
    public void setUpRound() {
        if(currentRoundNumber == 20) {
            ServerUtils.addPlayer(player);
            showLeadearboard();
            return;
        }
        question = ServerUtils.requireQuestion(game.getId(), currentRoundNumber);
        currentRoundNumber++;

        Question.QuestionType type = question.getType();
        switch(type) {
            case OPEN -> {
                showOpenQ();
                openQCtrl.setUpOpen();
                break;
            }
            case COMPARISON, MORE_ENERGY -> {
                showMoreEnergyQ();
                moreECtrl.setUpMoreEnergy();
                break;
            }
            case ENERGY_GUESS -> {
                showMultiChoiceQ();
                multiCtrl.setUpEnergyGuess();
                break;
            }
            default -> {}
        }
    }
}
