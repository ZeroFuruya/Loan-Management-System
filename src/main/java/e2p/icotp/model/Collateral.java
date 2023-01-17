package e2p.icotp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Collateral {
    private IntegerProperty loaner_id;
    private IntegerProperty loan_id;
    private StringProperty collateral;

    public Collateral(int loaner_id, int loan_id, String collateral) {
        this.loaner_id = new SimpleIntegerProperty(loaner_id);
        this.loan_id = new SimpleIntegerProperty(loan_id);
        this.collateral = new SimpleStringProperty(collateral);
    }

    public Collateral() {
        this(0, 0, "");
    }
}
