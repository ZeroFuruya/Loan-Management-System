package e2p.icotp.layout.modal;


import e2p.icotp.App;
import e2p.icotp.model.Enums.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class LoanController {
    @FXML
    private TextField loan_id;

    @FXML
    private TextField loaner_id;

    @FXML
    private TextField term;

    @FXML
    private DatePicker release_date;

    @FXML
    private DatePicker maturity_date;

    @FXML
    private TextField principal;

    @FXML
    private TextField interest;

    @FXML
    private TextField penalty;

    @FXML
    private TextField due;

    @FXML
    private TextField paid;

    @FXML
    private TextField balance;

    @FXML
    private ComboBox<Status> status;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private App app;


    public void load(App app){
        this.app = app;
    }
}
