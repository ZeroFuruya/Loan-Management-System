package e2p.icotp.layout.accounts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import e2p.icotp.App;
import e2p.icotp.service.loader.LogInLoader;
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
    private Button logIn_button;

    @FXML
    private Hyperlink sign_up;

    private Account account = new Account();

    @FXML
    private void handle_signUp() throws IOException {
        LogInLoader.load_sign_up(app);
    }

    @FXML
    private void handle_login() throws UnsupportedEncodingException {
        // byte[] textEncrypted = account.getPassword().getBytes("UTF8");
        // byte[] decodedKey = Base64.getDecoder().decode(account.getPassKey());
        // SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length,
        // "DES");

        // System.out.println("Encrypted: " + account.getPassword());
        // System.out.println("Decrypted: " + decryptPassword(textEncrypted,
        // originalKey));

    }

    private App app;

    public void load(App app) {
        this.app = app;
        init_bindings();
    }

    private void init_bindings() {

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

    // private String decryptPassword(byte[] textEncrypted, SecretKey myDesKey) {
    // String s = "";
    // System.out.println(textEncrypted);
    // try {

    // Cipher desCipher;
    // desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

    // // Decrypting text
    // desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
    // byte[] textDecrypted = desCipher.doFinal(textEncrypted);

    // // Converting decrypted byte array to string
    // s = new String(textDecrypted);
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // return s;
    // }
}
