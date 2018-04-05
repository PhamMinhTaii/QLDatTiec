package Common.Action;

import CommonConstance.SetStage;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BackFrmUser implements EventHandler<ActionEvent> {

    private final TextField txtUserSession; // txt nhận userSession để truyền vè form User khi Back

     public  BackFrmUser(TextField userSession){
         this.txtUserSession = userSession;
     }

    @Override
    public void handle(ActionEvent e) {
        Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/FXMLUserManagement.fxml"));
            Parent root = loader.load();
            FXMLUserManagementController display = loader.getController();
            display.getSessionUser(txtUserSession.getText());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Hệ Thống Tiệc Cưới T&T");
            SetStage.setStage(stage, scene, 645, 500);
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
