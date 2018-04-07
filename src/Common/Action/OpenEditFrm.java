package Common.Action;

import BUS.UserBUS;
import CommonConstance.AlertOfMe;
import CommonConstance.ComBoBox;
import CommonConstance.SetStage;
import entity.User;
import frmUserManagement.Edit.FXMLEditController;
import frmUserManagement.FXMLUserManagementController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenEditFrm {

    private final UserBUS userBUS = new UserBUS();

    public void openEdit(Stage stage, User userEdit, String userSession) {
        try {
            if (userEdit == null) { // chưa click trên table view thì user sẽ null;
                AlertOfMe.alert("Chưa chọn User để thay đổi");
                return;
            }
            if (!userBUS.findRole(userSession).equals(ComBoBox.admin)) {
                AlertOfMe.alert("Tài Khoản Không Có Quyền Thay Đỗi Thông Tin Users ");
                return;
            } else {
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/Edit/FXMLEdit.fxml"));
            Parent root = loader.load();
            FXMLEditController display = loader.getController();
            display.getData(userEdit, userSession);
            Scene scene = new Scene(root);
            SetStage.setStage(stage, scene, 500, 550);
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
