package e2p.icotp.util.custom;

import e2p.icotp.model.LoanType;
import javafx.scene.control.ListCell;

public class LoanTypeListCell extends ListCell<LoanType> {
    @Override
    protected void updateItem(LoanType loan_type, boolean empty) {
        super.updateItem(loan_type, empty);
        if (loan_type == null || empty) {
            setText(null);
        } else {
            setText(loan_type.getName().get());
        }
    }
}
