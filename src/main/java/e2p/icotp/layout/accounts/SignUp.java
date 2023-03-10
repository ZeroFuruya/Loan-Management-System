package e2p.icotp.layout.accounts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

    public class SignUp {
    StringProperty username;
    StringProperty password;
    StringProperty confirmPassword;

    public SignUp(){
        this("", "","");
    }

    public SignUp(SignUp signUp){
        this(signUp.getUsername(), signUp.getPassword(), signUp.getConfirmPassWord());
    }


    public SignUp(String username, String password, String confirmPassword){
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.confirmPassword = new SimpleStringProperty(confirmPassword);
    }

  
    

    

    // Setters
    public void setUsername(String username){
        this.username.set(username);
    }
    public void setPassword(String password){
        this.password.set(password);
    }
    public void setConfirmPassword(String confirmPassword){
        this.password.set(confirmPassword);
    }

    // Getters
    public String getUsername(){
        return this.username.get();
    }
    public String getPassword(){
        return this.password.get();
    }
    public String getConfirmPassWord(){
        return this.password.get();
    }

    // Properties
    public StringProperty getUsernameProperty(){
        return this.username;
    }
    public StringProperty getPasswordProperty(){
        return this.password;
    }
    public StringProperty getConfirmPasswordProperty(){
        return this.password;
    }
}
