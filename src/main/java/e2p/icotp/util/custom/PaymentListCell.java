package e2p.icotp.util.custom;

import e2p.icotp.model.Payment;
import javafx.scene.control.ListCell;

public class PaymentListCell extends ListCell<Payment> {
    @Override
    protected void updateItem(Payment payment, boolean empty){
        super.updateItem(payment, empty);
        if(payment == null || empty){
            setText(null);
        }
        else{
            setText(payment.toString());
        }
    }
}
