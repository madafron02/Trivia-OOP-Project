package client.scenes;

import client.utils.ActivityUtil;
import commons.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.inject.Inject;


public class AdminCtrl {

    private final ActivityUtil activityUtil;
    private final MainCtrl mainCtrl;

    @FXML
    private TableView<Activity> table;
    @FXML
    private TableColumn<Activity, Long> activID;
    @FXML
    private TableColumn<Activity, String> activTitle;
    @FXML
    private TableColumn<Activity, Long> activConsum;
    @FXML
    private Button backBTN;
    @FXML
    private TextField deleteId;
    @FXML
    private Label errorM;

    @Inject
    public AdminCtrl(ActivityUtil activityUtil, MainCtrl mainCtrl){
        this.activityUtil = activityUtil;
        this.mainCtrl = mainCtrl;
    }

    public void initialize(){
        this.activID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.activTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.activConsum.setCellValueFactory(new PropertyValueFactory<>("consumption"));
        ObservableList<Activity> observableList =
                FXCollections.observableList(activityUtil.getAll());
        table.setItems(observableList);
    }

    public void goToAddActivity(){
        mainCtrl.showAddActivity();
    }

    public void deleteActivity(){
            try{
                activityUtil.deleteByID(Long.parseLong(deleteId.getText()));
                errorM.setVisible(false);
                initialize();
            }catch (Exception e) {
                errorM.setVisible(true);
            }
    }

    public void goBack(){
        mainCtrl.showSplash();
    }
}
