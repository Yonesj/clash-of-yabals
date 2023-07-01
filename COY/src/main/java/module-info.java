module com.example.coy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.coy to javafx.fxml;
    exports com.example.coy;

}