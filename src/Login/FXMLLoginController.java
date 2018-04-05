package Login;
import BUS.UserBUS;
import CommonConstance.SetStage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.FXMLMainController;

public class FXMLLoginController implements Initializable {

    private UserBUS userBUS;

    @FXML private TextField txtUser;
    @FXML private TextField txtPassWord;
    @FXML private Button btnOpenMain;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnOpenMain.setOnAction(new OpenMain());
    }
    @FXML
    private void exitAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure ?");

        alert.setTitle("Exit");
        alert.setHeaderText(null); // không thiết lập thông tin header    
        ButtonType btnOK = ButtonType.OK; // tạo btn OK
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE); // tạo button cancel
        alert.getButtonTypes().setAll(btnOK, btnCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnOK) {
            Platform.exit();
          //  System.exit(0);
        }
    }
     class OpenMain implements EventHandler<ActionEvent>{  

        @Override
        public void handle(ActionEvent e) {
            userBUS = new UserBUS();

            int result = userBUS.Login(txtUser.getText(),txtPassWord.getText());

            if (result == 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/FXMLMain.fxml"));
                    Parent root = loader.load();
                    FXMLMainController display = loader.getController();
                    display.getsessionUser(txtUser.getText()); // chuyển tài khoản ssang form User
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    //SetStage.setStage(stage, scene, null, null);
                    stage.setScene(scene);
                    stage.show();                   
                    ((((Node)(e.getSource())).getScene()).getWindow()).hide();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else {       
                alert(result);
            }            
        }        
    }
     
     public void alert(int result){
         Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Đăng Nhập");
                alert.setHeaderText(null);
                switch (result) {
                    case -1:
                        alert.setContentText("Tài Khoản Đang Bị Khoá");
                        break;
                    case -2:
                        alert.setContentText("Mật Khẩu Không Đúng");
                        break;
                    default:
                        alert.setContentText("Đăng Nhập Sai. Xin Kiểm Tra Lại");
                        break;
                }
                alert.show();
     }
     
}



 
