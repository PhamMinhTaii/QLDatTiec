package frmUserManagement.Edit;

import BUS.UserBUS;
import Common.Action.BackFrmUser;
import CommonConstance.Alert;
import CommonConstance.ComBoBox;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLEditController implements Initializable {

    private UserBUS userBUS;
    @FXML
    private TextField txtID;
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
    private TextField txtLastName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> cbGender;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComBoBox.setComBoBox(cbRole, cbActive, cbGender);
        btnBack.setOnAction(new BackFrmUser());       
    }

    public void updateUser(ActionEvent event) {
        userBUS = new UserBUS();
        User user = new User(txtID.getText(), txtUserName.getText(), txtPassWord.getText(), txtFistName.getText(),
                txtLastName.getText(), cbRole.getValue(), cbActive.getValue(), txtAddress.getText(),
                txtEmail.getText(), cbGender.getValue());
        try {
            int kq = userBUS.updateUser(user, txtConfirm.getText());
            setAlert(kq);
        } catch (Exception e) {
            Alert.alert("Lỗi Hệ Thống !!");
        }
    }

    public void setAlert(int kq) {
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
            Alert.alert("Thay đổi Thành Công !!");
        }
    }

    // Phương thức nhận dữ liệu user từ frm User và load lên.
    public void getData(User user) {
        txtID.setText(user.getId());
        txtUserName.setText(user.getUserName());
        txtPassWord.setText(user.getPassword());
        txtConfirm.setText(user.getPassword());
        txtFistName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        cbRole.setValue(user.getRole());
        cbActive.setValue(user.getActive());
        txtAddress.setText(user.getAddress());       
        txtEmail.setText(user.getEmail());
        cbGender.setValue(user.getGender());
    }
}
