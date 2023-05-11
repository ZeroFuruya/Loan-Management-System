package e2p.icotp.layout.accounts;

import e2p.icotp.App;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class VerifyAction {
    App app;
    BooleanProperty isVerified;

    @FXML
    PasswordField pf_code;

    @FXML
    Label password_error;

    @FXML
    void handle_verification() {
        String decryptedPass = Encrypt.decrypt(app.getAdminProperty().getPassword(),
                app.getAdminProperty().getPassKey());
        String passFieldInput = pf_code.getText();

        if (!decryptedPass.equals(passFieldInput)) {
            password_error.visibleProperty().set(true);
            return;
        }

        isVerified.set(true);
        ModalLoader.modal_close(app);
    }

    public void load(App app, BooleanProperty isVerified) {
        this.app = app;
        this.isVerified = new SimpleBooleanProperty(isVerified.get());
    }
}
