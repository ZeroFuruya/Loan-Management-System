package e2p.icotp.layout.modal;

import org.kordamp.ikonli.javafx.FontIcon;

import e2p.icotp.App;
import e2p.icotp.model.Loaner;
import e2p.icotp.service.loader.ModalLoader;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class LoanerController {
    @FXML
    private TextField lastname;

    @FXML
    private TextField firstname;

    @FXML
    private TextField middleName;

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
    private FontIcon lastname_icon;
    @FXML
    private FontIcon firstname_icon;
    @FXML
    private FontIcon middlename_icon;
    @FXML
    private FontIcon birthday_icon;
    @FXML
    private FontIcon address_icon;
    @FXML
    private FontIcon contact_icon;
    @FXML
    private FontIcon email_icon;
    @FXML
    private FontIcon loanerId_icon;
    @FXML
    private FontIcon socialSecurity_icon;
    @FXML
    private FontIcon civilStatus_icon;
    @FXML
    private FontIcon citizenship_icon;
    @FXML
    private FontIcon placeOfBirth_icon;

    // ToolTip
    @FXML
    private Tooltip lastnameTT;
    @FXML
    private Tooltip firstnameTT;
    @FXML 
    private Tooltip middlenameTT;
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
    private Button save;
    @FXML
    private Button cancel;

    private App app;
    private Loaner loaner;

    @FXML
    private void handle_cancel(){
        ModalLoader.modal_close(app);
    }

    public void load(App app){
        this.app = app;
    }



}
