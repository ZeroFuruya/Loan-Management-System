package e2p.icotp.util.custom.cbox;

import e2p.icotp.model.Loaner;
import javafx.scene.control.ListCell;

public class LoanerListCell extends ListCell<Loaner> {
    @Override
    protected void updateItem(Loaner loaner, boolean empty) {
        super.updateItem(loaner, empty);
        if (loaner == null || empty) {
            setText(null);
        } else {
            setText(loaner.getName());
        }
    }
}
