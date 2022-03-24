package client.scenes;

import client.utils.ServerUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javax.inject.Inject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class LobbyCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private Timer timer;
    @FXML
    private Button startGame;

    @FXML
    private ListView<String> playerList = new ListView<>();

    @Inject
    public LobbyCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
        timer = new Timer();
    }
    public void initialize(){
        TimerTask refreshTask = new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        };
        TimerTask startTask = new TimerTask() {
            @Override
            public void run() {
                startRounds();
            }
        };
        timer.schedule(refreshTask,0,1000);
        timer.schedule(startTask,0,1000);
    }
    public void start(){
        server.setStatus(true);
    }
    public void refresh(){
        List<String> names = server.getGame().getPlayers()
                .stream().map(p->p.getName()).collect(Collectors.toList());
        playerList.setItems(FXCollections.observableList(names));
    }
    public void startRounds() {
       if(server.getStatus())mainCtrl.showMultiChoiceQ(mainCtrl.getPlayer());
    }
}
