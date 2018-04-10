package main;

import Common.Action.LogOut;
import CommonConstance.SetStage;
import frmUserManagement.FXMLUserManagementController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLMainController implements Initializable {

    @FXML
    private Label lbUserSession;
    @FXML
    private Button btnLogout;
    @FXML
    private DatePicker dpicker;
    @FXML
    private Label lblA1;
    @FXML
    private Label lblA2;
    @FXML
    private Label lblA3;
    @FXML
    private Label lblA4;
    @FXML
    private Label lblB1;
    @FXML
    private Label lblB2;
    @FXML
    private Label lblB3;
    @FXML
    private Label lblB4;
    @FXML
    private Label lblC1;
    @FXML
    private Label lblC2;
    @FXML
    private Label lblC3;
    @FXML
    private Label lblC4;
    @FXML
    private Label lblD1;
    @FXML
    private Label lblD2;
    @FXML
    private Label lblD3;
    @FXML
    private Label lblD4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLogout.setOnAction(new LogOut());

    }

    @FXML
    private void datTiecCuoiAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Books/FXMLBook.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void moveUserForm(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/FXMLUserManagement.fxml"));
        Parent root = loader.load();
        FXMLUserManagementController display = loader.getController();
        display.getSessionUser(lbUserSession.getText());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hệ Thống Tiệc Cưới T&T");
        stage.initStyle(StageStyle.UTILITY);
        SetStage.setStage(stage, scene, 645, 550);
    }

    public void getsessionUser(String userSession) {
        lbUserSession.setText(userSession);
    }

}
