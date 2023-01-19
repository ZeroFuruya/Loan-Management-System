package e2p.icotp.util.custom;

import e2p.icotp.model.Loan;
import javafx.util.StringConverter;

public class LoanStringConverter extends StringConverter<Loan> {
    @Override
    public String toString(Loan object){
        return object.getStatus();
    }

    @Override
    public Loan fromString(String string){
        return null;
    }
}
