package e2p.icotp.layout.accounts;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.service.loader.AdminLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class VerifyAction {
    App app;

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

        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_recovery() throws IOException {
        AdminLoader.load_set_up_passcode(app);
    }

    public void load(App app) {
        this.app = app;
        if (app.getAdminProperty().getAccountId() == 1) {
            ModalLoader.modal_close(app);
        }
        ModalLoader.modal_close(app);
    }
}
