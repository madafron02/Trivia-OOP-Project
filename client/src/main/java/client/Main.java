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
package client;

import static com.google.inject.Guice.createInjector;

import java.io.IOException;
import java.net.URISyntaxException;

import client.scenes.*;
import com.google.inject.Injector;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static final Injector INJECTOR = createInjector(new MyModule());
    private static final MyFXML FXML = new MyFXML(INJECTOR);

    public static void main(String[] args) throws URISyntaxException, IOException {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //var overview = FXML.load(QuoteOverviewCtrl.class,
        // "client", "scenes", "QuoteOverview.fxml");
        //var add = FXML.load(AddQuoteCtrl.class, "client", "scenes", "AddQuote.fxml");

        var splash = FXML.load(SplashCtrl.class, "client", "scenes", "Splash.fxml");
        var single = FXML.load(SingleCtrl.class, "client", "scenes", "Single.fxml");
        var lobby = FXML.load(LobbyCtrl.class, "client", "scenes", "Lobby.fxml");
        var help = FXML.load(HelpCtrl.class, "client", "scenes", "Help.fxml");
        var igLeaderboard = FXML.load(IngameLeaderboardCtrl.class,
                "client", "scenes", "IngameLeaderboard.fxml");
        var leaderboard = FXML.load(AllTimeLeaderboardCtrl.class,
                "client", "scenes", "AllTimeLeaderboard.fxml");
        var winners = FXML.load(WinnersCtrl.class, "client", "scenes", "Winners.fxml");
        var mainCtrl = INJECTOR.getInstance(MainCtrl.class);
        mainCtrl.initializeNew(primaryStage, splash, single, lobby, help, igLeaderboard, leaderboard, winners);
    }
}