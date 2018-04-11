package main.Business;

import entity.Booking;
import java.util.Calendar;
import java.util.List;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class DrawBarChart {

    public static void getMonths(List<Booking> list, int[] m) {
      
        Calendar cal = Calendar.getInstance();
        cal.get(Calendar.MONTH);
        int thang;
        for (Booking book : list) {
            cal.setTime(book.getBookingDate());
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

    public static void drawBarChar(BarChart<?, ?> salary, List<Booking> list, int[] m) {
        salary.getData().clear();
        salary.setVisible(true);
          m = new int[12];
        getMonths(list, m);
        XYChart.Series set = new XYChart.Series<>();
        for (int i = 0; i < m.length; i++) {
            set.getData().add(new XYChart.Data(String.valueOf(i + 1), m[i]));
        }
        salary.getData().add(set);
    }
}
