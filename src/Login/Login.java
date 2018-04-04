package Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Hệ Thống Tiệc Cưới T&T");
        stage.initStyle(StageStyle.UTILITY);
        stage.setMaxHeight(400);
        stage.setMaxWidth(500);
        stage.show();
// Test from User
//        Parent root = FXMLLoader.load(getClass().getResource("/frmUserManagement/FXMLUserManagement.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Hệ Thống Tiệc Cưới T&T");
//        stage.initStyle(StageStyle.UTILITY);
//        stage.setMaxWidth(645);
//        stage.setMinWidth(645);
//        stage.setMaxHeight(500);
//        stage.setMinHeight(500);
//        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
