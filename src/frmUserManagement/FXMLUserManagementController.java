package frmUserManagement;

import BUS.UserBUS;
import Common.Action.OpenAdd;
import CommonConstance.Alert;
import CommonConstance.ComBoBox;
import CommonConstance.SetStage;
import entity.User;
import frmUserManagement.Edit.FXMLEditController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;

public class FXMLUserManagementController implements Initializable {
    
    private UserBUS userBUS = new UserBUS();
    private String userNameDel;
    private ObservableList<User> lsUsers;
    @FXML
    private Label lbUserSession;    // truyền userSession
    private User userEdit; // đói tượng truyền dữ liệu
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDel;
    @FXML
    private TableView<User> tbUsers;
    @FXML
    private TableColumn cUserName;
    @FXML
    private TableColumn cFirstName;
    @FXML
    private TableColumn cLastName;
    @FXML
    private TableColumn cRole;
    @FXML
    private TableColumn cActibe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.loadUser();
        getRowClick();
        btnAdd.setOnAction(new OpenAdd(lbUserSession));
        btnEdit.setOnAction(new OpenEdit());
        btnDel.setOnAction(new DeleteUser());
    }
    
    public void loadUser() {
        
        lsUsers = FXCollections.observableArrayList();
        List<User> list;
        try {
            list = userBUS.findListUser();
            list.forEach(e -> {
                this.lsUsers.add(e);
            });
            
        } catch (Exception e) {
            Alert.alert("Lỗi Load Dữ Liệu");
        }
        tbUsers.setItems(this.lsUsers);
        cUserName.setCellValueFactory(new PropertyValueFactory("userName"));
        cFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        cLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        cRole.setCellValueFactory(new PropertyValueFactory("role"));
        cActibe.setCellValueFactory(new PropertyValueFactory("active"));
    }
    
    public void getRowClick() {
        this.tbUsers.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1 && !row.isEmpty()) {
                    userEdit = new User(row.getItem());
                    userNameDel = row.getItem().getUserName();
                }
            });
            return row;
        });
    }
    
    class OpenEdit implements EventHandler<ActionEvent> {
        
        @Override
        public void handle(ActionEvent e) {
            Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();
            try {
                if (userEdit == null) { // chưa click trên table view thì user sẽ null;
                    Alert.alert("Chưa chọn User để thay đổi");
                    return;
                }
                 if (!userBUS.findRole(lbUserSession.getText()).equals(ComBoBox.admin)){
                     Alert.alert("Tài Khoản Không Có Quyền Thay Đỗi Thông Tin Users ");
                     return;                     
                 }                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/Edit/FXMLEdit.fxml"));
                Parent root = loader.load();
                FXMLEditController display = loader.getController();                
                display.getData(userEdit, lbUserSession.getText());
                Scene scene = new Scene(root);
                SetStage.setStage(stage, scene, 500, 550);
            } catch (IOException ex) {
                Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class DeleteUser implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try {                
                if (userNameDel == null) {
                    Alert.alert("Chưa Chọn User Để Xoá");
                } else {
                    if (userBUS.findRole(lbUserSession.getText()).equals(ComBoBox.admin)) {
                        Optional<ButtonType> result = Alert.alertResult("Chắc Chắn Xoá User : " + userNameDel);
                        if (result.get() == ButtonType.OK) {
                            if (userBUS.deleteUser(userNameDel) == 1) {
                                Alert.alert("Xoá Thành Công");
                                lsUsers.clear();
                                loadUser();
                            }
                        }
                    } else
                        Alert.alert("Tài Khoản Của Bạn Không Có Quyền Xoá");                    
                }
            } catch (Exception e) {
                Alert.alert("Lỗi Hệ Thống");
            }
        }
    }
    
    public void getSessionUser(String userSession) {
        lbUserSession.setText(userSession);
    }
}
