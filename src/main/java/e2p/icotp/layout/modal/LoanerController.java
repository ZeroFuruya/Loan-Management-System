package e2p.icotp.layout.modal;

import e2p.icotp.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private Button save;
    @FXML
    private Button cancel;

    private App app;

    public void load(App app){
        this.app = app;
    }
}
