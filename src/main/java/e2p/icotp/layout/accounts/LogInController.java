package e2p.icotp.layout.accounts;

import java.io.IOException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.service.loader.AdminLoader;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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

    private MainController mc;

    @FXML
    private void handle_signUp() throws IOException {
        ModalLoader.modal_close(app);
        LogInLoader.load_sign_up(app, mc);
    }

    @FXML
    private void handle_login() throws IOException {
        String decryptedPass = Encrypt.decrypt(account.getPassword(), account.getPassKey());
        String passFieldInput = password_field.getText();

        if (!decryptedPass.equals(passFieldInput)) {
            password_error.visibleProperty().set(true);
            return;
        }
        ModalLoader.modal_close(app);
        password_error.visibleProperty().set(false);

        MainController.getisNotLoggedIn().set(false);

        if (account != null) {
            if (account.getAccountId() == 1) {
                app.setAdminProperty(account);
                mc.set_user_name("Admin");
                if (account.getPassCodeProperty().isNotNull().get()) {
                    if (account.getPassCodeProperty().get().isEmpty()
                            || account.getPassCodeProperty().get().isBlank()) {
                        AdminLoader.load_set_up_passcode(app);
                    }
                }
            } else {
                app.setAdminProperty(account);
                mc.set_user_name(app.getAdminProperty().getUsername());
            }
        }

        if (!isLoggedIn) {
            return;
        }
        ModalLoader.modal_close(app);
    }

    private App app;

    public void load(App app, boolean isLoggedIn, MainController mc) {
        this.app = app;
        this.isLoggedIn = isLoggedIn;
        this.mc = mc;

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
