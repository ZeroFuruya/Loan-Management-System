package e2p.icotp.util.custom.cbox;

import e2p.icotp.model.LoanPlan;
import javafx.scene.control.ListCell;

public class LoanPlanListCell extends ListCell<LoanPlan> {
    @Override
    protected void updateItem(LoanPlan loan_plan, boolean empty) {
        super.updateItem(loan_plan, empty);
        if (loan_plan == null || empty) {
            setText(null);
        } else {
            setText(loan_plan.getId().get() + "");
        }
    }
}
