//package Common.Action;
//
//import frmUserManagement.Edit.FXMLEditController;
//import frmUserManagement.FXMLUserManagementController;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class OpenEdit implements EventHandler<ActionEvent> {
//
//    @Override
//    public void handle(ActionEvent e) {
//
//        Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();     
//        try {
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/Edit/FXMLEdit.fxml"));
//            Parent root = loader.load();
//            FXMLEditController display = loader.getController();
//            
//            display.getData("Tình", "pass");
//            
//            
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.setMaxWidth(500);
//            stage.setMinWidth(500);
//            stage.setMaxHeight(550);
//            stage.setMinHeight(550);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//}
