package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Game;
import commons.Player;
import jakarta.ws.rs.WebApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;

public class NameSelectCtrl {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private Player player;
    @FXML
    private TextField nameInput;

    @FXML
    private Label nameCheck;

    @FXML
    private Button checkButton;
    private static boolean checked = false;

    @FXML
    private Button toHelp;

    @FXML
    private Button toLobby;

    @FXML
    private Button goBack;

    @Inject
    public NameSelectCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;

    }

    public void checkName(){
        if(checked){
            nameCheck.setText("Your name has already been checked");
            return;
        }
        String name = nameInput.getText();
        if(name.isEmpty()){
            nameCheck.setText("Please enter a valid name");
            return;
        }
        try {
            Player thisplayer = new Player(nameInput.getText());
            if(!mainCtrl.isSingleMode()){
                Game currentGame = server.getGame();
                boolean flag = true;
                for(Player player: currentGame.getPlayers()) {
                    if (player.getName().equals(thisplayer.getName())) {
                        flag = false;
                        break;
                    }
                }
                if(!flag){
                    nameInput.setText("this name is already taken");
                    return;
                }
                server.addPlayerToCurrentGame(thisplayer);
            }
            player = thisplayer;
            server.addPlayer(thisplayer);
        } catch (WebApplicationException e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
        nameCheck.setText("Your name is saved successfully!");
        checked = true;
    }

    public void goToLobby(){
        if(!checked){
            nameCheck.setText("Please check your name before you start");
            return;
        }
        if(mainCtrl.isSingleMode())mainCtrl.showMultiChoiceQ(player);
        else mainCtrl.showLobby();
    }

    public void goToHelp() {
        mainCtrl.showHelp();
    }

    public void goBack() {
        mainCtrl.showSplash();
    }
}
