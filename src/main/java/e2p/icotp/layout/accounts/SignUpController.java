package e2p.icotp.layout.accounts;

import java.io.IOException;

import javax.crypto.SecretKey;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.AccountDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private PasswordField confirmPassPF;

    @FXML
    private Label usernameErr;
    @FXML
    private Label passwordNoMatchErr;

    @FXML
    private Button signUpButton;

    @FXML
    private Hyperlink logIn;

    private App app;
    private Account user;
    private MainController mc;

    @FXML
    void handle_signUp() throws Exception {
        SecretKey key = Encrypt.generateKey();
        String keyString = Encrypt.convertSecretKeyToString(key);
        Encrypt.prepareSecreteKey(keyString);
        String pass = passwordPF.getText();
        String encryptedPass = Encrypt.encrypt(pass, keyString);

        generate_id();
        user.setUsername(usernameTF.getText());
        user.setPassword(encryptedPass);
        user.setPassKey(keyString);
        AccountDAO.insert(user);
        app.accountsMasterlist().add(user);

        ModalLoader.modal_close(app);
        LogInLoader.load_log_in(app, false, mc);

        // TODO DECRYPT PASS ON LOG IN
    }

    @FXML
    void handle_loginLink() throws IOException {
        ModalLoader.modal_close(app);
        LogInLoader.load_log_in(app, false, mc);
    }

    public void load(App app, MainController mc) {
        this.app = app;
        user = new Account();
        this.mc = mc;
        init_bindings();
    }

    private void init_bindings() {

        BooleanBinding signUpList = Bindings.createBooleanBinding(() -> {
            return app.accountsMasterlist().stream()
                    .anyMatch(users -> usernameTF.textProperty().get().equals(users.getUsername()));
        }, usernameTF.textProperty());

        BooleanBinding isPassEmpty = Bindings.createBooleanBinding(() -> {
            return passwordPF.textProperty().isEmpty().get() ? true : false;
        }, passwordPF.textProperty());
        BooleanBinding isPassConfirmEmpty = Bindings.createBooleanBinding(() -> {
            return confirmPassPF.textProperty().isEmpty().get() ? true : false;
        }, confirmPassPF.textProperty());

        usernameErr.textProperty().bind(Bindings.when(signUpList).then("Username already taken").otherwise(
                Bindings.when(usernameTF.textProperty().isEmpty()).then("Field must not be empty").otherwise("")));
        usernameErr.visibleProperty().bind(signUpList.or(usernameTF.textProperty().isEmpty()));

        passwordNoMatchErr.textProperty()
                .bind(Bindings.when(passwordPF.textProperty().isEqualTo(confirmPassPF.textProperty()))
                        .then("").otherwise("Password doesn't match"));
        passwordNoMatchErr.visibleProperty()
                .bind(passwordPF.textProperty().isEqualTo(confirmPassPF.textProperty()).not());

        signUpButton.disableProperty()
                .bind(passwordNoMatchErr.visibleProperty().or(isPassEmpty).or(isPassConfirmEmpty));

    }

    int final_num = 0;

    private void generate_id() {
        String temp_val;
        String string_val = "";
        string_val = RandomIDGenerator.getRandomNumber() + "";
        for (int i = 0; i < 3; i++) {
            int initial_num = RandomIDGenerator.getRandomNumber();
            temp_val = Integer.toString(initial_num);
            string_val = temp_val + string_val;
        }
        final_num = Integer.parseInt(string_val);

        app.loanPlanMasterlist().forEach(loan_plan -> {
            if (loan_plan.getId().get() == final_num) {
                generate_id();
            } else {
                user.setAccountId(final_num);
            }
        });
    }
}
