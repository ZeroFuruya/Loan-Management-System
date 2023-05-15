package e2p.icotp.layout.accounts;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import e2p.icotp.App;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.AccountDAO;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class SetUpPasscode {

    @FXML
    PasswordField pf_passcode;
    @FXML
    PasswordField pf_passcode_confirm;

    @FXML
    Label label_error_message;

    @FXML
    Button btn_confirm;

    App app;

    Account admin;
    Account admin_copy;

    @FXML
    void handle_passcode() throws NoSuchAlgorithmException, IOException {
        String pass = pf_passcode.getText();
        String encryptedPass = Encrypt.encrypt(pass, admin.getPassKey());

        admin.getPassCodeProperty().set(encryptedPass);
        // AccountDAO.removeByLoanId(admin_copy);
        // AccountDAO.insert(admin);
        AccountDAO.update(admin);

        ModalLoader.modal_close(app);
    }

    public void load(App app) {
        this.app = app;

        this.admin = app.getAdminProperty();
        this.admin_copy = new Account(admin);

        init_bindings();
    }

    private void init_bindings() {

        BooleanBinding isPassEmpty = Bindings.createBooleanBinding(() -> {
            return pf_passcode.textProperty().isEmpty().get() ? true : false;
        }, pf_passcode.textProperty());
        BooleanBinding isPassConfirmEmpty = Bindings.createBooleanBinding(() -> {
            return pf_passcode_confirm.textProperty().isEmpty().get() ? true : false;
        }, pf_passcode_confirm.textProperty());

        // label_error_message.visibleProperty().bind(isPassEmpty);
        label_error_message.textProperty()
                .bind((Bindings.when(pf_passcode.textProperty().isEqualTo(pf_passcode_confirm.textProperty()))
                        .then("Correct").otherwise("Password doesn't match")));
        label_error_message.visibleProperty()
                .bind(pf_passcode.textProperty().isEqualTo(pf_passcode_confirm.textProperty()).not());
        // TODO FIX BUTTON
        // btn_confirm.disableProperty().bind(label_error_message.visibleProperty().or(isPassEmpty));

        btn_confirm.disableProperty()
                .bind(label_error_message.visibleProperty().or(isPassEmpty).or(isPassConfirmEmpty));
    }
}