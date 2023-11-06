module com.kyilmaz80.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.dbcp2;

    opens com.kyilmaz80.hotel to javafx.fxml;
    exports com.kyilmaz80.hotel;
    exports com.kyilmaz80.hotel.utils;
    opens com.kyilmaz80.hotel.utils to javafx.fxml;
}