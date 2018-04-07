package Common.Action;

import BUS.UserBUS;
import CommonConstance.AlertOfMe;
import Login.FXMLLoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.FXMLMainController;

public class LoginMain {

    private UserBUS userBUS;

    public void login(String userSession) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/FXMLMain.fxml"));
            Parent root = loader.load();
            FXMLMainController display = loader.getController();
            display.getsessionUser(userSession); // chuyển tài khoản ssang form User
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(String userName, String passWord) {
        userBUS = new UserBUS();
        int result = userBUS.Login(userName, passWord);
        if (result == 1) {
            login(userName);
            return true;
        }
        AlertOfMe.alertLogin(result);
        return false;
    }
}
