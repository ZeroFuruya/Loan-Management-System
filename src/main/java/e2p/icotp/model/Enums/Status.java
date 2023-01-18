package e2p.icotp.model.Enums;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Status {
    public static final StringProperty application = new SimpleStringProperty("Application");
    public static final StringProperty open = new SimpleStringProperty("Open");
    public static final StringProperty closed = new SimpleStringProperty("Closed");
    public static final StringProperty paid = new SimpleStringProperty("Paid");
}