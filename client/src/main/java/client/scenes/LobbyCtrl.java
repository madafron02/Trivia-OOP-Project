package client.scenes;

import client.utils.ServerUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javax.inject.Inject;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

public class LobbyCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private Timer timer;
    @FXML
    private Button startGame;

    @FXML
    private ListView<String> playerList = new ListView<>();

    /**
     * create the lobby instance
     * @param server an instance that can send http request to the server
     * @param mainCtrl the main controller
     */
    @Inject
    public LobbyCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
        timer = new Timer();
    }

    /**
     * request for the new status per second
     * refresh the list page and travel to the game page if a player clicks the start button
     */

    /*
    public void initialize(){
        TimerTask refreshTask = new TimerTask() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(()->{
                    refresh();
                });
            }
        };
        TimerTask startTask = new TimerTask() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(()->{
                    startRounds();
                });
            }
        };
        timer.schedule(refreshTask,0,1000);
        timer.schedule(startTask,0,1000);
    }
    */

    /**
     * send the signal to the server which indicate that a new game is going to start
     */
    public void start(){
        server.setStatus(true);
    }

    /**
     * require for the newest player list and show it in the page
     */
    public void refresh(){
        List<String> names = server.getGame().getPlayers()
                .stream().map(p->p.getName()).collect(Collectors.toList());
        playerList.setItems(FXCollections.observableList(names));
    }

    /**
     * travel to the game page if it is time to start
     */
    public void startRounds() {
        if(server.getStatus()){
            timer.cancel();
            mainCtrl.showMoreEnergyQ(mainCtrl.getPlayer());
        }
    }
}
