package e2p.icotp.layout.accounts;

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
    private Button signUp;
    @FXML
    private Hyperlink logIn;
}
