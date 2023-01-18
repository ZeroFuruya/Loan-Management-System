package e2p.icotp.model;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Payment {
    private IntegerProperty payment_id;
    private IntegerProperty loaner_id;
    private IntegerProperty loan_id;
    private ObjectProperty<LocalDate> payment_date;
    private DoubleProperty payment_amount;



    
    public Payment(int payment_id, int loaner_id, int loan_id,
            LocalDate payment_date, double payment_amount) {
        this.payment_id = new SimpleIntegerProperty(payment_id);
        this.loaner_id = new SimpleIntegerProperty(loaner_id) ;
        this.loan_id = new SimpleIntegerProperty(loan_id) ;
        this.payment_date = new SimpleObjectProperty<LocalDate>(payment_date) ;
        this.payment_amount = new SimpleDoubleProperty(payment_amount) ;
    }

    // SETTERS
    public void setPayment_id(IntegerProperty payment_id) {
        this.payment_id = payment_id;
    }
    public void setLoaner_id(IntegerProperty loaner_id) {
        this.loaner_id = loaner_id;
    }

    public void setLoan_id(IntegerProperty loan_id) {
        this.loan_id = loan_id;
    }

    public void setPayment_date(ObjectProperty<LocalDate> payment_date) {
        this.payment_date = payment_date;
    }

    public void setPayment_amount(DoubleProperty payment_amount) {
        this.payment_amount = payment_amount;
    }

    // GETTERS
    public int getPayment_id(){
        return this.payment_id.get();
    }

    public int getLoaner_id(){
        return this.loaner_id.get();
    }

    public int getLoanProperty(){
        return this.loan_id.get();
    }

    public LocalDate getPaymentDate(){
        return this.payment_date.get();
    }

    public double getPayment_amount(){
        return this.getPayment_amount();
    }
    

    // PROPERTY GETTERS
    public IntegerProperty getPayment_id_Property() {
        return payment_id;
    }
    
    public IntegerProperty getLoaner_id_Property() {
        return loaner_id;
    }
    
    public IntegerProperty getLoan_id_Property() {
        return loan_id;
    }
    
    public ObjectProperty<LocalDate> getPayment_date_Property() {
        return payment_date;
    }
    
    public DoubleProperty getPayment_amount_Property() {
        return payment_amount;
    }
}
