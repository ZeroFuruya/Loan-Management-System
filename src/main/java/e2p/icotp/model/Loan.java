package e2p.icotp.model;

import java.time.LocalDate;

import e2p.icotp.model.Enums.Status;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

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
    private Status status;

    // SETTERS

    // GETTERS

    // PROPERTY GETTERS
}
