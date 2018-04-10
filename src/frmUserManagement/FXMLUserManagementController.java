package frmUserManagement;

import BUS.UserBUS;
import Common.Action.BackFrmUser;
import Common.Action.Delete;
import Common.Action.LogOut;
import Common.Action.OpenAdd;
import Common.Action.OpenAdvance;
import Common.Action.OpenEditFrm;
import CommonConstance.AlertOfMe;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLUserManagementController implements Initializable {

    private final UserBUS userBUS = new UserBUS();
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
    private Button btnAdvance;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtKeyWord;
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
    private TextField txtUserSession;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.loadUser("");
        setTableView();
        getRowClick();
        btnAdd.setOnAction(new OpenAdd(lbUserSession));
        btnEdit.setOnAction(new OpenEdit());
        btnDel.setOnAction(new DeleteUser());
        txtKeyWord.textProperty().addListener(e -> {
            lsUsers.clear();
            loadUser(txtKeyWord.getText());
        });
        btnLogOut.setOnAction(new LogOut());
        btnAdvance.setOnAction(new OpenAdvance(lbUserSession));
       // txtUserSession.setText(lbUserSession.getText());
       // btnBack.setOnAction(new BackFrmUser(txtUserSession));
    }

    public void loadUser(String keyWord) {
        lsUsers = FXCollections.observableArrayList();
        try {
            this.lsUsers.addAll(userBUS.findListUser(keyWord));
        } catch (Exception e) {
            AlertOfMe.alert("Lỗi Load Dữ Liệu");
        }
        tbUsers.setItems(this.lsUsers);

    }

    public void setTableView() {
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
            OpenEditFrm open = new OpenEditFrm();
            open.openEdit(stage, userEdit, lbUserSession.getText());
        }
    }

    class DeleteUser implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Delete del = new Delete();
            if (del.delete(userNameDel, lbUserSession.getText())) {
                lsUsers.clear();
                loadUser("");
            }
        }
    }

    public void getSessionUser(String userSession) {
        lbUserSession.setText(userSession);
    }
}
