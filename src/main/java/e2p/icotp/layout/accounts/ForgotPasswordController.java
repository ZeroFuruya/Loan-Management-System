package e2p.icotp.layout.accounts;

import javax.crypto.SecretKey;

import e2p.icotp.App;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.AccountDAO;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ForgotPasswordController {
    @FXML
    private TextField codeTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private PasswordField confirmPassPF;

    @FXML
    private Label emailAccount_label;

    @FXML
    private Label passwordNoMatchErr;
    @FXML
    private Label incorrecrCodeErr;

    @FXML
    private Button changePass_button;

    @FXML
    private VBox field_container_vbox;

    private App app;
    private Account admin;
    private Account admin_copy;

    String ver_code = "";

    BooleanProperty isValidCode = new SimpleBooleanProperty(false);

    // @FXML
    // private void handle_send_code() throws GeneralSecurityException, IOException,
    // MessagingException {
    // incorrecrCodeErr.setVisible(true);
    // String temp_val;
    // ver_code = "";
    // ver_code = RandomIDGenerator.getRandomNumber() + "";
    // for (int i = 0; i < 6; i++) {
    // int initial_num = RandomIDGenerator.getRandomNumber();
    // temp_val = Integer.toString(initial_num);
    // ver_code = temp_val + ver_code;
    // }
    // GMailer mail_sender = new GMailer();

    // StringBuilder code_message = new StringBuilder(
    // "You are trying to change your forgotten password for Zephyr Loan Management
    // System.\n Please enter this code on the field from the app.");
    // code_message.append("\n");
    // code_message.append("\n");
    // code_message.append("Verification Code: ");
    // code_message.append(ver_code);

    // mail_sender.sendMail(admin.getSecurityQuestion(), "Zephyr Loan Management
    // Verification Code",
    // code_message.toString());

    // codeTF.textProperty().addListener((o, ov, nv) -> {
    // if (nv.isEmpty() || nv.isBlank()) {
    // incorrecrCodeErr.setVisible(true);
    // }

    // if (!nv.equals(ver_code)) {
    // incorrecrCodeErr.setVisible(true);
    // return;
    // }
    // isValidCode.set(true);
    // incorrecrCodeErr.setVisible(false);
    // });
    // }

    @FXML
    void handle_change_pass() throws Exception {
        SecretKey key = Encrypt.generateKey();
        String keyString = Encrypt.convertSecretKeyToString(key);
        Encrypt.prepareSecreteKey(keyString);
        String pass = passwordPF.getText();
        String encryptedPass = Encrypt.encrypt(pass, keyString);
        System.out.println(encryptedPass);

        admin.setPassword(encryptedPass);
        admin.setPassKey(keyString);
        admin.setAccountId(admin.getAccountId());
        admin.setSecurityQuestion(admin.getSecurityQuestion());
        admin.setUsername(admin.getUsername());
        AccountDAO.remove(admin_copy);
        AccountDAO.insert(admin);
        app.accountsMasterlist().add(admin);

        ModalLoader.modal_close(app);
    }

    public void load(App app) {
        this.app = app;
        admin = app.getAdminProperty();
        admin_copy = new Account(admin);

        System.out.println(admin.getUsername());
        System.out.println(admin.getAccountId());
        System.out.println(admin.getSecurityQuestion());
        System.out.println(admin.getPassKey());
        System.out.println(admin.getPassword());

        init_bindings();
    }

    private void init_bindings() {

        // emailAccount_label.textProperty().bind(admin.getSecurityQuestionProperty());

        BooleanBinding isPassEmpty = passwordPF.textProperty().isEmpty();
        BooleanBinding isPassNotMatches = passwordPF.textProperty().isNotEqualTo(confirmPassPF.textProperty());

        passwordNoMatchErr.textProperty()
                .bind(Bindings.when(passwordPF.textProperty().isEmpty()).then("Field must not be empty")
                        .otherwise(Bindings.when(passwordPF.textProperty().isEqualTo(confirmPassPF.textProperty()))
                                .then("").otherwise("Password doesn't match")));

        changePass_button.disableProperty()
                .bind(isPassEmpty.or(isPassNotMatches).or(incorrecrCodeErr.visibleProperty()));

    }
}
