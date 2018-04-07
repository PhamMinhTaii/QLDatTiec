
package Common.Action;

import Login.Login;
import frmUserManagement.FXMLUserManagementController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LogOut implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
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
