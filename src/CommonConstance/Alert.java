package CommonConstance;

public class Alert {
    
    public  static void alert(String content){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR,content);
            alert.setTitle("Thông Báo");
            alert.setHeaderText(null);
            alert.show();
    }

}
