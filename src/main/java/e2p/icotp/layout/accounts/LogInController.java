package e2p.icotp.layout.accounts;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {
    @FXML
    private TextField username_field;
    @FXML
    private PasswordField password_field;

    @FXML
    private Label username_error;
    @FXML
    private Label password_error;
    @FXML
    private Label label_modal;

    @FXML
    private Button logIn_button;

    @FXML
    private Hyperlink sign_up;

    private Account account = new Account();

    private boolean isLoggedIn;

    @FXML
    private void handle_signUp() throws IOException {
        ModalLoader.modal_close(app);
        LogInLoader.load_sign_up(app);
    }

    @FXML
    private void handle_login() throws IOException {
        String decryptedPass = Encrypt.decrypt(account.getPassword(), account.getPassKey());
        String passFieldInput = password_field.getText();

        if (!decryptedPass.equals(passFieldInput)) {
            password_error.visibleProperty().set(true);
            return;
        }
        password_error.visibleProperty().set(false);

        MainController.getisNotLoggedIn().set(false);

        if (account.getAccountId() == 1) {
            app.setAdminProperty(account);
        }

        ModalLoader.modal_close(app);

        if (!isLoggedIn) {
            return;
        }

        LogInLoader.load_set_up_passcode(app);
    }

    private App app;

    public void load(App app, boolean isLoggedIn) {
        this.app = app;
        this.isLoggedIn = isLoggedIn;

        init_bindings();
    }

    private void init_bindings() {

        if (isLoggedIn) {
            label_modal.textProperty().set("Confirm Admin");
        } else {
            label_modal.textProperty().set("Log In");
        }

        password_error.setVisible(false);

        BooleanBinding loginExist = Bindings.createBooleanBinding(() -> {
            return !app.accountsMasterlist().stream()
                    .anyMatch(users -> username_field.textProperty().get().equals(users.getUsername()));
        }, username_field.textProperty());

        username_error.textProperty().bind(Bindings.when(username_field.textProperty().isEmpty()).then("Empty field")
                .otherwise(Bindings.when(loginExist).then("Account does not exist")
                        .otherwise("")));

        logIn_button.disableProperty().bind(username_error.textProperty().isNotEqualTo(""));
    }

    @FXML
    private void validate_user() {
        app.accountsMasterlist().forEach(user -> {
            if (username_field.getText().equals(user.getUsername())) {
                account = user;
                return;
            }
        });
    }
}
