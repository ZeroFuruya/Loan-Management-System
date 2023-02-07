package e2p.icotp.util.custom;

import e2p.icotp.model.LoanPlan;
import javafx.util.StringConverter;

public class LoanPlanStringConverter extends StringConverter<LoanPlan> {
    @Override
    public String toString(LoanPlan object) {
        return object.getTypeProperty().get().getName().get();
    }

    @Override
    public LoanPlan fromString(String string) {
        return null;
    }
}
