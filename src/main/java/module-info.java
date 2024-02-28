module com.example.tugas1_pppl {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tugas1_pppl to javafx.fxml;
    exports com.example.tugas1_pppl;
}