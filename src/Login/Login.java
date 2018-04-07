package Login;

import CommonConstance.SetStage;
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
//        Parent root = FXMLLoader.load(getClass().getResource("/frmUserManagement/Advance/FXMLAdvance.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Hệ Thống Tiệc Cưới T&T");
//        stage.initStyle(StageStyle.UTILITY);
//        SetStage.setStage(stage, scene, 600, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
