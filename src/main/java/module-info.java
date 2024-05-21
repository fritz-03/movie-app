module org.fritz.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    requires java.management;


    opens org.fritz.app to javafx.fxml;
    exports movie;
    exports org.fritz.app;
    exports movie.api;
    opens movie.api to javafx.fxml;
}
