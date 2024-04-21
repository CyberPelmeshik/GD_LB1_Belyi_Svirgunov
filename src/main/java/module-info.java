module lb {
    requires javafx.controls;
    requires javafx.fxml;

    opens lb to javafx.fxml;
    exports lb;
}
