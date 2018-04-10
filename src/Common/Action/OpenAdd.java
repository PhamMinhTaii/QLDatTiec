package Common.Action;

import BUS.UserBUS;
import CommonConstance.AlertOfMe;
import CommonConstance.ComBoBox;
import CommonConstance.SetStage;
import frmUserManagement.Add.FXMLAddController;
import frmUserManagement.FXMLUserManagementController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OpenAdd implements EventHandler<ActionEvent> {
    
    private final UserBUS userBUS = new UserBUS();
    private final Label lbUserSession;
    
    public  OpenAdd(Label userSession){
        this.lbUserSession = userSession;
    }

    @Override
    public void handle(ActionEvent e) {
        Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();
        try {
             if (!userBUS.findRole(lbUserSession.getText()).equals(ComBoBox.admin)){
                     AlertOfMe.alert("Tài Khoản Không Có Quyền Thêm Users ");
                     return;                     
                 }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/Add/FXMLAdd.fxml"));
            Parent root = loader.load();
            FXMLAddController display = loader.getController();
            display.getUserSession(lbUserSession.getText());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Thêm Nhân Viên");
            SetStage.setStage(stage, scene, 500, 550);
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
