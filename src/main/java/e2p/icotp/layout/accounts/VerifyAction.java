package e2p.icotp.layout.accounts;

import e2p.icotp.App;
import e2p.icotp.service.loader.ModalLoader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

public class VerifyAction {
    App app;
    BooleanProperty isVerified;

    @FXML
    void handle_verification() {
        isVerified.set(true);
        ModalLoader.modal_close(app);
    }

    public void load(App app, BooleanProperty isVerified) {
        this.app = app;
        this.isVerified = new SimpleBooleanProperty(isVerified.get());
    }
}
