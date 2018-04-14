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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    float tienAn, tienTrangTri, tienPhong;
    StringProperty stringp = new SimpleStringProperty();

    String idConcept = null;
    String tienBan = null;
    List<Menu> menu = new ArrayList<>();
    String idRoom = null;
    String isShift = null;
    Date dateBook = null;
    Room room = null;
    int soBan = 0;
    double tongTienListMenu = 0;

    @FXML
    private Label lbUserSession;

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
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));
        Parent root = loader.load();
        FXMLMenuController display = loader.getController();
        display.getsessionUser(lbUserSession.getText()); // đưa lên cho menu
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();

    }

// Load listview Menu
    public void loadLvMenu(List<Menu> mn) {
        menu = mn;

        int i = 0;
        // List<Menu> list = bookBus.loadListMenu();
        for (Menu m : mn) {
            i++;
            tienAn = Float.parseFloat(m.getPrice());
            tongTienListMenu += tienAn;
            String giaTien = String.format("%0,3.0fVNĐ", tienAn);
            lvMenu.getItems().add(i + "-" + m.getMenuName() + "   " + giaTien);
        }
        String giaTien = String.format("%0,3.0fVNĐ", tongTienListMenu);
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

    private void getUser(String userName) {

    }
    public void getsessionUser(String userSession) {
        lbUserSession.setText(userSession); // nhận từ main
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
        double tongTienDoAn = tongTienListMenu;
        if (ccSanh.getValue().equals("A")) {
            Room r = bookBus.getRomId("A");
            room = r;
            soBan = 20;
        }
        if (ccSanh.getValue().equals("B")) {
            Room r = bookBus.getRomId("B");
            room = r;
            soBan = 30;
        }
        if (ccSanh.getValue().equals("C")) {
            Room r = bookBus.getRomId("C");
            room = r;
            soBan = 30;
        }
        if (ccSanh.getValue().equals("D")) {
            Room r = bookBus.getRomId("D");
            room = r;
            soBan = 50;
        }
        tienPhong = Float.parseFloat(room.getPrice());
        String giaTien = String.format("%0,3.0fVNĐ", tienPhong);
        tienBan = giaTien;
        lblDatPhong.setText(giaTien + "   (" + soBan + "bàn)");

        tongTienDoAn *= soBan;
        String tienTong = String.format("%0,3.0fVNĐ", tongTienDoAn);
        lblTienDoAn.setText(tienTong);
    }

    @FXML
    private void dateAction(ActionEvent event) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        dateBook = date.parse(dateDatTiec.getValue().toString());
        Concept c = new Concept();
    }

    @FXML
    private void chonCaAction(ActionEvent event) {
        if (ccCa.getValue().equals("8h Sáng")) {
            isShift = "8";
        }
        if (ccCa.getValue().equals("11h Trưa")) {
            isShift = "11";
        }
        if (ccCa.getValue().equals("15h Chiều")) {
            isShift = "15";
        }
        if (ccCa.getValue().equals("18h Tối")) {
            isShift = "18";
        }
    }

    //---------------Phan CUSTOMER-----------------//
    @FXML
    private void saveCustomerAction(ActionEvent event) {
        String idBook = UUID.randomUUID().toString();
        try {
            if (menu.isEmpty()) {
                AlertOfMe.alert("Bạn chưa chọn món ăn!");
            } else {
                // luu thong tin khach hang
                String sex = null;
                String idCustomer = UUID.randomUUID().toString();
                if (rdoNam.isSelected()) {
                    sex = "Nam";
                }
                if (rdoNu.isSelected()) {
                    sex = "Nữ";
                }
                Customer c = new Customer(idCustomer, txtFirstName.getText(),
                        txtLastName.getText(), txtPhone.getText(), txtAddress.getText(), sex);
                int kq1 = bookBus.ktraCustomer(c.getFirstName(), c.getLastName(), c.getPhone(), c.getAddress());

                // luu thong tin booking
                Concept concept = bookBus.getConcept(idConcept);
                //User user=bookBus.getUSer(userName);
                Booking b = new Booking(idBook, concept, c, room, null, "Mo Ta",
                        dateBook, isShift);
                int kq2 = bookBus.ktraBook(b.getRoom(), b.getBookingDate(), b.getShift());
                if (kq1 == 1 && kq2 == 1) {
                    bookBus.addCustomer(c);
                    bookBus.addBook(b);

                    for (Menu mn : menu) {
                        BookingDetailId idBD = new BookingDetailId(b.getBookingId(), mn.getMenuId());
                        BookingDetail bd = new BookingDetail(idBD, b, mn, null);
                        bookBus.addBookDetail(bd);
                        cleanFinal();
                    }
                    cleanFinal();
                }
                // luu danh sach mon an

            }
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

    private void cleanFinal() {
        txtAddress.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtPhone.clear();
        dateDatTiec.setValue(null);
        ccCa.setPromptText(null);
        ccSanh.setPromptText(null);
        ccbConcept.setPromptText(null);
        menu.clear();
        txtBackground.clear();
        txtFlower.clear();
        txtTable.clear();
        lblTienConcept.setText("");
        lblTrangTri.setText("");
        lblDatPhong.setText("");
        lblTongTien.setText("");
        lblTienDoAn.setText("");
    }

    @FXML
    private void tongTienAction(ActionEvent event) {
        float sumConcept = 0;
        float sumMenu = 0;
        float sumRoom = 0;
        double sum = 0;
        String gia = lblTrangTri.getText();
        gia = gia.replaceAll("[VNĐ,]", "");
        sumConcept = Float.parseFloat(gia);

        gia = lblTienDoAn.getText();
        gia = gia.replaceAll("[VNĐ,]", "");
        sumMenu = Float.parseFloat(gia);

        gia = tienBan;
        gia = gia.replaceAll("[VNĐ,]", "");
        sumRoom = Float.parseFloat(gia);

        sum = sumConcept + sumMenu + sumRoom;
        sum += (sum * 0.1);
        String tienTong = String.format("%0,3.0fVNĐ", sum);
        lblTongTien.setText(tienTong);

    }
}
