package e2p.icotp.layout.accounts;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private IntegerProperty account_id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty pass_key;
    private IntegerProperty security_question;
    private StringProperty security_answer;

    public Account() {
        this(0, "", "", "", 0, "");
    }

    public Account(Account account) {
        this(account.getAccountId(), account.getUsername(), account.getPassword(), account.getPassKey(),
                account.getSecurityQuestion(), account.getSecurityAnswer());
    }

    public Account(int account_id, String username, String password, String pass_key, int security_question,
            String security_answer) {
        this.account_id = new SimpleIntegerProperty(account_id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.pass_key = new SimpleStringProperty(pass_key);
        this.security_question = new SimpleIntegerProperty(security_question);
        this.security_answer = new SimpleStringProperty(security_answer);
    }

    // Setters

    public void setAccountId(int val) {
        this.account_id.set(val);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setPassKey(String val) {
        this.pass_key.set(val);
    }

    public void setSecurityQuestion(int val) {
        this.security_question.set(val);
    }

    public void setSecurityAnswer(String val) {
        this.security_answer.set(val);
    }

    // Getters
    public String getUsername() {
        return this.username.get();
    }

    public String getPassword() {
        return this.password.get();
    }

    public String getPassKey() {
        return this.pass_key.get();
    }

    public int getSecurityQuestion() {
        return this.security_question.get();
    }

    public String getSecurityAnswer() {
        return this.security_answer.get();
    }

    public int getAccountId() {
        return this.account_id.get();
    }

    // Properties
    public StringProperty getUsernameProperty() {
        return this.username;
    }

    public StringProperty getPasswordProperty() {
        return this.password;
    }

    public StringProperty getPassKeyProperty() {
        return this.pass_key;
    }

    public StringProperty getSecurityAnswerProperty() {
        return this.security_answer;
    }

    public IntegerProperty getSecurityQuestionProperty() {
        return this.security_question;
    }

    public IntegerProperty getAccountIdProperty() {
        return this.account_id;
    }
}
