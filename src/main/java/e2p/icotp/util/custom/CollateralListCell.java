package e2p.icotp.util.custom;

import e2p.icotp.model.Collateral;
import javafx.scene.control.ListCell;

public class CollateralListCell extends ListCell<Collateral> {
    @Override
    protected void updateItem(Collateral col, boolean empty){
        super.updateItem(col, empty);
        if(col == null || empty){
            setText(null);
        }
        else{
            setText(col.getCollateral());
        }
    }
}
