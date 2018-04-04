package Common.Action;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BackFrmUser implements EventHandler<ActionEvent> {
 
    @Override
    public void handle(ActionEvent e) {
        Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/frmUserManagement/FXMLUserManagement.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Hệ Thống Tiệc Cưới T&T");           
            stage.setMaxWidth(645);
            stage.setMinWidth(645);
            stage.setMaxHeight(500);
            stage.setMinHeight(500);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
