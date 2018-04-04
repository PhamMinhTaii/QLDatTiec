package frmUserManagement;

import BUS.UserBUS;
import Common.Action.OpenAdd;
import CommonConstance.Alert;
import CommonConstance.SetStage;
import entity.User;
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
import frmUserManagement.Edit.FXMLEditController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;

public class FXMLUserManagementController implements Initializable {

    UserBUS userBUS = new UserBUS();
    private ObservableList<User> lsUsers;
    @FXML
    private Label lbUserSession;    // truyền userSession
    private User userEdit; // đói tượng truyền dữ liệu
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnAdd;
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

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.loadUser();
        getRowClick();
        btnAdd.setOnAction(new OpenAdd());
        btnEdit.setOnAction(new OpenEdit());
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
                }
            });
            return row;
        });
    }

    public class OpenEdit implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            Stage stage = (Stage) (((Node) (e.getSource())).getScene()).getWindow();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/Edit/FXMLEdit.fxml"));
                Parent root = loader.load();
                FXMLEditController display = loader.getController();
                if (userEdit == null) { // chưa click trên table view thì user sẽ null;
                    Alert.alert("Chưa chọn User để thay đổi");
                    return;
                }
                display.getData(userEdit);
                Scene scene = new Scene(root);
                SetStage.setStage(stage, scene, 500, 550);
            } catch (IOException ex) {
                Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public  void getSessionUser(String userSession){
        lbUserSession.setText(userSession);
    }
}
