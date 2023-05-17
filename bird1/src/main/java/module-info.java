module com.example.bird1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bird1 to javafx.fxml;
    exports com.example.bird1;
}