
package main.Business;

import javafx.scene.control.Label;

public class VisibleRoom {
    
      public static void setShift(String Shift, Label t1, Label t2, Label t3, Label t4) {
        switch (Shift) {
            case "8":
                t1.setText("Đã đặt");
                break;
            case "11":
                t2.setText("Đã đặt");
                break;
            case "15":
                t3.setText("Đã đặt");
                break;
            case "18":
                t4.setText("Đã đặt");
                break;
            default:
                break;
        }
    }
}
