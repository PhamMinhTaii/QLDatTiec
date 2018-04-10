package CommonConstance;

import javafx.scene.control.ComboBox;

public class ComBoBox {

    public static final String male = "Male";
    public static final String female = "Female";
    public static final String admin = "Admin";
    public static final String member = "Member";
    public static final String active = "Active";
    public static final String disable = "Disable";

    public static void setComBoBox(ComboBox<String> cbRole, ComboBox<String> cbActive, ComboBox<String> cbGender) {

        cbRole.getItems().addAll(ComBoBox.admin, ComBoBox.member);
        cbRole.setValue(ComBoBox.member);
        cbActive.getItems().addAll(ComBoBox.active, ComBoBox.disable);
        cbActive.setValue(ComBoBox.active);
        cbGender.getItems().addAll(ComBoBox.male, ComBoBox.female);
        cbGender.setValue(ComBoBox.male);

    }
}
