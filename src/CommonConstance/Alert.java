package CommonConstance;

import java.util.Optional;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Alert {
    
    public  static void alert(String content){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR,content);
            alert.setTitle("Thông Báo");
            alert.setHeaderText(null);
            alert.show();
    }
    
    public  static Optional<ButtonType> alertResult(String content){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION,content);
        alert.setTitle("Thông Báo");
        alert.setHeaderText(null);
         ButtonType btnOK = ButtonType.OK; // tạo btn OK
        ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE); // tạo button cancel
        alert.getButtonTypes().setAll(btnOK, btnCancel);
        return alert.showAndWait();
    }
}
