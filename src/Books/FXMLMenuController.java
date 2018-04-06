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
import DAO.HibernateUtil;
import entity.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
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
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;

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
    MenuBUS menubus = new MenuBUS();
    ObservableList<Menu> lsMenu;
    CheckBox cbDatMon = new CheckBox();
    String titleID = null;
    List<Menu> listChonMon = new ArrayList<>();

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
            load("KV", "Món Khai Vị");
        }
        if (cbbLoaiMon.getValue().equals("Món tráng miệng")) {
            cbbLoaiMon.getItems().clear();
            load("TM", "Món Tráng Miệng");
        }
        if (cbbLoaiMon.getValue().equals("Nước uống")) {
            cbbLoaiMon.getItems().clear();
            load("N", "Đồ Uống");
        }
        if (cbbLoaiMon.getValue().equals("Món chính")) {
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
            System.out.println(s.getMenuName());
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
                    float temp=Float.parseFloat(mn.getPrice());
                    String money=String.format("%0,3.0fVNĐ", temp);
                    txtGia.setText(money);
                    txtMoTa.setText(mn.getDescription());
                    imageMonAn.setImage(new Image(mn.getImage()));
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
        if (listChonMon != null) {
            listChonMon.forEach(s -> {
                System.err.println(s.getMenuName() + " " + s.getPrice());
            });
        } else {
            System.out.println("Danh sach rong");
        }
    }

    @FXML
    private void luuAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLBook.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        closeAction();

        //stage.show();
    }

    @FXML
    private void closeAction() {
        Stage stage = (Stage) btnDatMon.getScene().getWindow();
    }

    @FXML
    private void themAction(ActionEvent event) {

    }

    @FXML
    private void xoaAction(ActionEvent event) {

    }

    @FXML
    private void suaAction(ActionEvent event) {

    }

    class BooleanCell extends TableCell<Menu, Boolean> {

        private CheckBox checkBox;

        public BooleanCell() {
            checkBox = new CheckBox();
            checkBox.setDisable(true);
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (isEditing()) {
                        commitEdit(newValue == null ? false : newValue);
                    }
                }
            });
            this.setGraphic(checkBox);
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setEditable(true);
        }

        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
            checkBox.setDisable(false);
            checkBox.requestFocus();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            checkBox.setDisable(true);
        }

        public void commitEdit(Boolean value) {
            super.commitEdit(value);
            checkBox.setDisable(true);
        }

        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                checkBox.setSelected(item);
            }
        }
    }

}
