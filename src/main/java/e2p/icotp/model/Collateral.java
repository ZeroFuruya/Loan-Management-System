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

    // SETTERS
    public void setLoaner_id(int val) {
        this.loaner_id.set(val);
    }

    public void setLoan_id(int val) {
        this.loan_id.set(val);
    }

    public void setCollateral(String val) {
        this.collateral.set(val);
    }

    // GETTERS

    public int getLoaner_id() {
        return this.loaner_id.get();
    }

    public int getLoan_id() {
        return this.loan_id.get();
    }

    public String getCollateral() {
        return this.collateral.get();
    }

    // PROPERTY GETTERS

    public IntegerProperty getLoaner_property() {
        return this.loaner_id;
    }

    public IntegerProperty getLoan_property() {
        return this.loan_id;
    }

    public StringProperty getCollateral_property() {
        return this.collateral;
    }
}