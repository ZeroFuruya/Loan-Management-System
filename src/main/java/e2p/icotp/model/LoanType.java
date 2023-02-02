package e2p.icotp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoanType {
    private IntegerProperty type_id;
    private StringProperty type_name;
    private StringProperty type_desc;

    public LoanType(int id, String name, String desc) {
        this.type_id = new SimpleIntegerProperty(id);
        this.type_name = new SimpleStringProperty(name);
        this.type_desc = new SimpleStringProperty(desc);
    }

    public LoanType() {
        this(0, "", "");
    }

    public LoanType(LoanType loan_type) {
        this(loan_type.getId().get(), loan_type.getName().get(), loan_type.getDesc().get());
    }

    // PROPERTY GETTERS

    public IntegerProperty getId() {
        return this.type_id;
    }

    public StringProperty getName() {
        return this.type_name;
    }

    public StringProperty getDesc() {
        return this.type_desc;
    }
}
