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

    /**
     * create the nameselect controller
     * @param server an instance that can send http request to the server
     * @param mainCtrl main controller
     */
    @Inject
    public NameSelectCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;

    }

    /**
     * check if the name is valid
     * a palyer connot check again when its name checked successfully
     * the input is invalid if it is null
     * the input is invalid for the mutiplayer mode
     * if someone with the same name exists in the current round
     * if the name is valid, send it to the server and save it in the main controller
     */
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
                    nameCheck.setText("this name is already taken");
                    return;
                }
                thisplayer.setMulti(true);
                mainCtrl.setGame(server.addPlayerToCurrentGame(thisplayer));
                for(Player player: mainCtrl.getGame().getPlayers()){
                    if(player.getName().equals(thisplayer.getName())){
                        mainCtrl.setPlayer(player);
                    }
                }
            }
            else mainCtrl.setPlayer(server.addPlayer(thisplayer));
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

    /**
     * if the player haven't check his name, do nothing
     * for the single player mode, travel to the game page
     * for the mutiplayer mode, travel to the lobby
     */
    public void goToLobby(){
        if(!checked){
            nameCheck.setText("Please check your name before you start");
            return;
        }
        checked = false;
        nameCheck.setText("");
        nameInput.setText("");
        //for the single player we also require a new game but it's only for setting questions
        if(mainCtrl.isSingleMode()){
            Game game = server.addGame(new Game());
            server.setQuestion(game.getId());
            mainCtrl.setGame(game);
            mainCtrl.setUpRound();
        }
        else mainCtrl.showLobby();
    }

    /**
     * show the help page
     */
    public void goToHelp() {
        mainCtrl.showHelp();
    }

    /**
     * go back to the splash page
     */
    public void goBack() {
        mainCtrl.showSplash();
    }

    public ServerUtils getServer() {
        return server;
    }
}
