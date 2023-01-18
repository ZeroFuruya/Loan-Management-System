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
    public void setLoan_id(int loan_id){
        this.loan_id.set(loan_id);
    }

    public void setLoaner_id(int loaner_id){
        this.loaner_id.set(loaner_id);
    }

    public void setRelease_date(LocalDate release_date){
        this.release_date.set(release_date);
    }

    public void setTerm(int term){
        this.term.set(term);
    }

    public void setMaturity_date(LocalDate maturity_date){
        this.maturity_date.set(maturity_date);
    }

    public void setPrincipal(double principal){
        this.principal.set(principal);
    }

    public void setInterest(double interest){
        this.interest.set(interest);
    }

    public void setPenalty(double penalty){
        this.penalty.set(penalty);
    }

    public void setDue(double due){
        this.due.set(due);
    }

    public void setPaid(double paid){
        this.paid.set(paid);
    }

    public void setBalance(double balance){
        this.balance.set(balance);
    }

    public void setStatus(String val) {
        this.status.set(val);
        ;
    }

    // GETTERS
    public int getLoan_id(){
        return this.loan_id.get();
    }

    public int getLoaner_id(){
        return this.loaner_id.get();
    }

    public LocalDate getRelease_date(){
        return this.release_date.get();
    }

    public int getTerm(){
        return this.term.get();
    }

    public LocalDate getMaturity_date(){
        return this.maturity_date.get();
    }

    public Double getPrincipal(){
        return this.principal.get();
    }

    public Double getInterest(){
        return this.interest.get();
    }

    public Double getPenalty(){
        return this.penalty.get();
    }

    public Double getDue(){
        return this.due.get();
    }

    public Double getPaid(){
        return this.paid.get();
    }

    public Double getBalance(){
        return this.balance.get();
    }

    public String getStatus() {
        return this.status.get();
    }

    // PROPERTY GETTERS
    public IntegerProperty getLoanID_Property(){
        return this.loan_id;
    }

    public IntegerProperty getLoanerID_Property(){
        return this.loaner_id;
    }

    public ObjectProperty<LocalDate> getRelease_date_Property(){
        return this.release_date;
    }

    public IntegerProperty getTermProperty(){
        return this.term;
    }

    public ObjectProperty<LocalDate> getMaturity_date_Property(){
        return this.maturity_date;
    }

    public DoubleProperty getPrincipalProperty(){
        return this.principal;
    }

    public DoubleProperty getInterestProperty(){
        return this.interest;
    }

    public DoubleProperty getPenaltyProperty(){
        return this.penalty;
    }

    public DoubleProperty getDueProperty(){
        return this.due;
    }

    public DoubleProperty getPaidProperty(){
        return this.paid;
    }

    public DoubleProperty getBalanceProperty(){
        return this.balance;
    }

    public StringProperty getStatusProperty() {
        return this.status;
    }
}
