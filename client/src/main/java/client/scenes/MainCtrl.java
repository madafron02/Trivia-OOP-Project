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

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainCtrl {

    private Stage primaryStage;

    //private QuoteOverviewCtrl overviewCtrl;
    //private Scene overview;

    //private AddQuoteCtrl addCtrl;
    //private Scene add;

    private SplashCtrl splashCtrl;
    private Scene opening;

    private SingleCtrl singleCtrl;
    private Scene singleplayer;

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

    public void initializeNew(Stage primaryStage, Pair<SplashCtrl, Parent> opening,
                              Pair<SingleCtrl, Parent> singleplayer,
                              Pair<LobbyCtrl, Parent> lobbyCtrlParentPair,
                              Pair<HelpCtrl, Parent> helpCtrlParentPair,
                              Pair<IngameLeaderboardCtrl, Parent> igLeaderboardPair,
                              Pair<AllTimeLeaderboardCtrl, Parent> leaderboardPair,
                              Pair<CorrectCtrl, Parent> correctCtrlParentPair,
                              Pair<WrongCtrl, Parent> wrongCtrlParentPair) {

        this.primaryStage = primaryStage;
        this.splashCtrl = opening.getKey();
        this.opening = new Scene(opening.getValue());
        this.singleplayer = new Scene(singleplayer.getValue());
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

        showSplash();
        primaryStage.show();
    }

    public void showSplash() {
        primaryStage.setTitle("Splash");
        primaryStage.setScene(opening);
    }

    public void showSingle() {
        primaryStage.setTitle("Singleplayer Mode");
        primaryStage.setScene(singleplayer);
    }

    public void showLobby() {
        primaryStage.setTitle("Waiting room");
        primaryStage.setScene(lobby);
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

    public void showLeadearboard() {
        primaryStage.setTitle("All-time leaderboard");
        primaryStage.setScene(leaderboard);
    }

    /*
    public void showOverview() {
        primaryStage.setTitle("Quotes: Overview");
        primaryStage.setScene(overview);
        overviewCtrl.refresh();
    }

    public void showAdd() {
        primaryStage.setTitle("Quotes: Adding Quote");
        primaryStage.setScene(add);
        add.setOnKeyPressed(e -> addCtrl.keyPressed(e));
    }
    */
}