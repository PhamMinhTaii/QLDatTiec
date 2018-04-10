package Login;

import Common.Action.LoginMain;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import CommonConstance.AlertOfMe;

public class FXMLLoginController implements Initializable {

    private final LoginMain login = new LoginMain();
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassWord;
    @FXML
    private Button btnOpenMain;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnOpenMain.setOnAction(new OpenMain());
        txtPassWord.setOnKeyPressed(new OpenMainUsingKeyEvent());
    }

    @FXML
    private void exitAction(ActionEvent event) {
        Optional<ButtonType> result = AlertOfMe.alertResult(Alert.AlertType.INFORMATION, "Thoát ?");
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    class OpenMain implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (login.login(txtUser.getText(), txtPassWord.getText())) {
                ((((Node) (e.getSource())).getScene()).getWindow()).hide();
            }
        }
    }

    class OpenMainUsingKeyEvent implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent e) {
            if (e.getCode().equals(KeyCode.ENTER)) {
                if (login.login(txtUser.getText(), txtPassWord.getText())) {
                    ((((Node) (e.getSource())).getScene()).getWindow()).hide();
                }
            }
        }
    }
}
