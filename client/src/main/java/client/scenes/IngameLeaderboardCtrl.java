package client.scenes;

import client.utils.ServerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;

public class IngameLeaderboardCtrl {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @Inject
    public IngameLeaderboardCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    @FXML
    private Label playername1;
    @FXML
    private Label playername2;
    @FXML
    private Label playername3;
    @FXML
    private Label playername4;
    @FXML
    private Label playername5;
    @FXML
    private Label score1;
    @FXML
    private Label score2;
    @FXML
    private Label score3;
    @FXML
    private Label score4;
    @FXML
    private Label score5;




}
