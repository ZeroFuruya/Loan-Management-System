package e2p.icotp.layout.modal;

import java.sql.SQLException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.Loaner;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanDAO;
import e2p.icotp.service.server.dao.LoanerDAO;
import e2p.icotp.service.server.dao.PaymentDAO;
import e2p.icotp.util.custom.IDTextFieldFormatter;
import e2p.icotp.util.custom.LocalizeDateConverter;
import e2p.icotp.util.custom.RandomIDGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;

public class LoanerController {
    @FXML
    private TextField name;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField address;
    @FXML
    private TextField contactNo;
    @FXML
    private TextField email;
    @FXML
    private TextField loaner_id;
    @FXML
    private TextField social_security;
    @FXML
    private TextField civil_status;
    @FXML
    private TextField citizenship;
    @FXML
    private TextField place_of_Birth;

    // Icons
    @FXML
    private Label name_icon;
    @FXML
    private Label birthday_icon;
    @FXML
    private Label address_icon;
    @FXML
    private Label contact_icon;
    @FXML
    private Label email_icon;
    @FXML
    private Label loanerId_icon;
    @FXML
    private Label socialSecurity_icon;
    @FXML
    private Label civilStatus_icon;
    @FXML
    private Label citizenship_icon;
    @FXML
    private Label placeOfBirth_icon;

    // ToolTip
    @FXML
    private Tooltip nameTT;
    @FXML
    private Tooltip birthdayTT;
    @FXML
    private Tooltip addressTT;
    @FXML
    private Tooltip contactTT;
    @FXML
    private Tooltip emailTT;
    @FXML
    private Tooltip loanerIdTT;
    @FXML
    private Tooltip socialSecurityTT;
    @FXML
    private Tooltip civilStatusTT;
    @FXML
    private Tooltip citizenshipTT;
    @FXML
    private Tooltip placeOfBirthTT;

    @FXML
    private Button upload_img;
    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private App app;
    private Loaner loaner;
    private Loaner og_loaner;

    private BooleanProperty isEdit;

    private TextFormatter<Long> phone_formatter;
    private TextFormatter<Long> social_formatter;

    private MainController mc;

    long final_num = 0;

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() throws SQLException {
        modify_loaner_listener();
        if (isEdit.get()) {
            LoanerDAO.update(loaner);
            app.loanerMasterlist().remove(og_loaner);
            app.loanerMasterlist().add(loaner);
        } else {
            LoanerDAO.insert(loaner);
            app.loanerMasterlist().add(loaner);
        }
        notify_changes();
        ModalLoader.modal_close(app);
    }

    public void load(App app, Loaner loaner, boolean isEdit, MainController mc) {
        this.app = app;
        this.loaner = loaner;
        this.og_loaner = loaner;
        this.isEdit = new SimpleBooleanProperty(isEdit);
        this.mc = mc;

        init_bindings();
        init_fields();
    }

    private void init_bindings() {

        // ERROR BINDINGS
        name_icon.visibleProperty().bind(name.textProperty().isEmpty());
        birthday_icon.visibleProperty().bind(birthday.valueProperty().isNull());
        address_icon.visibleProperty().bind(address.textProperty().isEmpty());
        contact_icon.visibleProperty().bind(contactNo.textProperty().isEmpty());
        email_icon.visibleProperty().bind(email.textProperty().isEmpty());
        loanerId_icon.visibleProperty().bind(loaner_id.textProperty().isEmpty());
        socialSecurity_icon.visibleProperty().bind(social_security.textProperty().isEmpty());
        civilStatus_icon.visibleProperty().bind(civil_status.textProperty().isEmpty());
        citizenship_icon.visibleProperty().bind(citizenship.textProperty().isEmpty());
        placeOfBirth_icon.visibleProperty().bind(place_of_Birth.textProperty().isEmpty());

        save.disableProperty()
                .bind(name_icon.visibleProperty().or(name_icon.visibleProperty())
                        .or(birthday_icon.visibleProperty()).or(address_icon.visibleProperty())
                        .or(contact_icon.visibleProperty()).or(email_icon.visibleProperty())
                        .or(loanerId_icon.visibleProperty()).or(socialSecurity_icon.visibleProperty())
                        .or(civilStatus_icon.visibleProperty()).or(citizenship_icon.visibleProperty())
                        .or(placeOfBirth_icon.visibleProperty()));

        birthday.setConverter(new LocalizeDateConverter());
        birthday.setEditable(false);

    }

    private void init_fields() {
        phone_formatter = new IDTextFieldFormatter();
        social_formatter = new IDTextFieldFormatter();

        contactNo.setTextFormatter(phone_formatter);
        social_security.setTextFormatter(social_formatter);

        if (isEdit.get()) {
            loaner_id.textProperty().set(loaner.getLoaner_id() + "");
        } else {
            generate_id();
        }

        name.textProperty().set(loaner.getName());
        birthday.valueProperty().set(loaner.getBirthdate());
        address.textProperty().set(loaner.getAddress());
        contactNo.textProperty().set(loaner.getPhone() + "");
        email.textProperty().set(loaner.getEmail());
        social_security.textProperty().set(loaner.getSocial_security() + "");
        civil_status.textProperty().set(loaner.getCivilStatus());
        citizenship.textProperty().set(loaner.getCitizenship());
        place_of_Birth.textProperty().set(loaner.getPlaceOfBirth());
    }

    private void generate_id() {
        String temp_val;
        String string_val = "";
        for (int i = 0; i < 7; i++) {
            int initial_num = RandomIDGenerator.getRandomNumber();
            temp_val = Integer.toString(initial_num);
            string_val = temp_val + string_val;
        }
        final_num = Long.parseLong(string_val);

        app.loanerMasterlist().forEach(loaner -> {
            if (loaner.getLoaner_id() == final_num) {
                generate_id();
            } else {
                loaner_id.textProperty().set(final_num + "");
            }
        });
    }

    // CUSTOMS
    private void modify_loaner_listener() {
        loaner.setName(name.textProperty().get());
        loaner.setBirthdate(birthday.getValue());
        loaner.setAddress(address.textProperty().get());
        loaner.setPhone(long_parser(contactNo.textProperty().get()));
        loaner.setEmail(email.textProperty().get());
        loaner.setLoaner_id(long_parser(loaner_id.textProperty().get()));
        loaner.setSocial_security(long_parser(social_security.textProperty().get()));
        loaner.setCivilStatus(civil_status.textProperty().get());
        loaner.setCitizenship(citizenship.textProperty().get());
        loaner.setPlaceOfBirth(place_of_Birth.textProperty().get());
    }

    private Long long_parser(String val) {
        return Long.parseLong(val);
    }

    private void notify_changes() throws SQLException {
        app.loanMasterList().setAll(LoanDAO.getMasterlist());
        app.paymentMasterlist().setAll(PaymentDAO.getMasterlist());
    }
}
