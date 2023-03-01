package e2p.icotp.model;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Payment {
    private LongProperty payment_id;
    private ObjectProperty<Loaner> loaner_id;
    private ObjectProperty<Loan> loan_id;
    private ObjectProperty<LocalDate> payment_date;
    private DoubleProperty payment_amount;
    private ObjectProperty<LocalDate> date_payment;

    public Payment(long payment_id, Loaner loaner_id, Loan loan_id,
            LocalDate payment_date, double payment_amount, LocalDate date_payment) {
        this.payment_id = new SimpleLongProperty(payment_id);
        this.loaner_id = new SimpleObjectProperty<>(new Loaner(loaner_id));
        this.loan_id = new SimpleObjectProperty<>(loan_id);
        this.payment_date = new SimpleObjectProperty<>(payment_date);
        this.payment_amount = new SimpleDoubleProperty(payment_amount);
        this.date_payment = new SimpleObjectProperty<>(date_payment);
    }

    public Payment() {
        this(0, new Loaner(), new Loan(), LocalDate.now(), 0.0, LocalDate.now());
    }

    // SETTERS
    public void setPayment_id(long payment_id) {
        this.payment_id.set(payment_id);
    }

    public void setLoaner_id(Loaner loaner_id) {
        this.loaner_id.set(loaner_id);
    }

    public void setLoan_id(Loan loan_id) {
        this.loan_id.set(loan_id);
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date.set(payment_date);
    }

    public void setPayment_amount(double payment_amount) {
        this.payment_amount.set(payment_amount);
    }

    // GETTERS
    public long getPayment_id() {
        return this.payment_id.get();
    }

    public Loaner getLoaner_id() {
        return this.loaner_id.get();
    }

    public Loan getLoan_id() {
        return this.loan_id.get();
    }

    public LocalDate getPaymentDate() {
        return this.payment_date.get();
    }

    public double getPayment_amount() {
        return this.payment_amount.get();
    }

    // PROPERTY GETTERS
    public LongProperty getPayment_id_Property() {
        return payment_id;
    }

    public ObjectProperty<Loaner> getLoaner_id_Property() {
        return loaner_id;
    }

    public ObjectProperty<Loan> getLoan_id_Property() {
        return loan_id;
    }

    public ObjectProperty<LocalDate> getPayment_date_Property() {
        return payment_date;
    }

    public DoubleProperty getPayment_amount_Property() {
        return payment_amount;
    }

    public ObjectProperty<LocalDate> getDatePaymentProperty() {
        return date_payment;
    }
}
