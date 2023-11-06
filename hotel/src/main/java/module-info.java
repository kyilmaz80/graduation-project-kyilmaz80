module com.kyilmaz80.hotel {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.kyilmaz80.hotel to javafx.fxml;
    exports com.kyilmaz80.hotel;
}