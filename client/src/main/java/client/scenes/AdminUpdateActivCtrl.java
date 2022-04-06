package client.scenes;

import client.utils.ActivityUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class AdminUpdateActivCtrl {

    @FXML
    private TextField pathId;
    @FXML
    private TextField imagePath;
    @FXML
    private TextField title;
    @FXML
    private TextField consumption;
    @FXML
    private TextField id;
    @FXML
    private Label errorId;
    @FXML
    private Label errorUp;
    @FXML
    private Button back;
    @FXML
    private Button updatePath;
    @FXML
    private Button updateIMG;
    @FXML
    private Button updateTitle;
    @FXML
    private Button updateConsum;

    private MainCtrl mainCtrl;
    private final ActivityUtil activityUtil;
    private AdminCtrl adminCtrl;

    @Inject
    public AdminUpdateActivCtrl(ActivityUtil activityUtil
            , MainCtrl mainCtrl, AdminCtrl adminCtrl){
        this.activityUtil = activityUtil;
        this.mainCtrl = mainCtrl;
        this.adminCtrl = adminCtrl;
    }

    public void updateFileP(){
        long id;
        try {
            id = Long.parseLong(this.id.getText());
            errorId.setVisible(false);
            try{
                activityUtil.updateFileP(id, this.pathId.getText());
                errorUp.setVisible(false);
                adminCtrl.initialize();
                pathId.clear();
            }catch (Exception e){
                errorUp.setVisible(true);
                System.out.println("Entered File Path is invalid!");
            }
        }catch(Exception e){
            errorId.setVisible(true);
            System.out.println("Entered ID is invalid!");
        }
    }

    public void updateImage(){
        long id;
        try {
            id = Long.parseLong(this.id.getText());
            errorId.setVisible(false);
            try{
                activityUtil.updateImageP(id, this.imagePath.getText());
                errorUp.setVisible(false);
                adminCtrl.initialize();
                imagePath.clear();
            }catch (Exception e){
                errorUp.setVisible(true);
                System.out.println("Entered Image Path is invalid!");
            }
        }catch(Exception e){
            errorId.setVisible(true);
            System.out.println("Entered ID is invalid!");
        }
    }
    public void updateTitle(){
        long id;
        try {
            id = Long.parseLong(this.id.getText());
            errorId.setVisible(false);
            try{
                activityUtil.updateTitle(id, this.title.getText());
                errorUp.setVisible(false);
                adminCtrl.initialize();
                title.clear();
            }catch (Exception e){
                errorUp.setVisible(true);
                System.out.println("Entered Title is invalid!");
            }
        }catch(Exception e){
            errorId.setVisible(true);
            System.out.println("Entered ID is invalid!");
        }
    }
    public void updateConsum(){
        long id;
        try {
            id = Long.parseLong(this.id.getText());
            errorId.setVisible(false);
            try{
                activityUtil.updateConsum(id, Long.parseLong(this.consumption.getText()));
                errorUp.setVisible(false);
                adminCtrl.initialize();
                consumption.clear();
            }catch (Exception e){
                errorUp.setVisible(true);
                System.out.println("Entered Consumption is invalid!");
            }
        }catch(Exception e){
            errorId.setVisible(true);
            System.out.println("Entered ID is invalid!");
        }
    }

    public void goBack(){
        mainCtrl.showSplash();
    }
}

