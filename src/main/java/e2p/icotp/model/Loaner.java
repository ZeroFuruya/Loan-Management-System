package e2p.icotp.model;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loaner {
    private LongProperty loaner_id;
    private StringProperty name;
    private StringProperty address;
    private LongProperty phone;
    private ObjectProperty<LocalDate> birthdate;
    private LongProperty social_security;

    public Loaner(long loaner_id, String name, String address, long phone,
            LocalDate birthdate, long social_security) {
        this.loaner_id = new SimpleLongProperty(loaner_id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleLongProperty(phone);
        this.birthdate = new SimpleObjectProperty<>(birthdate);
        this.social_security = new SimpleLongProperty(social_security);
    }

    // SETTERS
    public void setLoaner_id(long loaner_id) {
        this.loaner_id.set(loaner_id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setPhone(long phone) {
        this.phone.set(phone);
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate.set(birthdate);
    }

    public void setSocial_security(long social_security) {
        this.social_security.set(social_security);
    }

    // GETTERS
    public long getLoaner_id() {
        return this.loaner_id.get();
    }

    public String getName() {
        return this.name.get();
    }

    public String getAddress() {
        return this.address.get();
    }

    public long getPhone() {
        return this.phone.get();
    }

    public LocalDate getBirthdate() {
        return this.birthdate.get();
    }

    public long getSocial_security() {
        return this.social_security.get();
    }

    // PROPERTY GETTERS
    public LongProperty getLoaner_idProperty() {
        return loaner_id;
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty getAddressProperty() {
        return address;
    }

    public LongProperty getPhoneProperty() {
        return phone;
    }

    public ObjectProperty<LocalDate> getBirthdateProperty() {
        return birthdate;
    }

    public LongProperty getSocial_securityProperty() {
        return social_security;
    }

}
