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

    // GETTERS

    // PROPERTY GETTERS
    public IntegerProperty getLoaner_id() {
        return loaner_id;
    }
    public void setLoaner_id(IntegerProperty loaner_id) {
        this.loaner_id = loaner_id;
    }

    public StringProperty getName() {
        return name;
    }
    public void setName(StringProperty name) {
        this.name = name;
    }
    public StringProperty getAddress() {
        return address;
    }
    public void setAddress(StringProperty address) {
        this.address = address;
    }
    public StringProperty getPhone() {
        return phone;
    }
    public void setPhone(StringProperty phone) {
        this.phone = phone;
    }
    public ObjectProperty<LocalDate> getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(ObjectProperty<LocalDate> birthdate) {
        this.birthdate = birthdate;
    }
    public IntegerProperty getSocial_security() {
        return social_security;
    }
    public void setSocial_security(IntegerProperty social_security) {
        this.social_security = social_security;
    }
   
}
