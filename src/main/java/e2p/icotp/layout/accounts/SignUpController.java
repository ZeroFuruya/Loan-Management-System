package e2p.icotp.layout.accounts;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import e2p.icotp.App;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.AccountDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
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
import javafx.scene.control.Tooltip;
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
    private Tooltip usernameTT;
    @FXML
    private Tooltip passwordTT;
    @FXML
    private Tooltip confirmPassTT;

    @FXML
    private Button signUpButton;
    @FXML
    private ToggleButton showPasswordButton;
    @FXML
    private ToggleButton confirmPasswordButton;

    @FXML
    private Hyperlink logIn;

    private App app;
    private Account user;
    SecretKey myDesKey;
    byte[] textEncrypted;

    @FXML
    void passwordFieldKeyTyped(KeyEvent event) {
    }

    @FXML
    void handle_showPasswordButton(ActionEvent event) {
    }

    @FXML
    void handle_passwordField() {

        signUpButton.setVisible(false);
    }

    @FXML
    void handle_signUp() throws Exception {
        // Encrypt Pass
        String encryptedPass = encryptPassword(passwordPF.getText());
        String encodedKey = Base64.getEncoder().encodeToString(myDesKey.getEncoded());

        generate_id();
        user.setUsername(usernameTF.getText());
        user.setPassword(encryptedPass);
        user.setPassKey(encodedKey);
        AccountDAO.insert(user);
        app.accountsMasterlist().add(user);
        ModalLoader.modal_close(app);

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

    private SecretKey generateKey() throws NoSuchAlgorithmException {
        // Generating objects of KeyGenerator &
        // SecretKey
        KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
        SecretKey myDesKey = keygenerator.generateKey();

        return myDesKey;
    }

    private String encryptPassword(String pass) throws NoSuchAlgorithmException {
        String s = "";
        myDesKey = generateKey();
        try {
            // Creating object of Cipher
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");

            // Creating byte array to store string
            byte[] text = pass.getBytes("UTF8");

            // Encrypting text
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            textEncrypted = desCipher.doFinal(text);

            // Converting encrypted byte array to string
            s = new String(textEncrypted);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return s;
    }

    // private void decryptPassword(byte[] textEncrypted) {
    // try {

    // Cipher desCipher;
    // desCipher = Cipher.getInstance("DES");

    // // Decrypting text
    // desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
    // byte[] textDecrypted = desCipher.doFinal(textEncrypted);

    // // Converting decrypted byte array to string
    // String s = new String(textDecrypted);
    // System.out.println(s);
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

    int final_num = 0;

    private void generate_id() {
        String temp_val;
        String string_val = "";
        for (int i = 0; i < 4; i++) {
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
