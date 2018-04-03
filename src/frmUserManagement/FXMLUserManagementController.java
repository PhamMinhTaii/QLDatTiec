package frmUserManagement;

import BUS.UserBUS;
import Common.Action.OpenAdd;
import CommonConstance.Alert;
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
import javafx.scene.control.TableRow;
import javafx.stage.Stage;

public class FXMLUserManagementController implements Initializable {

    UserBUS userBUS = new UserBUS();

    private ObservableList<User> lsUsers;   
    private User userEdit ; // đói tượng truyền dữ liệu
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
        btnEdit.setOnAction(new OpenEdit());
        btnAdd.setOnAction(new OpenAdd());
        rowClick();

    }

    public void loadUser() {
        
        lsUsers = FXCollections.observableArrayList();
        List<User> list = null;
        try {
             list = userBUS.findListUser();
            
        } catch (Exception e) {
             Alert.alert("Lỗi Load Dữ Liệu");
        }
        list.forEach(e -> {
                this.lsUsers.add(e);
            });
        tbUsers.setItems(this.lsUsers);
        cUserName.setCellValueFactory(new PropertyValueFactory("userName"));
        cFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        cLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        cRole.setCellValueFactory(new PropertyValueFactory("role"));
        cActibe.setCellValueFactory(new PropertyValueFactory("active"));
    }

    public void rowClick() {
        this.tbUsers.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1 && !row.isEmpty()) {
                    userEdit =new User(row.getItem());                  
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

                display.getData(userEdit);
                
                System.out.println(userEdit.getId() + " " + userEdit.getUserName());

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaxWidth(500);
                stage.setMinWidth(500);
                stage.setMaxHeight(550);
                stage.setMinHeight(550);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLUserManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
