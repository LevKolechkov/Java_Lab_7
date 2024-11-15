module org.example.lab_7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.lab_7 to javafx.fxml;
    exports org.example.lab_7;
}