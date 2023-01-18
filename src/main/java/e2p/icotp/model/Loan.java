package e2p.icotp.model;

import java.time.LocalDate;

import e2p.icotp.model.Enums.Status;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loan {
    private IntegerProperty loan_id;
    private IntegerProperty loaner_id;
    private ObjectProperty<LocalDate> release_date;
    private IntegerProperty term;
    private ObjectProperty<LocalDate> maturity_date;
    private DoubleProperty principal;
    private DoubleProperty interest;
    private DoubleProperty penalty;
    private DoubleProperty due;
    private DoubleProperty paid;
    private DoubleProperty balance;
    private StringProperty status;

    public Loan(int loan_id, int loaner_id, LocalDate release_date, int term, LocalDate maturity_date, double principal,
            double interest, double penalty, double due, double paid, double balance, String status) {

        this.loan_id = new SimpleIntegerProperty(loan_id);
        this.loaner_id = new SimpleIntegerProperty(loaner_id);
        this.release_date = new SimpleObjectProperty<>(release_date);
        this.term = new SimpleIntegerProperty(term);
        this.maturity_date = new SimpleObjectProperty<>(maturity_date);
        this.principal = new SimpleDoubleProperty(principal);
        this.interest = new SimpleDoubleProperty(interest);
        this.penalty = new SimpleDoubleProperty(penalty);
        this.due = new SimpleDoubleProperty(due);
        this.paid = new SimpleDoubleProperty(paid);
        this.balance = new SimpleDoubleProperty(balance);
        this.status = new SimpleStringProperty(status);
    }

    public Loan() {
        this(0, 0, LocalDate.now(), 0, LocalDate.now(), 0, 0, 0, 0, 0, 0, Status.application.get());
    }

    // SETTERS

    public void setStatus(String val) {
        this.status.set(val);
        ;
    }

    // GETTERS

    public String getStatus() {
        return this.status.get();
    }

    // PROPERTY GETTERS

    public StringProperty getStatusProperty() {
        return this.status;
    }
}
