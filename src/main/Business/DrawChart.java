package main.Business;

import entity.Booking;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

public class DrawChart {

    public static void getMonths(List<Booking> listBooking, int[] m, int yearSelected) {

        Calendar cal = Calendar.getInstance();
        int thang;
        int nam;
        for (Booking book : listBooking) {
            cal.setTime(book.getBookingDate());
            nam = cal.get(Calendar.YEAR);
            if (nam == yearSelected) {
                thang = cal.get(Calendar.MONTH) + 1;
                switch (thang) {
                    case 1:
                        m[0]++;
                        break;
                    case 2:
                        m[1]++;
                        break;
                    case 3:
                        m[2]++;
                        break;
                    case 4:
                        m[3]++;
                        break;
                    case 5:
                        m[4]++;
                        break;
                    case 6:
                        m[5]++;
                        break;
                    case 7:
                        m[6]++;
                        break;
                    case 8:
                        m[7]++;
                        break;
                    case 9:
                        m[8]++;
                        break;
                    case 10:
                        m[9]++;
                        break;
                    case 11:
                        m[10]++;
                        break;
                    case 12:
                        m[11]++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static List createItems(List<Booking> listBooking) { // tạo dử liệu cho combox chọn năm
        Calendar cal = Calendar.getInstance();
        Set<Integer> rDuplicates = new HashSet<>();
        for (Booking book : listBooking) {
            cal.setTime(book.getBookingDate());
            rDuplicates.add(cal.get(Calendar.YEAR));
        }
        List sortedList = new ArrayList(rDuplicates);
        Collections.sort(sortedList);
        return sortedList;
    }

    public static void setComBoxYears(List<Booking> listBooking, ComboBox years) {
        years.getItems().addAll(createItems(listBooking)); // gán dữ liệu cho combox chọn năm
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int nam = cal.get(Calendar.YEAR);
        years.setValue(nam);
    }

    public static void drawBarChar(BarChart<?, ?> salary, List<Booking> list, int[] m, int yearSelected) {
        salary.getData().clear();
        salary.setVisible(true);
        m = new int[12];
        getMonths(list, m, yearSelected);
        XYChart.Series set = new XYChart.Series<>();
        for (int i = 0; i < m.length; i++) {
            set.getData().add(new XYChart.Data(String.valueOf(i + 1), m[i]));
        }
        salary.getData().add(set);
    }

    public static int[] drawPieChar(PieChart pieChart, List<Booking> list, int[] quarter, int yearSelected) {
        pieChart.getData().removeAll(pieChart.getData());
        pieChart.setVisible(true);
        quarter = new int[4];
        getQuarter(list, quarter, yearSelected);
        List<PieChart.Data> listData = new ArrayList<>();
        for (int i = 0; i < quarter.length; i++) {
            listData.add(new PieChart.Data(String.valueOf(i + 1), quarter[i]));           
        }
         pieChart.getData().addAll(listData);
         return quarter;
    }

    public static void getQuarter(List<Booking> listBooking, int[] quarter, int yearSelected) {
        Calendar cal = Calendar.getInstance();
        int thang;
        int nam;
        for (Booking book : listBooking) {
            cal.setTime(book.getBookingDate());
            nam = cal.get(Calendar.YEAR);
            if (nam == yearSelected) {
                thang = cal.get(Calendar.MONTH) + 1;
                switch (thang) {
                    case 1:
                    case 2:
                    case 3:
                        quarter[0]++;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        quarter[1]++;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        quarter[2]++;
                        break;
                    case 10:
                    case 11:
                    case 12:
                        quarter[3]++;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    
}
