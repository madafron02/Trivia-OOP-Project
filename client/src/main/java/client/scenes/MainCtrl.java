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

import commons.Game;
import commons.Player;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainCtrl {
    private boolean isSingleMode;
    private Player player;
    private Game game;
    private Stage primaryStage;

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

    private  IngameLeaderboardCtrl igLeaderboardCtrl;
    private Scene igLeaderboard;

    private AllTimeLeaderboardCtrl leaderboardCtrl;
    private Scene leaderboard;

    private WinnersCtrl winnersCtrl;
    private Scene winners;

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
                              Pair<MoreEnergyQCtrl, Parent> moreEnergy) {
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
        this.nameSelect = new Scene(nameSelectCtrlParentPair.getValue(), Color.web("#011826"));
        this.nameSelectCtrl = nameSelectCtrlParentPair.getKey();

        showSplash();
        primaryStage.show();
        this.isSingleMode = true;
    }

    public MultiChoiceQCtrl getMultiCtrl() {
        return multiCtrl;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void primarySetSceneOnly() {
        primaryStage.setScene(moreEnergy);
    }

    public void showSplash() {
        primaryStage.setTitle("Quizzzz!");
        primaryStage.setScene(opening);
        primaryStage.setMinHeight(900);
        primaryStage.setMinWidth(1440);
    }

    public void showMultiChoiceQ(Player player) {
        primaryStage.setTitle("Questions");
        primaryStage.setScene(multiChoice);
        multiCtrl.setPlayer(player);
        multiCtrl.setQuestion();
        multiCtrl.setCurrentRoundNumber(0);
    }

    public void showMoreEnergyQ( Player player) {
        primaryStage.setTitle("Question");
        primaryStage.setScene(moreEnergy);
        moreECtrl.setPlayer(player);
        moreECtrl.setQuestion();
        moreECtrl.setCurrentRoundNumber(0);
    }

    public void showEnergyQ(Pair<OpenQCtrl, Parent> openQPair) {
        this.openQ = new Scene(openQPair.getValue(), Color.web("#011826"));
        primaryStage.setTitle("Question");
        primaryStage.setScene(openQ);
    }

    public boolean isSingleMode() {
        return isSingleMode;
    }

    public void setSingleMode(boolean type) {
        isSingleMode = type;
    }

    public void showNameSelect() {
        primaryStage.setTitle("Name Screen");
        primaryStage.setScene(nameSelect);
    }

    public void showLobby() {
        primaryStage.setTitle("Waiting room");
        primaryStage.setScene(lobby);
    }

    public LobbyCtrl getLobby() {
        return lobbyCtrl;
    }

    public void showCorrect() {
        primaryStage.setTitle("Correct Answer!");
        primaryStage.setScene(correct);
    }

    public void showHelp() {
        primaryStage.setTitle("Help");
        primaryStage.setScene(help);
    }

    public void showWrong() {
        primaryStage.setTitle("Wrong answer!");
        primaryStage.setScene(wrong);
    }

    public void showIgLeaderboard() {
        primaryStage.setTitle("In-game leaderboard");
        primaryStage.setScene(igLeaderboard);
    }

    public void showWhatIsMoreExpensive() {
        primaryStage.setTitle("What is more expensive");
        primaryStage.setScene(moreEnergy);
    }

    public void showLeadearboard() {
        primaryStage.setTitle("All-time leaderboard");
        primaryStage.setScene(leaderboard);
    }

    public void showWinners() {
        primaryStage.setTitle("Winners");
        primaryStage.setScene(winners);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public Game getGame(){
        return this.game;
    }
}
