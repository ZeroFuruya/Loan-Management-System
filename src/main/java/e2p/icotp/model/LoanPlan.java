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

    // PROPERTY GETTERS

    public IntegerProperty getId() {
        return this.plan_id;
    }

    public ObjectProperty<LoanType> getType() {
        return this.loan_type;
    }

    public LongProperty getTerm() {
        return this.term;
    }

    public DoubleProperty getInterest() {
        return this.interest;
    }

    public DoubleProperty getPenalty() {
        return this.penalty;
    }
}
