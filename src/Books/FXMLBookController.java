/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Books;

import BUS.BooksBUS;
import entity.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.persistence.Convert;

/**
 * FXML Controller class
 *
 * @author Minh Taii
 */
public class FXMLBookController implements Initializable {

    @FXML
    private ToggleGroup time;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtLastName;
    @FXML
    private RadioButton rdoNam;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton rdoNu;
    @FXML
    private ListView lvMenu;

    /**
     * Initializes the controller class.
     */
    BooksBUS bookBus = new BooksBUS();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadLvMenu();
    }

    @FXML
    private void selectAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void saveCustomerAction(ActionEvent event) {
        String sex = null;
        if (rdoNam.isSelected()) {
            sex = "Nam";
        }
        if (rdoNu.isSelected()) {
            sex = "Nữ";
        }
        Customer customer = new Customer("3", txtFirstName.getText(), txtLastName.getText(),
                txtPhone.getText(), txtAddress.getText(), sex);
        System.err.println(customer.getGender());
        int kq = bookBus.addCustomer(customer);
        if (kq == 1) {
            System.out.println("Them danh cong");
        }
    }

    // Load listview Menu
    private void loadLvMenu() {
        List<Menu> list = bookBus.loadListMenu();
        for (Menu mn : list) {
            float money=Float.parseFloat(mn.getPrice()) ;
            String giaTien=String.format("%0,3.0fVNĐ",money );
            lvMenu.getItems().add(mn.getMenuName() + " - " + giaTien);
        }       
    }

}
