package e2p.icotp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Collateral {
    private ObjectProperty<Loaner> loaner_id;
    private ObjectProperty<Loan> loan_id;
    private IntegerProperty collateral_id;
    private ObjectProperty<LoanPlan> loan_plan_id;
    private StringProperty collateral;
    private StringProperty status;

    public Collateral(Loaner loaner_id, Loan loan_id, int collateral_id, LoanPlan loan_plan_id, String collateral,
            String status) {
        this.loaner_id = new SimpleObjectProperty<>(loaner_id);
        this.loan_id = new SimpleObjectProperty<>(loan_id);
        this.collateral_id = new SimpleIntegerProperty(collateral_id);
        this.loan_plan_id = new SimpleObjectProperty<>(loan_plan_id);
        this.collateral = new SimpleStringProperty(collateral);
        this.status = new SimpleStringProperty(status);
    }

    public Collateral() {
        this(new Loaner(), new Loan(), 0, new LoanPlan(), "", "");
    }

    // SETTERS
    public void setLoaner_id(Loaner val) {
        this.loaner_id.set(val);
    }

    public void setLoan_id(Loan val) {
        this.loan_id.set(val);
    }

    public void setPlan_id(LoanPlan val) {
        this.loan_plan_id.set(val);
    }

    public void setCollateral_id(int val) {
        this.collateral_id.set(val);
    }

    public void setCollateral(String val) {
        this.collateral.set(val);
    }

    // GETTERS

    public Loaner getLoaner_id() {
        return this.loaner_id.get();
    }

    public Loan getLoan_id() {
        return this.loan_id.get();
    }

    public int getCollateral_id() {
        return this.collateral_id.get();
    }

    public LoanPlan getPlan_id() {
        return this.loan_plan_id.get();
    }

    public String getCollateral() {
        return this.collateral.get();
    }

    // PROPERTY GETTERS

    public ObjectProperty<Loaner> getLoaner_property() {
        return this.loaner_id;
    }

    public ObjectProperty<Loan> getLoan_property() {
        return this.loan_id;
    }

    public IntegerProperty getCollateralId_property() {
        return this.collateral_id;
    }

    public ObjectProperty<LoanPlan> getPlan_property() {
        return this.loan_plan_id;
    }

    public StringProperty getCollateral_property() {
        return this.collateral;
    }

    public StringProperty getStatusProperty() {
        return this.status;
    }
}