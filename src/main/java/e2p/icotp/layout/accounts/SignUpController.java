package e2p.icotp.layout.accounts;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import e2p.icotp.App;
import e2p.icotp.service.RegistryService;
import e2p.icotp.service.XMLService;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.util.FileUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class SignUpController implements Initializable {
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private PasswordField confirmPassPF;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label confirmPassLabel;
    @FXML
    private Label shownPasswordLabel;

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
    private SignUp signUp;
    private SignUp password;
    private SignUp confirmPassword;

    @FXML
    void passwordFieldKeyTyped(KeyEvent event) {
        shownPasswordLabel.textProperty().bind(Bindings.concat(passwordPF.getText()));

    }

    @FXML
    void handle_showPasswordButton(ActionEvent event) {
        if (showPasswordButton.isSelected()) {
            shownPasswordLabel.setVisible(true);
            shownPasswordLabel.textProperty().bind(Bindings.concat(passwordPF.getText()));
            showPasswordButton.setText("Hide");
        } else {
            shownPasswordLabel.setVisible(false);
            showPasswordButton.setText("show");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shownPasswordLabel.setVisible(true);
    }

    @FXML
    void handle_passwordField() {

        signUpButton.setVisible(false);
    }

    @FXML
    void handle_signUp() throws Exception {
        signUp = new SignUp(usernameTF.getText(), passwordPF.getText(), confirmPassPF.getText());
        app.getSignUpList().add(signUp);

        app.getSignUpList().forEach(p -> {
            System.out.println(p.getUsername());
        });

        File xml = RegistryService.getXML_FromRegistry();
        if (xml != null) {
            XMLService.wrap_signUpXML(app, xml);
        } else {
            FileUtil.create_dir(FileUtil.DATA_DIR + "Admin_users" + FileUtil.FS);
            File newXml = new File(FileUtil.DATA_DIR + "Admin_users" + FileUtil.FS + "users_accounts_xml");

            if (newXml != null) {
                if (!newXml.getPath().endsWith(".xml")) {
                    newXml = new File(newXml.getPath() + ".xml");
                }
                XMLService.wrap_signUpXML(app, newXml);
            }
        }
        // verification();
        buttonClick();
    }

    @FXML
    void handle_loginLink() throws IOException {
        LogInLoader.load_log_in(app);
    }

    private void buttonClick() {
        ModalLoader.modal_close(app);
    }

    public void load(App app) {
        this.app = app;
        init_bindings();
    }

    private void init_bindings() {

        BooleanBinding signUpList = Bindings.createBooleanBinding(() -> {
            return app.getSignUpList().stream()
                    .anyMatch(users -> usernameTF.textProperty().get().equals(users.getUsername()));
        }, usernameTF.textProperty());

        BooleanBinding passwordMatches = Bindings.createBooleanBinding(() -> {

            // validatePassword();
            verification();
            return !app.getSignUpList().stream().anyMatch(
                    pass -> password == pass);

        }, confirmPassPF.textProperty());

        usernameLabel.visibleProperty().bind(usernameTF.textProperty().isEmpty().or(signUpList));
        passwordLabel.visibleProperty().bind(passwordPF.textProperty().isEmpty().or(signUpList));
        confirmPassLabel.visibleProperty()
                .bind(confirmPassPF.textProperty().isEmpty().or(signUpList));

        usernameTT.textProperty()
                .bind(Bindings.when(signUpList).then("Account already exists. ").otherwise("Invalid Username "));

        confirmPassTT.textProperty()
                .bind(Bindings.when(passwordMatches).then("Password not match").otherwise("Invalid Confirmation"));
        // validatePassword();
        signUpButton.disableProperty()
                .bind(usernameLabel.visibleProperty().or(passwordLabel.visibleProperty().or(passwordMatches))
                        .or(confirmPassLabel.visibleProperty()));

    }

    private boolean validatePassword() {
        Pattern pattern = Pattern.compile("((?=.*\\\\d)(?=.*[a-z])(?=.*[@#$%]).{6,15})");
        Matcher match = pattern.matcher(passwordPF.getText());
        if (match.matches()) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Password must contain at least one (Digit, Lowercase, Uppercase and Special Character) and length must be between 6 - 15 ");
            alert.showAndWait();
        }
        return false;
    }

    private void verification() {
        if (password != null) {
            passwordPF.textProperty().get().equals(confirmPassPF.textProperty().get());

            password.setPassword(passwordPF.textProperty().get());
            confirmPassword.setConfirmPassword(password.getPassword());

        } else {
            app.getSignUpList();
        }

    }

}
