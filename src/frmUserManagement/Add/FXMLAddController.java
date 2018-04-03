package frmUserManagement.Add;

import BUS.UserBUS;
import Common.Action.BackFrmUser;
import CommonConstance.Alert;
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
    @FXML private Button btnBack;

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
    boolean role = false;
    boolean active = false;
    boolean gender = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComBoBox();
        btnBack.setOnAction(new BackFrmUser());

    }

    public void setComBoBox() {

        cbRole.getItems().addAll("Quản Lý", "Nhân Viên");
        cbRole.setValue("Nhân Viên");
        cbActive.getItems().addAll("Khoá", "Kích Hoạt");
        cbActive.setValue("Kích Hoạt");
        cbGender.getItems().addAll("Nữ", "Nam");
        cbGender.setValue("Nam");

    }

    public void addUser(ActionEvent event) {

        userBUS = new UserBUS();
        String id = UUID.randomUUID().toString();

        if (cbRole.getValue().equals("Quản Lý")) {
            role = true;
        }
        if (cbActive.getValue().equals("Kích Hoạt")) {
            active = true;
        }
        if (cbGender.getValue().equals("Nam")) {
            gender = true;
        }
        
        User user = new User(id, txtUserName.getText(), txtPassWord.getText(), txtFistName.getText(),
                    txtLastName.getText(), role, active, txtAddress.getText(), txtEmail.getText(), gender);

        try {
            int kq = userBUS.addUser(user, txtConfirm.getText());
            alert(kq);
        } catch (Exception e) {
            Alert.alert("Lỗi Hệ Thống !!");
        }
    }

    public void alert(int kq) {
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

}
