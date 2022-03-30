module Group7.Huddle {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires java.desktop;
	requires json;
	requires javafx.base;

    opens client to javafx.fxml;
    exports client;
}
