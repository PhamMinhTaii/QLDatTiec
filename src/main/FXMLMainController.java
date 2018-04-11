package main;

import BUS.RoomBUS;
import Common.Action.LogOut;
import CommonConstance.AlertOfMe;
import CommonConstance.SetStage;
import entity.Booking;
import frmUserManagement.FXMLUserManagementController;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Business.DrawChart;
import main.Business.VisibleRoom;

public class FXMLMainController implements Initializable {

    protected final RoomBUS roomBUS = new RoomBUS();
    private List<Booking> listBooking = null;
    @FXML
    private Label lbUserSession;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnDrawBarChart;
    @FXML
    private Button btnDrawPieChart;
    @FXML
    private DatePicker dpicker;
    @FXML
    private Label lblA1;
    @FXML
    private Label lblA2;
    @FXML
    private Label lblA3;
    @FXML
    private Label lblA4;
    @FXML
    private Label lblB1;
    @FXML
    private Label lblB2;
    @FXML
    private Label lblB3;
    @FXML
    private Label lblB4;
    @FXML
    private Label lblC1;
    @FXML
    private Label lblC2;
    @FXML
    private Label lblC3;
    @FXML
    private Label lblC4;
    @FXML
    private Label lblD1;
    @FXML
    private Label lblD2;
    @FXML
    private Label lblD3;
    @FXML
    private Label lblD4;
    @FXML
    private Label lblToi;
    @FXML
    private Label lblChieu;
    @FXML
    private Label lblTrua;
    @FXML
    private Label lblSang;
    @FXML
    private BarChart<?, ?> salaryBooking;
    private int[] months; //= new int[12];
    private int[] quarter;// =  new int[4];
    @FXML
    private PieChart pieChart;
    @FXML
    private GridPane Numbers;
    @FXML
    private ComboBox cbYears;

    ///-----set label girdview
    @FXML
    private Label lblQuantity_I;
    @FXML
    private Label lblQuantity_II;
    @FXML
    private Label lblQuantity_III;
    @FXML
    private Label lblQuantity_IV;
    @FXML
    private Label lblPercent_I;
    @FXML
    private Label lblPercent_II;
    @FXML
    private Label lblPercent_III;
    @FXML
    private Label lblPercent_IV;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatLabel();
        listBooking = findListBooking();
        dpicker.setValue(LocalDate.now());
        setGridViewBooking(listBooking);
        dpicker.setOnAction(y -> {
            setGridViewBooking(listBooking);
        });
        DrawChart.setComBoxYears(listBooking, cbYears); // khởi tạo combobox  chọn năm

        btnDrawBarChart.setOnAction((event) -> {
            pieChart.setVisible(false);
            Numbers.setVisible(false);
            int yearSelected = Integer.parseInt(cbYears.getValue().toString());
            DrawChart.drawBarChar(salaryBooking, listBooking, months, yearSelected);
        });
        btnDrawPieChart.setOnAction((event) -> {
            salaryBooking.setVisible(false);
            Numbers.setVisible(true);
            int yearSelected = Integer.parseInt(cbYears.getValue().toString());
            quarter = DrawChart.drawPieChar(pieChart, listBooking, quarter, yearSelected);
            setGridViewChart(quarter);
            btnLogout.setOnAction(new LogOut());
        });
    }

    private void formatLabel() {
        lblSang.setGraphic(new ImageView(new Image("Images/m.png")));
        lblTrua.setGraphic(new ImageView(new Image("Images/ch.png")));
        lblChieu.setGraphic(new ImageView(new Image("Images/tr.png")));
        lblToi.setGraphic(new ImageView(new Image("Images/t.png")));
    }

    @FXML
    private void datTiecCuoiAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Books/FXMLBook.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void moveUserForm(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frmUserManagement/FXMLUserManagement.fxml"));
        Parent root = loader.load();
        FXMLUserManagementController display = loader.getController();
        display.getSessionUser(lbUserSession.getText());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hệ Thống Tiệc Cưới T&T");
        stage.initStyle(StageStyle.UTILITY);
        SetStage.setStage(stage, scene, 645, 550);
    }

    public List<Booking> findListBooking() {
        try {
            return roomBUS.findListRoomID();
        } catch (Exception e) {
            AlertOfMe.alert("Lỗi Hệ Thống");
        }
        return null;
    }

    public void setGridViewBooking(List<Booking> list) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            clear();
            Date dateNow = f.parse(dpicker.getValue().toString());

            for (Booking book : list) {

                Date date = f.parse(book.getBookingDate().toString());
                if (date.equals(dateNow)) {
                    String roomName = roomBUS.findRoomName(book.getRoom().getRoomId());
                    switch (roomName) {
                        case "A":
                            VisibleRoom.setShift(book.getShift(), lblA1, lblA2, lblA3, lblA4);
                            break;
                        case "B":
                            VisibleRoom.setShift(book.getShift(), lblB1, lblB2, lblB3, lblB4);
                            break;
                        case "C":
                            VisibleRoom.setShift(book.getShift(), lblC1, lblC2, lblC3, lblC4);
                            break;
                        case "D":
                            VisibleRoom.setShift(book.getShift(), lblD1, lblD2, lblD3, lblD4);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setGridViewChart(int[] quarter) {
        int total = quarter[0] + quarter[1] + quarter[2] + quarter[3]; // tính tổng để tính %
        //--set lbl số lượng
        lblQuantity_I.setText(String.valueOf(quarter[0]));
        lblQuantity_II.setText(String.valueOf(quarter[1]));
        lblQuantity_III.setText(String.valueOf(quarter[2]));
        lblQuantity_IV.setText(String.valueOf(quarter[3]));        
        //--set lbl phần trăm
        lblPercent_I.setText(perCent(quarter[0], total));
        lblPercent_II.setText(perCent(quarter[1], total));
        lblPercent_III.setText(perCent(quarter[2], total));
        lblPercent_IV.setText(perCent(quarter[3], total));

    }

    public void clear() {
        lblA1.setText(null);
        lblA2.setText(null);
        lblA3.setText(null);
        lblA4.setText(null);
        lblB1.setText(null);
        lblB2.setText(null);
        lblB3.setText(null);
        lblB4.setText(null);
        lblC1.setText(null);
        lblC2.setText(null);
        lblC3.setText(null);
        lblC4.setText(null);
        lblD1.setText(null);
        lblD2.setText(null);
        lblD3.setText(null);
        lblD4.setText(null);

    }

    public String perCent(int count, int total) {
        return String.format("%.2f", ((double) count / total) * 100);
    }

    public void getsessionUser(String userSession) {
        lbUserSession.setText(userSession);
    }

}
