module com.kyilmaz80.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.kyilmaz80.hotel to javafx.fxml;
    exports com.kyilmaz80.hotel;
    exports com.kyilmaz80.hotel.utils;
    opens com.kyilmaz80.hotel.utils to javafx.fxml;
    exports com.kyilmaz80.hotel.models;
    opens com.kyilmaz80.hotel.models to javafx.fxml;
    exports com.kyilmaz80.hotel.controllers;
    opens com.kyilmaz80.hotel.controllers to javafx.fxml;
}