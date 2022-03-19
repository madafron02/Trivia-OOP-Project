package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Player;
import jakarta.ws.rs.WebApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;

public class NameSelectCtrl {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

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
        nameCheck.setText("Your name is saved successfully!");
        checked = true;
    }

    public void addPlayer() {
        Player player = new Player(nameInput.getText());
        server.addPlayer(player);
    }

    public void goToLobby(){
        if(!checked){
            nameCheck.setText("Please check your name before you start");
            return;
        }
        try {
            addPlayer();
        } catch (WebApplicationException e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
        LobbyCtrl lobbyCtrl = mainCtrl.getLobby();
        lobbyCtrl.addToList();

        mainCtrl.showLobby();
    }

    public void goToHelp() {
        mainCtrl.showHelp();
    }
}
