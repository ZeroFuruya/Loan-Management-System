package e2p.icotp.util.custom;

import e2p.icotp.model.Loaner;
import javafx.util.StringConverter;

public class LoanerStringConverter extends StringConverter<Loaner> {
    @Override
    public String toString(Loaner object){
        return object.getName();
    }

    @Override
    public Loaner fromString(String string){
        return null;
    }
}
