package CommonConstance;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SetStage {

    public static void setStage(Stage stage, Scene scene, double width, double height) {
        stage.setScene(scene);
        stage.setMaxWidth(width);
        stage.setMinWidth(width);
        stage.setMaxHeight(height);
        stage.setMinHeight(height);
        stage.show();
    }

}
