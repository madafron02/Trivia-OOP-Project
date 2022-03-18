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
            server.addPlayer(new Player(name));
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
    public void start(){
        if(!checked){
            nameCheck.setText("Please check your name before you start");
            return;
        }
        /*
        further code should be added inorder to navigate to another page
         */
        checked = false;
    }
}
