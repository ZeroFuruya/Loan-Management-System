package e2p.icotp.layout.accounts;

import java.util.concurrent.atomic.AtomicBoolean;

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
import javafx.scene.control.Tooltip;

public class LogInController {
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordFieldPF;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;

    @FXML
    private Tooltip usernameTT;
    @FXML
    private Tooltip passwordTT;

    @FXML
    private Button logInButton;

    @FXML
    private Hyperlink signUp;

    @FXML
    private void handle_signUp() {

    }

    @FXML
    private void handle_login() {
        login = new Login(usernameTF.getText(), passwordFieldPF.getText());
        app.getSignUpList().forEach(user -> {
            if (user.getUsername().equals(login.getUsername()) && user.getPassword().equals(login.getPassword()))
                ;
            {
                loginAtomic.set(true);
            }
        });
        if (loginAtomic.get()) {
            LogInLoader.modal_close(app);
        } else {

        }

    }

    private App app;
    private Login login;

    BooleanBinding loginMatches;
    AtomicBoolean loginAtomic;

    public void load(App app) {
        this.app = app;
        load_fields();
        init_bindings();
    }

    private void load_fields() {
        loginAtomic = new AtomicBoolean(false);
    }

    private void init_bindings() {

        BooleanBinding loginExist = Bindings.createBooleanBinding(() -> {
            return !app.getSignUpList().stream()
                    .anyMatch(users -> usernameTF.textProperty().get().equals(users.getUsername()));
        }, usernameTF.textProperty());

        loginMatches = Bindings.createBooleanBinding(() -> {
            return !app.getLoginList().stream().anyMatch(acc -> login == acc);
        }, passwordFieldPF.textProperty());

        logInButton.disableProperty().bind(usernameLabel.visibleProperty().or(passwordLabel.visibleProperty()));

        usernameLabel.visibleProperty().bind(usernameTF.textProperty().isEmpty().or(loginExist));
        passwordLabel.visibleProperty().bind(passwordFieldPF.textProperty().isEmpty().or(loginExist));

        usernameTT.textProperty()
                .bind(Bindings.when(loginExist).then("Account doesn't Exist. ").otherwise("Invalid Username"));
    }
}
