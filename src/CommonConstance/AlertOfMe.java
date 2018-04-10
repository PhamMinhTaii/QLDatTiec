package CommonConstance;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertOfMe {
    
    public  static void alert(String content){
       Alert alert = new Alert(AlertType.ERROR,content);
            alert.setTitle("Thông Báo");
            alert.setHeaderText(null);
            alert.show();
    }
    
    public  static Optional<ButtonType> alertResult(AlertType type,String content){
        Alert alert = new Alert(type,content);
        alert.setTitle("Thông Báo");
        alert.setHeaderText(null);
         ButtonType btnOK = ButtonType.OK; // tạo btn OK
        ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE); // tạo button cancel
        alert.getButtonTypes().setAll(btnOK, btnCancel);
        return alert.showAndWait();
    }
    
    public static void alertLogin(int result){
                switch (result) {
                    case -1:
                        alert("Tài Khoản Đang Bị Khoá");
                        break;
                    case -2:
                        alert("Mật Khẩu Không Đúng");
                        break;
                    default:
                        alert("Đăng Nhập Sai. Xin Kiểm Tra Lại");
                        break;
                }
     }
}
