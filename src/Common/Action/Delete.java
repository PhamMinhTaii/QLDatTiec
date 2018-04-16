package Common.Action;

import BUS.UserBUS;
import CommonConstance.AlertOfMe;
import CommonConstance.ComBoBox;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Delete {

    private final UserBUS userBUS = new UserBUS();

    public boolean delete(String userNameDel, String userSession) {
        try {
            if (userNameDel == null) {
                AlertOfMe.alert("Chưa Chọn User Để Xoá");
            } else {
                if (userBUS.findRole(userSession).equals(ComBoBox.admin)) {
                    Optional<ButtonType> result = AlertOfMe.alertResult(Alert.AlertType.INFORMATION, "Chắc Chắn Xoá User : " + userNameDel);
                    if (result.get() == ButtonType.OK) {
                        if (userBUS.deleteUser(userNameDel) == 1) {
                            AlertOfMe.alert("Xoá Thành Công");
                            return true;
                        }
                    }
                } else {
                    AlertOfMe.alert("Tài Khoản Của Bạn Không Có Quyền Xoá");
                }
            }
        } catch (Exception e) {
            AlertOfMe.alert("Lỗi Hệ Thống");
        }
        return false;
    }
}
