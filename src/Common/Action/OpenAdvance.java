package Common.Action;

import CommonConstance.SetStage;
import frmUserManagement.Advance.FXMLAdvanceController;
import frmUserManagement.FXMLUserManagementController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OpenAdvance implements EventHandler<ActionEvent> {

    @FXML
    private final Label lbUserSession;

    public OpenAdvance(Label userSession) {
        this.lbUserSession = userSession;
    }

    @Override
    public void handle(ActionEvent e) {
        Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/Advance/FXMLAdvance.fxml"));
            Parent root = loader.load();
            FXMLAdvanceController display = loader.getController();
            display.getUserSession(lbUserSession.getText());
            Scene scene = new Scene(root);
            SetStage.setStage(stage, scene, 600, 400);
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
