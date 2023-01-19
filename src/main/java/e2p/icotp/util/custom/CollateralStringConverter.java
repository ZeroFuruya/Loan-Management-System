package e2p.icotp.util.custom;

import e2p.icotp.model.Collateral;
import javafx.util.StringConverter;

public class CollateralStringConverter extends StringConverter<Collateral> {
    @Override
    public String toString(Collateral object){
        return object.getCollateral();
    }

    @Override
    public Collateral fromString(String string){
        return null;
    }
}
