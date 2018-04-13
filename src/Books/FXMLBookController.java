/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Books;

import BUS.BooksBUS;
import CommonConstance.*;
import entity.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.persistence.Convert;

/**
 * FXML Controller class
 *
 * @author Minh Taii
 */
public class FXMLBookController implements Initializable {

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
    @FXML
    private Button btnChonMon;

    /**
     * Initializes the controller class.
     */
    BooksBUS bookBus = new BooksBUS();
    @FXML
    private VBox vbox;
    @FXML
    private Label lblTienDoAn;
    @FXML
    private ComboBox ccbConcept;
    @FXML
    private TextField txtTable;
    @FXML
    private TextField txtBackground;
    @FXML
    private TextField txtFlower;
    @FXML
    private Label lblTienConcept;
    @FXML
    private DatePicker dateDatTiec;
    @FXML
    private ComboBox ccSanh;
    @FXML
    private ComboBox ccCa;
    @FXML
    private Label lblTienService;
    @FXML
    private Label lblTrangTri;
    @FXML
    private Label lblTongTien;
    @FXML
    private Label lblDatPhong;
    @FXML
    private Label lblGiamGia;
    float tienAn, tienTrangTri, tienPhong, sum = 0;
    StringProperty stringp = new SimpleStringProperty();

    String idConcept = null;
    List<String> idMenu = new ArrayList<>();
    String idRoom = null;
    String isShift = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //loadLvMenu();
        load();

    }

    private void load() {
        getCCBConcept();
        loadCCBShift();
        loadCCBRoom();
    }

    @FXML
    private void selectAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

// Load listview Menu
    public void loadLvMenu(List<Menu> mn) {
        double tongTien = 0;
        int i = 0;
        // List<Menu> list = bookBus.loadListMenu();
        for (Menu m : mn) {
            i++;
            tienAn = Float.parseFloat(m.getPrice());
            tongTien += tienAn;
            String giaTien = String.format("%0,3.0fVNĐ", tienAn);
            lvMenu.getItems().add(i + "-" + m.getMenuName() + "   " + giaTien);
            idMenu.add(m.getMenuId());
        }
        String giaTien = String.format("%0,3.0fVNĐ", tongTien);
        lvMenu.getItems().add("-------------------------------------------");
        lvMenu.getItems().add("Tổng Tiền: " + giaTien);
        lblTienDoAn.setText(giaTien);
    }

    private void luuMonAction(ActionEvent event) {
        List<Menu> list = bookBus.loadListMenu();

    }

    public void isClosed(boolean bool) throws IOException {
        if (bool) {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLBook.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
        }
    }

    //---------------Phan ROOM-----------------//
    private void loadCCBShift() {
        ccCa.getItems().clear();
        String[] shift = {"8h Sáng", "11h Trưa", "15h Chiều", "18h Tối"};
        for (String s : shift) {
            ccCa.getItems().add(s);
        }
    }

    private void loadCCBRoom() {
        ccSanh.getItems().clear();
        List<Room> listRoom = bookBus.loadCbbRom();
        for (Room r : listRoom) {
            ccSanh.getItems().add(r.getRoomName());
        }
    }

    @FXML
    private void chonSanhAction(ActionEvent event) {
        if (ccSanh.getValue().equals("Sảnh A")) {
            Room r = bookBus.getRomId("Sảnh A");
            idRoom = r.getRoomId();
        }
        if (ccSanh.getValue().equals("Sảnh B")) {
            Room r = bookBus.getRomId("Sảnh B");
            idRoom = r.getRoomId();
        }
        if (ccSanh.getValue().equals("Sảnh C")) {
            Room r = bookBus.getRomId("Sảnh C");
            idRoom = r.getRoomId();
        }
        if (ccSanh.getValue().equals("Sảnh D")) {
            Room r = bookBus.getRomId("Sảnh D");
            idRoom = r.getRoomId();
        }
    }

    @FXML
    private void dateAction(ActionEvent event) {
    }

    @FXML
    private void chonCaAction(ActionEvent event) {
        if (ccCa.getValue().equals("8h Sáng")) {
            isShift = "8h Sáng";
        }
        if (ccCa.getValue().equals("11h Trưa")) {
            isShift = "11h Trưa";
        }
        if (ccCa.getValue().equals("15h Chiều")) {
            isShift = "15h Chiều";
        }
        if (ccCa.getValue().equals("18h Tối")) {
            isShift = "18h Tối";
        }
    }

    //---------------Phan CUSTOMER-----------------//
    @FXML
    private void saveCustomerAction(ActionEvent event) {
        String bookId = UUID.randomUUID().toString();
        System.out.println("----------------------");
        System.out.println(idConcept);
        for (String s : idMenu) {
            System.out.println(s);
        }
        System.out.println(isShift);
        System.out.println(idRoom);

        try {
            // luu thong tin khach hang
            String sex = null;
            String id = UUID.randomUUID().toString();
            if (rdoNam.isSelected()) {
                sex = "Nam";
            }
            if (rdoNu.isSelected()) {
                sex = "Nữ";
            }
            Customer customer = new Customer(id, txtFirstName.getText(),
                    txtLastName.getText(), txtPhone.getText(), txtAddress.getText(), sex);
            int kq = bookBus.addCustomer(customer);
            setAlert(kq);
        } catch (Exception ex) {
            throw ex;
        }
    }

    //---------------Phan CONCEPT-----------------//
    private void getCCBConcept() {
        ccbConcept.getItems().clear();
        List<Concept> listConcept = bookBus.loadConcept();
        for (Concept c : listConcept) {
            ccbConcept.getItems().add(c.getColor());
            ccbConcept.setPromptText(c.getColor());
        }
    }

    @FXML
    private void cbbConceptAction(ActionEvent event) {
        if (ccbConcept.getValue().equals("Tông màu đỏ")) {
            ccbForText("Tông màu đỏ");
        }
        if (ccbConcept.getValue().equals("Tông màu trắng")) {
            ccbForText("Tông màu trắng");
        }
    }

    private void ccbForText(String name) {
        ccbConcept.getItems().clear();

        List<Concept> listConcept = bookBus.loadConceptForText(name);
        for (Concept c : listConcept) {
            //ccbConcept.getItems().clear();
            ccbConcept.getItems().add(c.getColor());
            ccbConcept.setPromptText(c.getColor());
            txtFlower.setText(c.getFolower());
            txtTable.setText(c.getTable());
            txtBackground.setText(c.getBackground());
            tienTrangTri = Float.parseFloat(c.getPrice());
            String giaTien = String.format("%0,3.0fVNĐ", tienTrangTri);
            lblTienConcept.setText(giaTien);
            lblTrangTri.setText(giaTien);
            idConcept = c.getConceptId();

        }
        getCCBConcept();
        ccbConcept.setPromptText(name);

    }

    private void setAlert(int kq) {

        if (kq == -1) {
            AlertOfMe.alert("Tên không đúng định dạng");
        }
        if (kq == -2) {
            AlertOfMe.alert("SĐT không đúng định dạng");
        }
        if (kq == -3) {
            AlertOfMe.alert("Địa chỉ không đúng định dạng");
        }
        if (kq == 1) {
            AlertOfMe.alert("Thêm khách hàng thành công!");
            txtAddress.clear();
            txtFirstName.clear();
            txtLastName.clear();
            txtPhone.clear();
        }
    }

}
