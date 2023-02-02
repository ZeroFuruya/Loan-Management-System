package e2p.icotp.layout.accounts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Login {
    StringProperty username;
    StringProperty password;

    public Login(){
        this("","");
    }

    public Login(String username, String password){
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public Login(Login login){
        this(login.getUsername(), login.getPassword());
    }


    // Setters
    public void setUsername(String username){
        this.username.set(username);
    }
    public void setPassword(String password){
        this.password.set(password);
    }

    // Getters
    public String getUsername(){
        return this.username.get();
    }
    public String getPassword(){
        return this.password.get();
    }

    // Properties
    public StringProperty getUsernameProperty(){
        return this.username;
    }
    public StringProperty getPasswordProperty(){
        return this.password;
    }
}
