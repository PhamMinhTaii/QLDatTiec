/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Books;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import BUS.MenuBUS;
import CommonConstance.*;
import DAO.HibernateUtil;
import entity.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Optional;
import java.util.UUID;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;
import sun.plugin2.main.server.LiveConnectSupport;

/**
 * FXML Controller class
 *
 * @author Minh Taii
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private TableView<Menu> tableMenu;
    @FXML
    private TableColumn colTenMon;
    @FXML
    private TableColumn colGia;
    @FXML
    private TableColumn colMoTa;
    @FXML
    private TextField txtMonAn;
    @FXML
    private TextField txtGia;
    @FXML
    private TextArea txtMoTa;
    @FXML
    private TableColumn colChon;
    @FXML
    private GridPane girdThemMon;
    @FXML
    private ImageView imageMonAn;
    @FXML
    private Button btnDatMon;
    @FXML
    private Button btnThemMon;
    @FXML
    private Button btnSuaMon;
    @FXML
    private Button btnXoaMon;
    @FXML
    private Button btnLuu;
    @FXML
    private ComboBox cbbLoaiMon;
    @FXML
    private Button btnChonHinh;
    @FXML
    private Pane pane;

    MenuBUS menubus = new MenuBUS();
    ObservableList<Menu> lsMenu;
    CheckBox cbDatMon = new CheckBox();
    String titleID = null;
    List<Menu> listChonMon = new ArrayList<>();
    String urlImage = null;
    String idMenuForUpdate = null;
    TitleMenu tmForUpdate = null;
    TitleMenu tmForAdd = null;
    boolean statusForUpdate = true;
    boolean isSelecrForUpdate = false;

    // CheckBox cbChon = new CheckBox();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadFirst();
        // su kien click TableView
        mouseClickTableview();
        // Tao column chon mon
        taoCheckBox();
    }

    // khoi tao ComboBox
    @FXML
    private void comboBoxAction(ActionEvent event) {
        if (cbbLoaiMon.getValue().equals("Món Khai Vị")) {
            cbbLoaiMon.getItems().clear();
            load("KV", "Món Khai Vị");
        }
        if (cbbLoaiMon.getValue().equals("Món Tráng Miệng")) {
            cbbLoaiMon.getItems().clear();
            load("TM", "Món Tráng Miệng");
        }
        if (cbbLoaiMon.getValue().equals("Đồ Uống")) {
            cbbLoaiMon.getItems().clear();
            load("N", "Đồ Uống");
        }
        if (cbbLoaiMon.getValue().equals("Món Chính")) {
            cbbLoaiMon.getItems().clear();
            load("MC", "Món Chính");
        }
    }

    // Form Load
    private void load(String title, String promptText) {
        txtMonAn.setEditable(false);
        txtGia.setEditable(false);
        txtMoTa.setEditable(false);
        imageMonAn.setFitHeight(230);
        imageMonAn.setFitWidth(360);
        imageMonAn.setPreserveRatio(true);
        // load ComboBox
        cbbLoaiMon.setPromptText(promptText);
        List<TitleMenu> listTitle = menubus.loadTitleMenu();
        for (TitleMenu titlemenu : listTitle) {
            cbbLoaiMon.getItems().add(titlemenu.getTitleName());
        }
        // gan gia tri cho cac cot trong TableView           
        colTenMon.setCellValueFactory(new PropertyValueFactory("menuName"));
        colGia.setCellValueFactory(new PropertyValueFactory("price"));
        colMoTa.setCellValueFactory(new PropertyValueFactory("description"));
        // hien dong du lieu
        List<Menu> list = menubus.loadMenu(title);
        lsMenu = FXCollections.observableArrayList();
        list.forEach((s) -> {
            lsMenu.addAll(s);
        });
        tableMenu.setItems(lsMenu);
    }

    // load dau tien 
    private void loadFirst() {
        List<Menu> listAll = menubus.loadMenuAll();
        for (Menu mn : listAll) {
            Menu mnTemp = new Menu(mn.getMenuId(), mn.getTitleMenu(),
                    mn.getMenuName(), mn.getPrice(), mn.getDescription(), mn.getImage(),
                    mn.getStatus(), false);
            menubus.update(mnTemp);
        }
        load("KV", "Món Khai Vị");
    }

    // su kien click TableView
    private void mouseClickTableview() {
        this.tableMenu.setRowFactory(tv -> {
            TableRow<Menu> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent e) -> {
                if (e.getClickCount() == 1 && !row.isEmpty()) {
                    Menu mn = row.getItem();
                    txtMonAn.setText(mn.getMenuName());
                    // dinh dang so tien
                    float temp = Float.parseFloat(mn.getPrice());
                    String money = String.format("%0,3.0fVNĐ", temp);
                    txtGia.setText(money);
                    txtMoTa.setText(mn.getDescription());
                   // imageMonAn.setImage(new Image(mn.getImage()));
                    idMenuForUpdate = mn.getMenuId();
                    tmForUpdate = mn.getTitleMenu();
                    statusForUpdate = mn.getStatus();
                    isSelecrForUpdate = mn.getIsSelect();                    
                    if (mn.getImage() != null) {
                        imageMonAn.setImage(new Image(mn.getImage()));
                        urlImage = mn.getImage();
                    }
                }
            });
            return row;
        });
    }

    // Tao cot checkBox
    private void taoCheckBox() {
        colChon.setSortable(false);
        colChon.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Menu, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(CellDataFeatures<Menu, CheckBox> param) {
                Menu mn = param.getValue();

                CheckBox cbChon = new CheckBox();
                cbChon.selectedProperty().setValue(mn.getIsSelect());
//-------------------------------------------------------------------------------//     
                cbChon.setOnMouseClicked((MouseEvent e) -> {
                    Menu mnCheckBox = new Menu(mn.getMenuId(), mn.getTitleMenu(),
                            mn.getMenuName(), mn.getPrice(), mn.getDescription(), mn.getImage(),
                            mn.getStatus(), mn.getIsSelect());
                    if (cbChon.isSelected()) {
                        listChonMon.add(mnCheckBox);
                        mnCheckBox.setIsSelect(true);
                        menubus.update(mnCheckBox);
                    } else {
                        listChonMon.remove(mnCheckBox);
                        mnCheckBox.setIsSelect(false);
                        menubus.update(mnCheckBox);
                    }
                });
//-------------------------------------------------------------------------------//            
                return new SimpleObjectProperty<CheckBox>(cbChon);
            }
        });
    }

    @FXML
    private void datmonAction(ActionEvent event) {
//        if (listChonMon != null) {
//            listChonMon.forEach(s -> {
//                System.err.println(s.getMenuName() + " " + s.getPrice());
//            });
//        } else {
//            System.out.println("Danh sach rong");
//        }
    }

    @FXML
    private void luuAction(ActionEvent event) throws IOException {
        //---------------------Them Mon An-------------------//
        themMonAn();
        //---------------------Sua Mon An-------------------//
//        Menu updateMenu = new Menu(idMenuForUpdate, tmForUpdate,
//                txtMonAn.getText(), txtGia.getText(), txtMoTa.getText(), urlImage,
//                statusForUpdate, isSelecrForUpdate);
//        menubus.update(updateMenu);
        // setAlert(kq);
    }

    // Them mon an
    private void themMonAn() {
        String id = UUID.randomUUID().toString();     
        String promptText = cbbLoaiMon.getPromptText();

        if (promptText.equals("Món Khai Vị")) {
            tmForAdd = menubus.getTitleMenu("KV");          
        }
        if (promptText.equals("Món Tráng Miệng")) {
            tmForAdd = menubus.getTitleMenu("TM");
        }
        if (promptText.equals("Đồ Uống")) {
            tmForAdd = menubus.getTitleMenu("N");
        }
        if (promptText.equals("Món Chính")) {
            tmForAdd = menubus.getTitleMenu("MC");
        }
        Menu mn = new Menu(id, tmForAdd,
                txtMonAn.getText(), txtGia.getText(), txtMoTa.getText(), urlImage, true, false);
        int kq = menubus.addMenu(mn);
        setAlert(kq);
        load(tmForAdd.getTitleId(), promptText);
    }

    @FXML
    private void closeAction() {
        Stage stage = (Stage) btnDatMon.getScene().getWindow();
    }

    @FXML
    private void themAction(ActionEvent event) {
        editAble(true);
        textClear();
        txtMonAn.setPromptText("Nhập vào tên món ăn");
        txtGia.setPromptText("Nhập vào giá");
        txtMoTa.setPromptText("Thêm mô tả về món ăn này");     
    }

    @FXML
    private void xoaAction(ActionEvent event) {   
        String gia = txtGia.getText();
        gia = gia.replaceAll("[VNĐ,]", "");
        txtGia.setText(gia);
        Optional<ButtonType> result = AlertOfMe.alertResult(Alert.AlertType.WARNING, "Bạn có chắc chắn muốn xóa món: "
                + txtMonAn.getText());
        if (result.get() == ButtonType.OK) {
            Menu updateMenu = new Menu(idMenuForUpdate, tmForUpdate,
                    txtMonAn.getText(), txtGia.getText(), txtMoTa.getText(), urlImage,
                    Boolean.FALSE, false);
            menubus.update(updateMenu);
//            if (menubus.update(updateMenu) == 1) {
//                AlertOfMe.alert("Xóa thành công!");
//            }
             loadFirst();
        }
    }

    @FXML
    private void suaAction(ActionEvent event) {
        editAble(true);
        txtMonAn.setPromptText("Sửa tên món ăn");
        txtGia.setPromptText("Sửa giá");
        txtMoTa.setPromptText("Sửa mô tả về món ăn này");
        String gia = txtGia.getText();
        gia = gia.replaceAll("[VNĐ,]", "");
        txtGia.setText(gia);
    }

    private void setAlert(int kq) {
        if (kq == -1) {
            AlertOfMe.alert("Tên món ăn không đúng định dạng");
        }
        if (kq == -2) {
            AlertOfMe.alert("Giá tiền không đúng định dạng");
        }
        if (kq == -3) {
            AlertOfMe.alert("Mô tả không đúng định dạng");
        }
        if (kq == 1) {
            AlertOfMe.alert("Thêm món ăn thành công!");
            textClear();
            editAble(false);
            txtMonAn.setPromptText(null);
            txtGia.setPromptText(null);
            txtMoTa.setPromptText(null);
        }
    }

    @FXML
    private void chonHinhAction(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        FileChooser filechoi = new FileChooser();
        File file = filechoi.showOpenDialog(stage);
        if (file != null) {
            urlImage = file.toURI().toString();
            System.out.println(urlImage);
            imageMonAn.setImage(new Image(urlImage));
        }
    }

    private void editAble(Boolean bool) {
        txtMonAn.setEditable(bool);
        txtGia.setEditable(bool);
        txtMoTa.setEditable(bool);
    }

    private void textClear() {
        txtGia.clear();
        txtMoTa.clear();
        txtMonAn.clear();
    }
}
