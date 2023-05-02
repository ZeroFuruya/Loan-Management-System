package e2p.icotp.layout.accounts;

import javax.crypto.SecretKey;

import e2p.icotp.App;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.AccountDAO;
import e2p.icotp.util.custom.cipher.Encrypt;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminController {
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField secQuesAnswer;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private PasswordField confirmPassPF;

    @FXML
    private ComboBox<String> securityQuestionCBox;

    private IntegerProperty securityQuestionVal = new SimpleIntegerProperty(0);

    @FXML
    private Label usernameErr;
    @FXML
    private Label passwordNoMatchErr;
    @FXML
    private Label secQuesErr;
    @FXML
    private Label secAnsErr;

    @FXML
    private Button signUpButton;
    @FXML
    private Button confirmAnswerBtn;

    private BooleanProperty secAnsIsEmpty = new SimpleBooleanProperty(true);

    private App app;
    private Account admin;

    // s TODO ADD SECURITY CODE

    // String ver_code = "";

    // BooleanProperty emailInputted = new SimpleBooleanProperty(false);

    // @FXML
    // private void handle_send_code() throws GeneralSecurityException, IOException,
    // MessagingException {
    // secQuesErr.setVisible(false);
    // incorrecrCodeErr.setVisible(true);
    // String temp_val;
    // ver_code = "";
    // ver_code = RandomIDGenerator.getRandomNumber() + "";
    // for (int i = 0; i < 4; i++) {
    // int initial_num = RandomIDGenerator.getRandomNumber();
    // temp_val = Integer.toString(initial_num);
    // ver_code = temp_val + ver_code;
    // }
    // GMailer mail_sender = new GMailer();

    // String emailAddress = emailTF.getText();

    // String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    // Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    // Matcher matcher = pattern.matcher(emailAddress);

    // StringBuilder code_message = new StringBuilder(
    // "You are trying to make an Admin account for Zephyr Loan Management
    // System.");
    // code_message.append("\n");
    // code_message.append("\n");
    // code_message.append("Verification Code: ");
    // code_message.append(ver_code);

    // if (!matcher.matches()) {
    // secQuesErr.setVisible(true);
    // return;
    // }
    // emailInputted.set(true);
    // mail_sender.sendMail(emailAddress, "Loan Management Verification Code",
    // code_message.toString());

    // secQuesAnswer.textProperty().addListener((o, ov, nv) -> {
    // if (nv.isEmpty() || nv.isBlank()) {
    // incorrecrCodeErr.setVisible(true);
    // }

    // if (!nv.equals(ver_code)) {
    // incorrecrCodeErr.setVisible(true);
    // return;
    // }

    // incorrecrCodeErr.setVisible(false);
    // });
    // }

    @FXML
    void handle_confirmAnswer() {
        String secAns = secQuesAnswer.getText();

        if (secAns.isBlank() || secAns.isEmpty()) {
            secAnsIsEmpty.set(true);
        } else {
            secAnsIsEmpty.set(false);
        }
    }

    @FXML
    void handle_signUp() throws Exception {
        SecretKey key = Encrypt.generateKey();
        String keyString = Encrypt.convertSecretKeyToString(key);
        Encrypt.prepareSecreteKey(keyString);
        String pass = passwordPF.getText();
        String encryptedPass = Encrypt.encrypt(pass, keyString);
        String encryptedAnswer = Encrypt.encrypt(secQuesAnswer.getText(), keyString);
        System.out.println(encryptedPass);
        System.out.println(encryptedAnswer);

        admin.setAccountId(1);
        admin.setUsername(usernameTF.getText());
        admin.setPassword(encryptedPass);
        admin.setPassKey(keyString);
        admin.setSecurityQuestion(securityQuestionVal.get());
        admin.setSecurityAnswer(encryptedAnswer);
        AccountDAO.insert(admin);
        app.accountsMasterlist().add(admin);

        ModalLoader.modal_close(app);
        LogInLoader.load_log_in(app);
    }

    public void load(App app) {
        this.app = app;
        admin = new Account();

        init_bindings();
        init_cboxes();
    }

    private void init_cboxes() {
        // TODO make a whole separate object for this called SecurityQuestion
        securityQuestionCBox.getItems().add("In what city or town did your mother and father meet?");
        securityQuestionCBox.getItems().add("What was the first exam you failed?");
        securityQuestionCBox.getItems().add("What was the name of your first stuffed animal?");
        securityQuestionCBox.getItems().add("What is the middle name of your youngest child?");
        securityQuestionCBox.getItems().add("Where were you when you had your first kiss?");
        securityQuestionCBox.getItems().add("What was the name of the boy or the girl you first kissed?");
        securityQuestionCBox.getItems().add("What is the name of the first animated movie you watched?");

        securityQuestionCBox.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (!nv.isEmpty() || !nv.isBlank()) {
                switch (nv) {
                    case "In what city or town did your mother and father meet?":
                        securityQuestionVal.set(1);
                        break;
                    case "What was the first exam you failed?":
                        securityQuestionVal.set(2);
                        break;
                    case "What was the name of your first stuffed animal?":
                        securityQuestionVal.set(3);
                        break;
                    case "What is the middle name of your youngest child?":
                        securityQuestionVal.set(4);
                        break;
                    case "Where were you when you had your first kiss?":
                        securityQuestionVal.set(5);
                        break;
                    case "What was the name of the boy or the girl you first kissed?":
                        securityQuestionVal.set(6);
                        break;
                    case "What is the name of the first animated movie you watched?":
                        securityQuestionVal.set(7);
                        break;
                    default:
                        securityQuestionVal.set(0);
                        break;
                }
                System.out.println(securityQuestionVal.get());
                System.out.println(nv);
            }
        });
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

        secQuesErr.visibleProperty().bind(securityQuestionVal.isEqualTo(0));
        secAnsErr.visibleProperty().bind(secAnsIsEmpty);

        passwordNoMatchErr.textProperty()
                .bind(Bindings.when(passwordPF.textProperty().isEmpty()).then("Field must not be empty")
                        .otherwise(Bindings.when(passwordPF.textProperty().isEqualTo(confirmPassPF.textProperty()))
                                .then("").otherwise("Password doesn't match")));

        signUpButton.disableProperty()
                .bind(usernameErr.visibleProperty()
                        .or(isPassEmpty.or(isPassNotMatches).or(secQuesErr.visibleProperty())
                                .or(secAnsErr.visibleProperty())));

        // secQuesAnswer.visibleProperty().bind(emailInputted);
    }
}
