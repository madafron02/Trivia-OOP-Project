package client.scenes;

import client.utils.ServerUtils;
import commons.Game;
import commons.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

public class WinnersCtrl {

    private final MainCtrl mainCtrl;
    private final ServerUtils server;
    @FXML
    private Button backToMenu;

    @FXML
    private Label firstPoints;
    @FXML
    private Label firstName;
    @FXML
    private Label secondPoints;
    @FXML
    private Label secondName;
    @FXML
    private Label thirdPoints;
    @FXML
    private Label thirdName;

    @Inject
    public WinnersCtrl(MainCtrl mainCtrl, ServerUtils server) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }
    public void setUp(){
        Color blue = Color.web("8FD1D9");
        secondPoints.setBackground(new Background(new BackgroundFill(blue,null,null)));
        thirdPoints.setBackground(new Background(new BackgroundFill(blue,null,null)));
        Game game = server.requireGame(mainCtrl.getGame().getId());
        List<Player> playerList = game.getPlayers();
        playerList.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getPoints()-o1.getPoints();
            }
        });
        if(playerList.size()>=1){
            firstName.setText(playerList.get(0).getName());
            firstPoints.setText(String.valueOf(playerList.get(0).getPoints()));
        }
        if(playerList.size()>=2){
            secondName.setText(playerList.get(1).getName());
            secondPoints.setText(String.valueOf(playerList.get(1).getPoints()));
        }
        if(playerList.size()>=3){
            thirdName.setText(playerList.get(2).getName());
            thirdPoints.setText(String.valueOf(playerList.get(2).getPoints()));
        }
    }
    public void goToSplash() {
        mainCtrl.showSplash();
    }

}
