package e2p.icotp.layout.accounts;

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
    private Button logIn;

    @FXML
    private Hyperlink signUp;
}
