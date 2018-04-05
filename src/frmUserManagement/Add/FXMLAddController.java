package frmUserManagement.Add;

import BUS.UserBUS;
import Common.Action.BackFrmUser;
import CommonConstance.Alert;
import CommonConstance.ComBoBox;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class FXMLAddController implements Initializable {

    private UserBUS userBUS;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtUserSession;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassWord;
    @FXML
    private PasswordField txtConfirm;
    @FXML
    private TextField txtFistName;
    @FXML
    private ComboBox<String> cbRole;
    @FXML
    private ComboBox<String> cbActive;
    @FXML
    private ComboBox<String> cbGender;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComBoBox.setComBoBox(cbRole,cbActive,cbGender);
        btnBack.setOnAction(new BackFrmUser(txtUserSession));

    }
    
    public void addUser(ActionEvent event) {

        userBUS = new UserBUS();
        String id = UUID.randomUUID().toString();

        User user = new User(id, txtUserName.getText(), txtPassWord.getText(), txtFistName.getText(),
                txtLastName.getText(), cbRole.getValue(), cbActive.getValue(), txtAddress.getText(),
                txtEmail.getText(), cbGender.getValue());

        try {
            int kq = userBUS.addUser(user, txtConfirm.getText());
            setAlert(kq);
        } catch (Exception e) {
            Alert.alert("Lỗi Hệ Thống !!");
        }
    }

    public void setAlert(int kq) {
        if (kq == -10) {
            Alert.alert("Tài Khoản Đã Tồn Tại");
            txtUserName.clear();
        }
        if (kq == -1) {
            Alert.alert("Tài Khoản Không Hợp Lệ");
            txtUserName.clear();
        }
        if (kq == -2) {
            Alert.alert("Mật Khẩu Không Hợp Lệ");
            txtConfirm.clear();
            txtPassWord.clear();
        }
        if (kq == -3) {
            Alert.alert("Xác Nhận Mật Khẩu Sai");
            txtPassWord.clear();
            txtConfirm.clear();
        }
        if (kq == -4) {
            Alert.alert("Tên Hoặc Tên Đệm Không Hợp Lệ");
            txtFistName.clear();
            txtLastName.clear();
        }
        if (kq == -5) {
            Alert.alert("Email Không Hợp Lệ");
            txtEmail.clear();
        }
        if (kq == 1) {
            Alert.alert("Thêm Thành Công !!");
            clear();

        }
    }

    public void clear() {
        txtPassWord.clear();
        txtConfirm.clear();
        txtFistName.clear();
        txtLastName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtUserName.clear();
    }
    // phunogư thức nhận dữ lệu userSesion lên form
    public void getUserSession(String userSession){
        txtUserSession.setText(userSession);
    }
}
