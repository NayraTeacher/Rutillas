module com.mycompany.rutillas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.rutillas to javafx.fxml;
    exports com.mycompany.rutillas;
}
