package e2p.icotp.util.custom;

import e2p.icotp.model.Payment;
import javafx.util.StringConverter;

public class PaymentStringConverter extends StringConverter<Payment> {
    @Override
    public String toString(Payment object){
        return object.toString();
    }

    @Override
    public Payment fromString(String string){
        return null;
    }
}
