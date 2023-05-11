package e2p.icotp.layout.accounts;

import javax.crypto.SecretKey;

import e2p.icotp.App;
import e2p.icotp.model.Enums.SecurityQuestions;
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
import javafx.scene.layout.VBox;

public class ForgotPasswordController {
    @FXML
    private ComboBox<String> secQuesCBox;
    @FXML
    private TextField secAnsTf;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private PasswordField confirmPassPF;

    @FXML
    private Label emailAccount_label;

    @FXML
    private Label passwordNoMatchErr;
    @FXML
    private Label incorrectQuestionErr;
    @FXML
    private Label incorrectAnswerErr;

    @FXML
    private Button changePass_button;
    @FXML
    private Button answer_confirm_btn;

    @FXML
    private VBox field_container_vbox;

    private App app;
    private Account admin;
    private Account admin_copy;

    private IntegerProperty securityQuestionVal = new SimpleIntegerProperty(0);
    private BooleanProperty secQuesIsWrong = new SimpleBooleanProperty(true);
    private BooleanProperty secAnsIsWrong = new SimpleBooleanProperty(true);
    private BooleanProperty secAnsIsEmpty = new SimpleBooleanProperty(true);

    // String ver_code = "";

    // BooleanProperty isValidCode = new SimpleBooleanProperty(false);

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
    void handle_confirm_answer() {
        String decryptedPass = Encrypt.decrypt(admin.getSecurityAnswer(), admin.getPassKey());
        System.out.println(admin.getSecurityAnswer() + " : " + admin.getPassKey());
        System.out.println(decryptedPass);

        if (secAnsTf.textProperty().get().isEmpty() || secAnsTf.textProperty().get().isBlank()) {
            secAnsIsEmpty.set(true);
            return;
        } else {
            secAnsIsEmpty.set(false);
        }

        if (secAnsTf.getText().equals(decryptedPass)) {
            secAnsIsWrong.set(false);
        } else {
            secAnsIsWrong.set(true);
        }
    }

    @FXML
    void handle_change_pass() throws Exception {
        String pass = passwordPF.getText();
        String encryptedPass = Encrypt.encrypt(pass, admin.getPassKey());
        // System.out.println(encryptedPass);

        admin.setPassword(encryptedPass);
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
        this.admin = app.getAdminProperty();
        this.admin_copy = new Account(admin);

        // System.out.println(admin.getUsername());
        // System.out.println(admin.getAccountId());
        // System.out.println(admin.getSecurityQuestion());
        // System.out.println(admin.getPassKey());
        // System.out.println(admin.getPassword());

        init_bindings();
        init_cboxes();
    }

    private void init_bindings() {

        // emailAccount_label.textProperty().bind(admin.getSecurityQuestionProperty());

        BooleanBinding isPassEmpty = passwordPF.textProperty().isEmpty();
        BooleanBinding isPassNotMatches = passwordPF.textProperty().isNotEqualTo(confirmPassPF.textProperty());

        incorrectQuestionErr.visibleProperty().bind(secQuesIsWrong);
        incorrectAnswerErr.visibleProperty().bind(secAnsIsEmpty.or(secAnsIsWrong));

        passwordNoMatchErr.textProperty()
                .bind(Bindings.when(passwordPF.textProperty().isEmpty()).then("Field must not be empty")
                        .otherwise(Bindings.when(passwordPF.textProperty().isEqualTo(confirmPassPF.textProperty()))
                                .then("").otherwise("Password doesn't match")));

        changePass_button.disableProperty()
                .bind(isPassEmpty.or(isPassNotMatches).or(incorrectAnswerErr.visibleProperty()));

    }

    private void init_cboxes() {
        secQuesCBox.getItems().add(SecurityQuestions.SQ_1);
        secQuesCBox.getItems().add(SecurityQuestions.SQ_2);
        secQuesCBox.getItems().add(SecurityQuestions.SQ_3);
        secQuesCBox.getItems().add(SecurityQuestions.SQ_4);
        secQuesCBox.getItems().add(SecurityQuestions.SQ_5);
        secQuesCBox.getItems().add(SecurityQuestions.SQ_6);
        secQuesCBox.getItems().add(SecurityQuestions.SQ_7);

        secQuesCBox.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (!nv.isEmpty() || !nv.isBlank()) {
                switch (nv) {
                    case SecurityQuestions.SQ_1:
                        securityQuestionVal.set(1);
                        break;
                    case SecurityQuestions.SQ_2:
                        securityQuestionVal.set(2);
                        break;
                    case SecurityQuestions.SQ_3:
                        securityQuestionVal.set(3);
                        break;
                    case SecurityQuestions.SQ_4:
                        securityQuestionVal.set(4);
                        break;
                    case SecurityQuestions.SQ_5:
                        securityQuestionVal.set(5);
                        break;
                    case SecurityQuestions.SQ_6:
                        securityQuestionVal.set(6);
                        break;
                    case SecurityQuestions.SQ_7:
                        securityQuestionVal.set(7);
                        break;
                    default:
                        securityQuestionVal.set(0);
                        break;
                }
                // System.out.println(securityQuestionVal.get());
                // System.out.println(nv);
                if (securityQuestionVal.get() != admin.getSecurityQuestion()) {
                    secQuesIsWrong.set(true);
                } else {
                    secQuesIsWrong.set(false);
                }
            }
        });
    }
}
