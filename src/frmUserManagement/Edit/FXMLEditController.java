package frmUserManagement.Edit;

import Common.Action.BackFrmUser;
import entity.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLEditController implements Initializable {
    
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
    boolean role = false;
    boolean active = false;
    boolean gender = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComBoBox();
        getComBoBox(role, active, gender);
        btnBack.setOnAction(new BackFrmUser());
        
    }
    
    public void setComBoBox() {
        
//        List<String> listRole = new ArrayList<>();
//        listRole.add(1, "Quản Lý");
//        listRole.add(0, "Nhân Viên");
//        String i = listRole.get(0);
        
        cbRole.getItems().addAll("Quản Lý", "Nhân Viên");        
        cbActive.getItems().addAll("Khoá", "Kích Hoạt");        
        cbGender.getItems().addAll("Nữ", "Nam");        
        
    }
    
    public void getComBoBox(boolean role, boolean active, boolean gender) {
        if (role == true) {
            cbRole.setValue("Quản Lý");
        }
        if (active == true) {
            cbActive.setValue("Kích Hoạt");
        }        
        if (gender == true) {
            cbGender.setValue("Nam");
        }        
    }
    
    public void getData(User user) {
        
        txtUserName.setText(user.getUserName());
        txtPassWord.setText(user.getPassword());
        txtConfirm.setText(user.getPassword());
        txtFistName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        //, cbRole.setValue(user.isRole());
        txtAddress.setText(user.getAddress());
        txtEmail.setText(user.getEmail());
        
    }
    
}
