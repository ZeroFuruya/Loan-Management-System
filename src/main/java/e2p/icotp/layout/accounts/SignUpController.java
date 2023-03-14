package e2p.icotp.layout.accounts;

import java.io.IOException;

import javax.crypto.SecretKey;

import e2p.icotp.App;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.AccountDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;

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

    @FXML
    void handle_signUp() throws Exception {
        SecretKey key = Encrypt.generateKey();
        String keyString = Encrypt.convertSecretKeyToString(key);
        Encrypt.prepareSecreteKey(keyString);
        String pass = passwordPF.getText();
        String encryptedPass = Encrypt.encrypt(pass, keyString);
        System.out.println(encryptedPass);

        generate_id();
        user.setUsername(usernameTF.getText());
        user.setPassword(encryptedPass);
        user.setPassKey(keyString);
        AccountDAO.insert(user);
        app.accountsMasterlist().add(user);

        ModalLoader.modal_close(app);
        LogInLoader.load_log_in(app);

        // TODO DECRYPT PASS ON LOG IN
    }

    @FXML
    void handle_loginLink() throws IOException {
        LogInLoader.load_log_in(app);
    }

    public void load(App app) {
        this.app = app;
        user = new Account();
        init_bindings();
    }

    private void init_bindings() {

        BooleanBinding signUpList = Bindings.createBooleanBinding(() -> {
            return app.accountsMasterlist().stream()
                    .anyMatch(users -> usernameTF.textProperty().get().equals(users.getUsername()));
        }, usernameTF.textProperty());

        usernameErr.textProperty().set("Username already taken");
        usernameErr.visibleProperty().bind(signUpList);

        passwordNoMatchErr.textProperty()
                .bind(Bindings.when(passwordPF.textProperty().isEqualTo(confirmPassPF.textProperty()))
                        .then("").otherwise("Password doesn't match"));
        signUpButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
            return usernameErr.textProperty().isEqualTo("Password doesn't match").get() ? true : false;
        }, usernameErr.textProperty()));

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
