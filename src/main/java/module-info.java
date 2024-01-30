module com.example.motomami {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.motomami to javafx.fxml;
    exports com.example.motomami;
    exports com.example.motomami.Controllers;
    opens com.example.motomami.Controllers to javafx.fxml;
    exports com.example.motomami.Utils;
    opens com.example.motomami.Utils to javafx.fxml;
}