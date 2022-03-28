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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;

import client.scenes.*;
import com.google.inject.Injector;

import javafx.application.Application;
import javafx.stage.Stage;
import javax.sound.sampled.*;

public class Main extends Application {

    private static final Injector INJECTOR = createInjector(new MyModule());
    private static final MyFXML FXML = new MyFXML(INJECTOR);

    public static void main(String[] args) throws URISyntaxException,
            IOException, UnsupportedAudioFileException, LineUnavailableException {
        String absolutePath = FileSystems.getDefault()
                .getPath("src/main/resources/Halloween Lobby Music.wav")
                .normalize()
                .toAbsolutePath()
                .toString();
        File file = new File(absolutePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        var splash = FXML.load(SplashCtrl.class, "client", "scenes", "Splash.fxml");
        splash.getValue().getStylesheets().add("Images/splashBG.css");

        var lobby = FXML.load(LobbyCtrl.class, "client", "scenes", "Lobby.fxml");
        var correct = FXML.load(CorrectCtrl.class, "client", "scenes", "Correct.fxml");
        var help = FXML.load(HelpCtrl.class, "client", "scenes", "Help.fxml");
        var wrong = FXML.load(WrongCtrl.class, "client", "scenes", "Wrong.fxml");
        var igLeaderboard = FXML.load(IngameLeaderboardCtrl.class,
                "client", "scenes", "IngameLeaderboard.fxml");
        var leaderboard = FXML.load(AllTimeLeaderboardCtrl.class,
                "client", "scenes", "AllTimeLeaderboard.fxml");
        var winners = FXML.load(WinnersCtrl.class, "client", "scenes", "Winners.fxml");
        var multiChoice =
                FXML.load(MultiChoiceQCtrl.class, "client", "scenes", "MultiChoiceQ.fxml");
        var moreEnergy = FXML.load(MoreEnergyQCtrl.class, "client", "scenes", "MoreEnergyQ.fxml");
        var mainCtrl = INJECTOR.getInstance(MainCtrl.class);

        var nameSelect = FXML.load(NameSelectCtrl.class, "client", "scenes", "NameSelect.fxml");
        nameSelect.getValue().getStylesheets().add("Images/selectName.css");

        mainCtrl.initializeNew(primaryStage, splash, nameSelect, lobby,
                help, igLeaderboard, leaderboard, correct, wrong, winners,
                multiChoice,moreEnergy);
    }
}
