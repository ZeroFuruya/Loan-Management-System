package e2p.icotp.model;

import java.time.LocalDate;

import e2p.icotp.model.Enums.LoanStatus;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loan {
    private IntegerProperty loan_id;
    private ObjectProperty<Loaner> loaner_id;
    private ObjectProperty<LoanType> loan_type;
    private ObjectProperty<LoanPlan> loan_plan;
    private ObjectProperty<LocalDate> release_date;
    private LongProperty term;
    private ObjectProperty<LocalDate> maturity_date;
    private DoubleProperty principal;
    private DoubleProperty interest;
    private DoubleProperty penalty;
    private StringProperty payment_frequency;
    private IntegerProperty due;
    private DoubleProperty paid;
    private DoubleProperty balance;
    private StringProperty status;
    private DoubleProperty next_payment;
    private ObjectProperty<LocalDate> next_due_date;
    private DoubleProperty total_unpaid;

    public Loan(int loan_id, Loaner loaner_id, LoanType loan_type, LoanPlan loan_plan, LocalDate release_date,
            long term,
            LocalDate maturity_date,
            double principal,
            double interest, double penalty, String payment_frequency, int due, double paid, double balance,
            String status, double next_payment,
            LocalDate next_due_date, double total_unpaid) {

        this.loan_id = new SimpleIntegerProperty(loan_id);
        this.loaner_id = new SimpleObjectProperty<>(loaner_id);
        this.loan_type = new SimpleObjectProperty<>(loan_type);
        this.loan_plan = new SimpleObjectProperty<>(loan_plan);
        this.release_date = new SimpleObjectProperty<>(release_date);
        this.term = new SimpleLongProperty(term);
        this.maturity_date = new SimpleObjectProperty<>(maturity_date);
        this.principal = new SimpleDoubleProperty(principal);
        this.interest = new SimpleDoubleProperty(interest);
        this.penalty = new SimpleDoubleProperty(penalty);
        this.payment_frequency = new SimpleStringProperty(payment_frequency);
        this.due = new SimpleIntegerProperty(due);
        this.paid = new SimpleDoubleProperty(paid);
        this.balance = new SimpleDoubleProperty(balance);
        this.status = new SimpleStringProperty(status);
        this.next_payment = new SimpleDoubleProperty(next_payment);
        this.next_due_date = new SimpleObjectProperty<>(next_due_date);
        this.total_unpaid = new SimpleDoubleProperty(total_unpaid);
    }

    public Loan() {
        this(0, new Loaner(), new LoanType(), new LoanPlan(), LocalDate.now(), 0, LocalDate.now(), 0.0, 0.0, 0.0, "", 0,
                0.0, 0.0, LoanStatus.APPLICATION, 0.0, LocalDate.now(), 0.0);
    }

    public Loan(int id) {
        this(id, new Loaner(), new LoanType(), new LoanPlan(), LocalDate.now(), 0, LocalDate.now(), 0.0, 0.0, 0.0, "",
                0,
                0.0, 0.0,
                LoanStatus.APPLICATION, 0.0, LocalDate.now(), 0.0);
    }

    // NEWLY ADDED PROPERTIES GETTER

    public StringProperty getPaymentFrequencyProperty() {
        return this.payment_frequency;
    }

    public DoubleProperty getTotalUnpaidProperty() {
        return this.total_unpaid;
    }

    // SETTERS
    public void setLoan_id(int loan_id) {
        this.loan_id.set(loan_id);
    }

    public void setLoaner_id(Loaner loaner_id) {
        this.loaner_id.set(loaner_id);
    }

    public void setLoanType(LoanType loan_type) {
        this.loan_type.set(loan_type);
    }

    public void setLoanPlan(LoanPlan loan_plan) {
        this.loan_plan.set(loan_plan);
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date.set(release_date);
    }

    public void setTerm(int term) {
        this.term.set(term);
    }

    public void setMaturity_date(LocalDate maturity_date) {
        this.maturity_date.set(maturity_date);
    }

    public void setPrincipal(double principal) {
        this.principal.set(principal);
    }

    public void setInterest(double interest) {
        this.interest.set(interest);
    }

    public void setPenalty(double penalty) {
        this.penalty.set(penalty);
    }

    public void setDue(int due) {
        this.due.set(due);
    }

    public void setPaid(double paid) {
        this.paid.set(paid);
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public void setStatus(String val) {
        this.status.set(val);
    }

    public void setNextPayment(double next_payment) {
        this.next_payment.set(next_payment);
    }

    public void setNextDueDate(LocalDate next_due_date) {
        this.next_due_date.set(next_due_date);
    }

    // GETTERS
    public int getLoan_id() {
        return this.loan_id.get();
    }

    public Loaner getLoaner_id() {
        return this.loaner_id.get();
    }

    public LoanType getLoanType() {
        return this.loan_type.get();
    }

    public LoanPlan getLoanPlan() {
        return this.loan_plan.get();
    }

    public LocalDate getRelease_date() {
        return this.release_date.get();
    }

    public long getTerm() {
        return this.term.get();
    }

    public LocalDate getMaturity_date() {
        return this.maturity_date.get();
    }

    public double getPrincipal() {
        return this.principal.get();
    }

    public double getInterest() {
        return this.interest.get();
    }

    public double getPenalty() {
        return this.penalty.get();
    }

    public int getDue() {
        return this.due.get();
    }

    public double getPaid() {
        return this.paid.get();
    }

    public double getBalance() {
        return this.balance.get();
    }

    public String getStatus() {
        return this.status.get();
    }

    public double getNextPayment() {
        return this.next_payment.get();
    }

    public LocalDate getNextDueDate() {
        return this.next_due_date.get();
    }

    // PROPERTY GETTERS
    public IntegerProperty getLoanID_Property() {
        return this.loan_id;
    }

    public ObjectProperty<Loaner> getLoanerID_Property() {
        return this.loaner_id;
    }

    public ObjectProperty<LoanType> getLoanType_Property() {
        return this.loan_type;
    }

    public ObjectProperty<LoanPlan> getLoanPlan_Property() {
        return this.loan_plan;
    }

    public ObjectProperty<LocalDate> getRelease_date_Property() {
        return this.release_date;
    }

    public LongProperty getTermProperty() {
        return this.term;
    }

    public ObjectProperty<LocalDate> getMaturity_date_Property() {
        return this.maturity_date;
    }

    public DoubleProperty getPrincipalProperty() {
        return this.principal;
    }

    public DoubleProperty getInterestProperty() {
        return this.interest;
    }

    public DoubleProperty getPenaltyProperty() {
        return this.penalty;
    }

    public IntegerProperty getDueProperty() {
        return this.due;
    }

    public DoubleProperty getPaidProperty() {
        return this.paid;
    }

    public DoubleProperty getBalanceProperty() {
        return this.balance;
    }

    public StringProperty getStatusProperty() {
        return this.status;
    }

    public DoubleProperty getNextPaymentProperty() {
        return this.next_payment;
    }

    public ObjectProperty<LocalDate> getNextdueDateProperty() {
        return this.next_due_date;
    }
}
