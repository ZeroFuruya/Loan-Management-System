package e2p.icotp.util.custom.cbox;

import e2p.icotp.model.LoanPlan;
import javafx.util.StringConverter;

public class LoanPlanStringConverter extends StringConverter<LoanPlan> {
    @Override
    public String toString(LoanPlan object) {
        return object.getId().get() + "";
    }

    @Override
    public LoanPlan fromString(String string) {
        return null;
    }
}
