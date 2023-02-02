package e2p.icotp.util.custom;

import e2p.icotp.model.LoanType;
import javafx.util.StringConverter;

public class LoanTypeStringConverter extends StringConverter<LoanType> {
    @Override
    public String toString(LoanType object) {
        return object.getName().get();
    }

    @Override
    public LoanType fromString(String string) {
        return null;
    }
}
