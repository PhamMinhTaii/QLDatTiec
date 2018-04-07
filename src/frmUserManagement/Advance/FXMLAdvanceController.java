package frmUserManagement.Advance;

import BUS.UserBUS;
import Common.Action.BackFrmUser;
import CommonConstance.ComBoBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class FXMLAdvanceController implements Initializable {
    
    private final UserBUS userBUS = new UserBUS();
    @FXML
    private final TextField txtUserSession = new TextField();
    @FXML
    private Button btnBack;
    @FXML
    private Button btnGender;
    @FXML
    private Button btnRole;
    @FXML
    private Button btnActive;
    @FXML
    private PieChart pieChart;
    @FXML
    private GridPane gPane;
    @FXML
    private Label txtCount_1;
    @FXML
    private Label txtCount_2;
    @FXML
    private TextField txtQua_1;
    @FXML
    private TextField txtQua_2;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtPer_1;
    @FXML
    private TextField txtPer_2;
    
    private final String[] genders = {ComBoBox.male, ComBoBox.female};
    private final String[] actives = {ComBoBox.active, ComBoBox.disable};
    private final String[] roles = {ComBoBox.admin, ComBoBox.member};
    private final int[] quanlity = new int[2];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBack.setOnAction(new BackFrmUser(txtUserSession));
        btnGender.setOnAction(new PChart(genders, "gender", "Male"));
        btnRole.setOnAction(new PChart(roles, "role", "Admin"));
        btnActive.setOnAction(new PChart(actives, "active", "Active"));
        
    }
    
    public class PChart implements EventHandler<ActionEvent> {
        
        private final String[] content;
        private final String property;
        private final String value;
        
        public PChart(String[] content, String property, String value) {
            this.content = content;
            this.property = property;
            this.value = value;
        }

        @Override
        public void handle(ActionEvent event) {
            gPane.setVisible(false);
            setPieChart(content, property, value);
        }
    }
    
    public void setPieChart(String[] content, String property, String value) {
        pieChart.getData().removeAll(pieChart.getData());
        int total = userBUS.countColunms("", null);
        quanlity[0] = userBUS.countColunms(property, value);
        quanlity[1] = total - quanlity[0];
        
        pieChart.getData().add(new PieChart.Data(content[0], quanlity[0]));
        pieChart.getData().add(new PieChart.Data(content[1], quanlity[1]));
        
        pieChart.getData().forEach((data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                gPane.setVisible(true);
                txtCount_1.setText(content[0]);
                txtCount_2.setText(content[1]);
                txtQua_1.setText(String.valueOf(quanlity[0]));
                txtQua_2.setText(String.valueOf(quanlity[1]));
                txtTotal.setText(String.valueOf(total));
                txtPer_1.setText(perCent(quanlity[0], total));
                txtPer_2.setText(perCent(quanlity[1], total));
            });
        });
    }
    
    public String perCent(int count, int total) {
        return String.format("%.2f", ((double) count / total) * 100);
    }
    
    public void getUserSession(String userSession) {
        txtUserSession.setText(userSession);
    }
}
