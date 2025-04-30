module org.example.pr_8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.pr_8 to javafx.fxml;
    exports org.example.pr_8.Employee;
    exports org.example.pr_8.Commission;
    opens org.example.pr_8.Commission to javafx.fxml;
    opens org.example.pr_8.Employee to javafx.fxml;
}