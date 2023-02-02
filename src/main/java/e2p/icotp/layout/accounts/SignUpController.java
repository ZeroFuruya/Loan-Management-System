package e2p.icotp.layout.accounts;

import e2p.icotp.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class SignUpController {
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
    private Tooltip usernameTT;
    @FXML
    private Tooltip passwordTT;
    @FXML
    private Tooltip confirmPassTT;

    @FXML
    private Button signUpButton;
    @FXML
    private Hyperlink logIn;

    private App app;
    private SignUp signUp;

    @FXML
    void handle_signUp(){
        
    }

    public void load(App app) {
        this.app = app;
        init_bindings();
    }

    private void init_bindings() {

        signUpButton.disableProperty().bind(usernameLabel.visibleProperty().or(passwordLabel.visibleProperty())
                .or(confirmPassLabel.visibleProperty()));
    }
}
