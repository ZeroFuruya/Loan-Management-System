package e2p.icotp.model;


import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class Loaner {
    private IntegerProperty loaner_id;
    private StringProperty name;
    private StringProperty address;
    private StringProperty phone;
    private ObjectProperty<LocalDate> birthdate;
    private IntegerProperty social_security;

    

    public Loaner(IntegerProperty loaner_id, StringProperty name, StringProperty address, StringProperty phone,
            ObjectProperty<LocalDate> birthdate, IntegerProperty social_security) {
        this.loaner_id = loaner_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthdate = birthdate;
        this.social_security = social_security;
    }

    // SETTERS
    public void setLoaner_id(int loaner_id) {
        this.loaner_id.set(loaner_id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate.set(birthdate);
    }

    public void setSocial_security(int social_security) {
        this.social_security.set(social_security);
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

    public String getPhone(){
        return this.phone.get();
    }

    public LocalDate getBirthdate(){
        return this.birthdate.get();
    }

    public int getSocial_security(){
        return this.social_security.get();
    }

    // PROPERTY GETTERS
    public IntegerProperty getLoaner_idProperty() {
        return loaner_id;
    }
   
    public StringProperty getNameProperty() {
        return name;
    }
    
    public StringProperty getAddressProperty() {
        return address;
    }
   
    public StringProperty getPhoneProperty() {
        return phone;
    }
    
    public ObjectProperty<LocalDate> getBirthdateProperty() {
        return birthdate;
    }
   
    public IntegerProperty getSocial_securityProperty() {
        return social_security;
    }
    
   
}
