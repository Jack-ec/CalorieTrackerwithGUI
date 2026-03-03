module ca.ucalgary.jack.chidlaw.group24finalsubmission {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.ucalgary.jack.chidlaw.group24finalsubmission to javafx.fxml;
    exports ca.ucalgary.jack.chidlaw.group24finalsubmission;
    exports objects;
    opens objects to javafx.fxml;
}