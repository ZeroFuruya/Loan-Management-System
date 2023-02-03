package e2p.icotp.layout.accounts;

import java.io.File;

import e2p.icotp.App;
import e2p.icotp.service.RegistryService;
import e2p.icotp.service.XMLService;
import e2p.icotp.util.FileUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
    void handle_signUp() throws Exception {
        signUp = new SignUp(usernameTF.getText(), passwordPF.getText(), confirmPassPF.getText());
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
    }

    public void load(App app) {
        this.app = app;
        init_bindings();
    }

    private void init_bindings() {

        signUpButton.disableProperty().bind(usernameLabel.visibleProperty().or(passwordLabel.visibleProperty())
                .or(confirmPassLabel.visibleProperty()));

        BooleanBinding signUpList = Bindings.createBooleanBinding(() -> {
            return app.getSignUpList().stream()
                    .anyMatch(users -> usernameTF.textProperty().get().equals(users.getUsername()));
        }, usernameTF.textProperty());

        usernameLabel.visibleProperty().bind(usernameTF.textProperty().isEmpty().or(signUpList));
        passwordLabel.visibleProperty().bind(passwordPF.textProperty().isEmpty().or(signUpList));
        confirmPassLabel.visibleProperty().bind(confirmPassPF.textProperty().isEmpty().or(signUpList));

        usernameTT.textProperty()
                .bind(Bindings.when(signUpList).then("Account already exists. ").otherwise("Invalid Username "));
    }
}
