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
import javafx.application.Platform;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
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
    @FXML
    private Label lbUserSession;

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
    String addOrUpdate = null;

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
        if (cbbLoaiMon.getValue().equals("Món khai vị")) {
            cbbLoaiMon.getItems().clear();
            load("KV", "Món khai vị");
        }
        if (cbbLoaiMon.getValue().equals("Món tráng miệng")) {
            cbbLoaiMon.getItems().clear();
            load("TM", "Món tráng miệng");
        }
        if (cbbLoaiMon.getValue().equals("Nước uống")) {
            cbbLoaiMon.getItems().clear();
            load("N", "Đồ uống");
        }
        if (cbbLoaiMon.getValue().equals("Món chính")) {
            cbbLoaiMon.getItems().clear();
            load("MC", "Món chính");
        }
    }

    // Form Load
    private void load(String title, String promptText) {
        editAble(false);
        textClear();
        btnChonHinh.setVisible(false);
        // load ComboBox
        cbbLoaiMon.setPromptText(promptText);
        cbbLoaiMon.getItems().clear();
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
        listAll.forEach((mn) -> {
            Menu mnTemp = new Menu(mn.getMenuId(), mn.getTitleMenu(),
                    mn.getMenuName(), mn.getPrice(), mn.getDescription(), mn.getImage(),
                    mn.getStatus(), false);
            menubus.update(mnTemp);

        });
        load("KV", "Món khai vị");
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
                    if (mn.getImage() != null) {
                        imageMonAn.setImage(new Image(mn.getImage()));
                    }
                    txtGia.setText(money);
                    txtMoTa.setText(mn.getDescription());
                    idMenuForUpdate = mn.getMenuId();
                    tmForUpdate = mn.getTitleMenu();
                    statusForUpdate = mn.getStatus();
                    isSelecrForUpdate = mn.getIsSelect();
                    urlImage = mn.getImage();
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
                cbChon.setOnMouseClicked((MouseEvent e) -> {
                    Menu mnCheckBox = new Menu(mn.getMenuId(), mn.getTitleMenu(),
                            mn.getMenuName(), mn.getPrice(), mn.getDescription(), mn.getImage(),
                            mn.getStatus(), mn.getIsSelect());
                    if (cbChon.isSelected()) {
                        mnCheckBox.setIsSelect(true);
                        menubus.update(mnCheckBox);
                    } else {
                        mnCheckBox.setIsSelect(false);
                        menubus.update(mnCheckBox);
                    }
                });
                return new SimpleObjectProperty<CheckBox>(cbChon);
            }
        });
    }

    @FXML
    private void datmonAction(ActionEvent event) throws IOException {
        // dong form menu
//        Parent root1 = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
//        
//        Stage stage1 = new Stage();
//        stage1.setScene(new Scene(root1));
//        stage1 = (Stage) btnDatMon.getScene().getWindow();
//        stage1.close();

        List<Menu> listIsSelect = menubus.loadMenuisSelect();
        for (Menu mn : listIsSelect) {
            listChonMon.add(mn);
        }

        Parent root = FXMLLoader.load(getClass().getResource("FXMLBook.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage = (Stage) btnDatMon.getScene().getWindow();
        stage.close();
        // chuyen du lieu
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLBook.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            throw ex;
        }
        FXMLBookController bookController = loader.getController();
        bookController.loadLvMenu(listChonMon);
        bookController.getsessionUser(lbUserSession.getText()); // trả usersession về cho book
        bookController.isClosed(true);
        root = loader.getRoot();
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void luuAction(ActionEvent event) throws IOException {
        System.out.println(addOrUpdate);
        if (addOrUpdate.equals("add")) {
            themMonAn();
        }
        if (addOrUpdate.equals("update")) {
            suaMonAn();
        }
    }

    @FXML
    private void closeAction() {
        Stage stage = (Stage) btnDatMon.getScene().getWindow();
    }

    @FXML
    private void xoaAction(ActionEvent event) {
        String gia = txtGia.getText();
        String idLoad=tmForUpdate.getTitleId();
        String promptLoad=cbbLoaiMon.getPromptText();
        gia = gia.replaceAll("[VNĐ,]", "");
        txtGia.setText(gia);
        Optional<ButtonType> result = AlertOfMe.alertResult(Alert.AlertType.WARNING, "Bạn có chắc chắn muốn xóa món: "
                + txtMonAn.getText());
        if (result.get() == ButtonType.OK) {
            Menu menuUpdate = new Menu(idMenuForUpdate, tmForUpdate,
                    txtMonAn.getText(), txtGia.getText(), txtMoTa.getText(), urlImage,
                    false, false);
            menubus.update(menuUpdate);
            AlertOfMe.alert("Xóa thành công!");
        }
        
        load(idLoad,promptLoad);
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

            urlImage = file.toString();
            imageMonAn.setImage(new Image(urlImage));
            System.out.println(urlImage);
        }

    }

    private void editAble(Boolean bool) {
        if (bool == false) {
            txtGia.setPromptText(null);
            txtMoTa.setPromptText(null);
            txtMonAn.setPromptText(null);
        }
        txtMonAn.setEditable(bool);
        txtGia.setEditable(bool);
        txtMoTa.setEditable(bool);
    }

    private void textClear() {
        txtGia.clear();
        txtMoTa.clear();
        txtMonAn.clear();
    }

    @FXML
    private void themAction(ActionEvent event) {
        addOrUpdate = "add";
        editAble(true);
        btnChonHinh.setVisible(true);
        textClear();
        txtMonAn.setPromptText("Nhập vào tên món ăn");
        txtGia.setPromptText("Nhập vào giá");
        txtMoTa.setPromptText("Thêm mô tả về món ăn này");
    }

    private void themMonAn() {
        String id = UUID.randomUUID().toString();
        String promptText = cbbLoaiMon.getPromptText();
        if (promptText.equals("Món khai vị")) {
            tmForAdd = menubus.getTitleMenu("KV");
        }
        if (promptText.equals("Món tráng miệng")) {
            tmForAdd = menubus.getTitleMenu("TM");
        }
        if (promptText.equals("Đồ uống")) {
            tmForAdd = menubus.getTitleMenu("N");
        }
        if (promptText.equals("Món chính")) {
            tmForAdd = menubus.getTitleMenu("MC");
        }
        Menu mn = new Menu(id, tmForAdd, txtMonAn.getText(),
                txtGia.getText(), txtMoTa.getText(), urlImage, true, false);
        int kq = menubus.addMenu(mn);
        setAlert(kq);
        load(tmForAdd.getTitleId(), promptText);
    }

    private void suaMonAn() {
        String idLoad=tmForUpdate.getTitleId();
        String promptLoad=cbbLoaiMon.getPromptText();
        Menu updateMenu = new Menu(idMenuForUpdate, tmForUpdate,
                txtMonAn.getText(), txtGia.getText(), txtMoTa.getText(), urlImage,
                statusForUpdate, isSelecrForUpdate);
        menubus.update(updateMenu);
        AlertOfMe.alert("Sửa món ăn thành công!");
         load(idLoad,promptLoad);
    }

    @FXML
    private void suaAction(ActionEvent event) {
        addOrUpdate = "update";
        editAble(true);
        txtMonAn.setPromptText("Sửa tên món ăn");
        txtGia.setPromptText("Sửa giá");
        txtMoTa.setPromptText("Sửa mô tả về món ăn này");
        String gia = txtGia.getText();
        gia = gia.replaceAll("[VNĐ,]", "");
        txtGia.setText(gia);
    }

    public void getsessionUser(String userSession) {
        lbUserSession.setText(ReplaceString.UserName(userSession)); // nhận từ book
    }
}
