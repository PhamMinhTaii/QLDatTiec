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

public class OpenAdd implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/frmUserManagement/Add/FXMLAdd.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Thêm Nhân Viên");
            stage.setMinWidth(500);
            stage.setMaxWidth(500);
            stage.setMinHeight(550);
            stage.setMaxHeight(550);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
