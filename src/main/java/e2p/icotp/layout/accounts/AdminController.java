package e2p.icotp.layout.accounts;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;
import javax.mail.MessagingException;

import e2p.icotp.App;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.AccountDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.util.custom.cipher.Encrypt;
import e2p.icotp.util.gmail.GMailer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminController {
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField codeTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private PasswordField confirmPassPF;

    @FXML
    private Label usernameErr;
    @FXML
    private Label passwordNoMatchErr;
    @FXML
    private Label invalidEmailErr;
    @FXML
    private Label incorrecrCodeErr;

    @FXML
    private Button signUpButton;
    @FXML
    private Button sendCodeButton;

    private App app;
    private Account admin;

    // TODO ADD SECURITY CODE

    String ver_code = "";

    BooleanProperty emailInputted = new SimpleBooleanProperty(false);

    @FXML
    private void handle_send_code() throws GeneralSecurityException, IOException, MessagingException {
        invalidEmailErr.setVisible(false);
        incorrecrCodeErr.setVisible(true);
        String temp_val;
        ver_code = "";
        ver_code = RandomIDGenerator.getRandomNumber() + "";
        for (int i = 0; i < 4; i++) {
            int initial_num = RandomIDGenerator.getRandomNumber();
            temp_val = Integer.toString(initial_num);
            ver_code = temp_val + ver_code;
        }
        GMailer mail_sender = new GMailer();

        String emailAddress = emailTF.getText();

        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(emailAddress);

        StringBuilder code_message = new StringBuilder(
                "You are trying to make an Admin account for Zephyr Loan Management System.");
        code_message.append("\n");
        code_message.append("\n");
        code_message.append("Verification Code: ");
        code_message.append(ver_code);

        if (!matcher.matches()) {
            invalidEmailErr.setVisible(true);
            return;
        }
        emailInputted.set(true);
        mail_sender.sendMail(emailAddress, "Loan Management Verification Code", code_message.toString());

        codeTF.textProperty().addListener((o, ov, nv) -> {
            if (nv.isEmpty() || nv.isBlank()) {
                incorrecrCodeErr.setVisible(true);
            }

            if (!nv.equals(ver_code)) {
                incorrecrCodeErr.setVisible(true);
                return;
            }

            incorrecrCodeErr.setVisible(false);
        });
    }

    @FXML
    void handle_signUp() throws Exception {
        SecretKey key = Encrypt.generateKey();
        String keyString = Encrypt.convertSecretKeyToString(key);
        Encrypt.prepareSecreteKey(keyString);
        String pass = passwordPF.getText();
        String encryptedPass = Encrypt.encrypt(pass, keyString);
        System.out.println(encryptedPass);

        admin.setAccountId(1);
        admin.setUsername(usernameTF.getText());
        admin.setPassword(encryptedPass);
        admin.setPassKey(keyString);
        admin.setEmail(emailTF.getText());
        AccountDAO.insert(admin);
        app.accountsMasterlist().add(admin);
        ModalLoader.modal_close(app);
        LogInLoader.load_log_in(app);
    }

    public void load(App app) {
        this.app = app;
        admin = new Account();

        init_bindings();
    }

    private void init_bindings() {

        BooleanBinding signUpList = Bindings.createBooleanBinding(() -> {
            return app.accountsMasterlist().stream()
                    .anyMatch(users -> usernameTF.textProperty().get().equals(users.getUsername()));
        }, usernameTF.textProperty());

        usernameErr.textProperty().bind(Bindings.when(signUpList).then("Username already taken").otherwise(
                Bindings.when(usernameTF.textProperty().isEmpty()).then("Field must not be empty").otherwise("")));
        usernameErr.visibleProperty().bind(signUpList.or(usernameTF.textProperty().isEmpty()));

        BooleanBinding isPassEmpty = passwordPF.textProperty().isEmpty();
        BooleanBinding isPassNotMatches = passwordPF.textProperty().isNotEqualTo(confirmPassPF.textProperty());

        passwordNoMatchErr.textProperty()
                .bind(Bindings.when(passwordPF.textProperty().isEmpty()).then("Field must not be empty")
                        .otherwise(Bindings.when(passwordPF.textProperty().isEqualTo(confirmPassPF.textProperty()))
                                .then("").otherwise("Password doesn't match")));

        signUpButton.disableProperty()
                .bind(usernameErr.visibleProperty()
                        .or(isPassEmpty.or(isPassNotMatches).or(invalidEmailErr.visibleProperty())
                                .or(incorrecrCodeErr.visibleProperty())));

        codeTF.visibleProperty().bind(emailInputted);
    }
}
