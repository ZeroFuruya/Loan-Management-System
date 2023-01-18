package e2p.icotp.model;


import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loaner {
    private IntegerProperty loaner_id;
    private StringProperty name;
    private StringProperty address;
    private IntegerProperty phone;
    private ObjectProperty<LocalDate> birthdate;
    private IntegerProperty social_security;

    

    public Loaner(int loaner_id, String name, String address, int phone,
            LocalDate birthdate, int social_security) {
        this.loaner_id = new SimpleIntegerProperty(loaner_id) ;
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address) ;
        this.phone = new SimpleIntegerProperty(phone) ;
        this.birthdate = new SimpleObjectProperty<LocalDate>(birthdate) ;
        this.social_security = new SimpleIntegerProperty(social_security) ;
    }

    // SETTERS

    public void setLoaner_id(IntegerProperty loaner_id) {
        this.loaner_id = loaner_id;
    }
    public void setName(StringProperty name) {
        this.name = name;
    }

    public void setAddress(StringProperty address) {
        this.address = address;
    }
    public void setPhone(IntegerProperty phone) {
        this.phone = phone;
    }
    public void setBirthdate(ObjectProperty<LocalDate> birthdate) {
        this.birthdate = birthdate;
    }

    public void setSocial_security(IntegerProperty social_security) {
        this.social_security = social_security;
    }

    // GETTERS

    public int getLoaner_id(){
        return this.loaner_id.get();
    }
    public String getName(){
        return this.name.get();
    }
    public String getAddress(){
        return this.address.get();
    }
    public int getPhone(){
        return this.phone.get();
    }
    public LocalDate getBirthdate(){
        return this.birthdate.get();
    }

    // PROPERTY GETTERS

    public IntegerProperty getLoaner_id_Property() {
        return loaner_id;
    }
    

    public StringProperty getNameProperty() {
        return name;
    }
    
    public StringProperty getAddressProperty() {
        return address;
    }
   
    public IntegerProperty getPhoneProperty() {
        return phone;
    }
   
    public ObjectProperty<LocalDate> getBirthdateProperty() {
        return birthdate;
    }
    
    public IntegerProperty getSocial_securityProperty() {
        return social_security;
    }
    
   
}
