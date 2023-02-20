package e2p.icotp.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoanPlan {
    private IntegerProperty plan_id;
    private ObjectProperty<LoanType> loan_type;
    private LongProperty term;
    private DoubleProperty interest;
    private DoubleProperty penalty;

    public LoanPlan(int plan, LoanType type, long term, double interest, double penalty) {
        this.plan_id = new SimpleIntegerProperty(plan);
        this.loan_type = new SimpleObjectProperty<>(type);
        this.term = new SimpleLongProperty(term);
        this.interest = new SimpleDoubleProperty(interest);
        this.penalty = new SimpleDoubleProperty(penalty);
    }

    public LoanPlan() {
        this(0, new LoanType(), 0, 0, 0);
    }

    // SETTERS 
    public void setPlan_id(int plan_id){
        this.setPlan_id(plan_id);
    }
    public void setLoan_type(LoanType loan_Type){
        this.setLoan_type(loan_Type);
    } 
    public void setTerm(Long term){
        this.term.set(term);
    }
    public void setInterest(double interest){
        this.interest.set(interest);
    }
    public void setPenalty(double penalty){
        this.penalty.set(penalty);
    }

    // GETTERS
    public int getPlan_id(){
        return this.plan_id.get();
    }
    public LoanType getLoanType(){
        return this.loan_type.get();
    }
    public long getTerm(){
        return this.term.get();
    }
    public double getInterest(){
        return this.interest.get();
    }
    public double getPenalty(){
        return this.penalty.get();
    }

    // PROPERTY GETTERS

    public IntegerProperty getIdProperty() {
        return this.plan_id;
    }

    public ObjectProperty<LoanType> getTypeProperty() {
        return this.loan_type;
    }

    public LongProperty getTermProperty() {
        return this.term;
    }

    public DoubleProperty getInterestProperty() {
        return this.interest;
    }

    public DoubleProperty getPenaltyProperty() {
        return this.penalty;
    }
}
