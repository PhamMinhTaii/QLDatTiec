
package Common.Action;

import CommonConstance.AlertOfMe;
import Login.Login;
import frmUserManagement.FXMLUserManagementController;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class LogOut implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        
         Optional<ButtonType> result = AlertOfMe.alertResult(Alert.AlertType.INFORMATION, "Đăng Xuất ?");
        if (result.get() == ButtonType.OK) {
           Login Login = new Login();
            Stage stage = new Stage();
            try {
                 
                Login.start(stage);                
                 ((((Node)(e.getSource())).getScene()).getWindow()).hide();  
            } catch (Exception ex) {
                Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
    }
    
}
