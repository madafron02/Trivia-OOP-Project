package client.scenes;

import client.utils.ActivityUtil;
import commons.Activity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class AdminAddActivCtrl {

    @FXML
    private TextField pathId;
    @FXML
    private TextField imagePath;
    @FXML
    private TextField title;
    @FXML
    private TextField consumption;
    @FXML
    private Label errorM;
    @FXML
    private Button back;
    @FXML
    private Button add;

    private MainCtrl mainCtrl;
    private final ActivityUtil activityUtil;
    private AdminCtrl adminCtrl;

    @Inject
    public AdminAddActivCtrl(ActivityUtil activityUtil
            , MainCtrl mainCtrl, AdminCtrl adminCtrl){
        this.activityUtil = activityUtil;
        this.mainCtrl = mainCtrl;
        this.adminCtrl = adminCtrl;
    }

    public void addActiv(){
            try {
                Activity temp = new Activity(pathId.getText(), imagePath.getText()
                        , title.getText(), Long.parseLong(consumption.getText()));
                activityUtil.addActivity(temp);
                adminCtrl.initialize();;
                pathId.clear();
                imagePath.clear();
                title.clear();
                consumption.clear();
                errorM.setVisible(false);
            }catch(Exception e){
                errorM.setVisible(true);
                System.out.println("Fields not filled out properly!");
            }
    }

    public void goBack(){
        mainCtrl.showSplash();
    }
}
