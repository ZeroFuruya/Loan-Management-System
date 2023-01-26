package e2p.icotp.model;

import java.time.LocalDate;

import e2p.icotp.util.custom.DateUtil;
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

    // STRINGS
    private StringProperty loaner_id_string;
    private StringProperty phone_string;
    private StringProperty birthdate_string;
    private StringProperty social_string;
    private StringProperty citizenship_string;
    private StringProperty placeOfBirth_string;
    private StringProperty civilStatus_string;

    public Loaner(long loaner_id, String name, String address, long phone,
            LocalDate birthdate, long social_security, String citizenship_string, String placeOfBirth_string, String civilStatus_string) {
        this.loaner_id = new SimpleLongProperty(loaner_id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleLongProperty(phone);
        this.birthdate = new SimpleObjectProperty<>(birthdate);
        this.social_security = new SimpleLongProperty(social_security);

        // STRINGS
        this.loaner_id_string = new SimpleStringProperty(loaner_id + "");
        this.phone_string = new SimpleStringProperty(phone + "");
        this.birthdate_string = new SimpleStringProperty(DateUtil.localizeDate(birthdate));
        this.social_string = new SimpleStringProperty(social_security + "");
        this.citizenship_string = new SimpleStringProperty(citizenship_string + "");
        this.civilStatus_string = new SimpleStringProperty(civilStatus_string + "");
        
    }

    public Loaner() {
        this(0, "", "", 0, LocalDate.now(), 0, "","","");
    }

    // STRING GETTERS
    public StringProperty getLoanerIdString() {
        return loaner_id_string;
    }

    public StringProperty getPhoneString() {
        return phone_string;
    }

    public StringProperty getBirthdateString() {
        return birthdate_string;
    }

    public StringProperty getSocialString() {
        return social_string;
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

    public void setCitizenship(String citizenship){
        this.citizenship_string.set(citizenship);
    }

    public void setPlaceOfBirth(String placeOfBirth){
        this.placeOfBirth_string.set(placeOfBirth);
    }

    public void setCivilStatus(String civilStatus){
        this.civilStatus_string.set(civilStatus);
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

    public String getCitizenship(){
        return this.citizenship_string.get();
    }

    public String getPlaceOfBirth(){
        return this.placeOfBirth_string.get();
    }

    public String getCivilStatus(){
        return this.civilStatus_string.get();
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
    
    public StringProperty getCitizenshipProperty(){
        return citizenship_string;
    }

    public StringProperty getPlaceOfBirthProperty(){
        return placeOfBirth_string;
    }

    public StringProperty getCivilStatusProperty(){
        return civilStatus_string;
    }
    

}
