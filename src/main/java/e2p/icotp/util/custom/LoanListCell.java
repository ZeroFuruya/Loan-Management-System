package e2p.icotp.util.custom;

import e2p.icotp.model.Loan;
import javafx.scene.control.ListCell;

public class LoanListCell extends ListCell<Loan> {
    @Override
    protected void updateItem(Loan loan, boolean empty){
        super.updateItem(loan, empty);
        if(loan == null || empty){
            setText(null);
        }
        else{
            setText(loan.getStatus());
        }
    }
}
