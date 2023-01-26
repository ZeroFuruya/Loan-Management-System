package e2p.icotp.layout.modal;

import org.kordamp.ikonli.javafx.FontIcon;

import e2p.icotp.App;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class PaymentController {
    @FXML
    private TextField lastname;

    @FXML
    private TextField loaner_id;

    @FXML
    private TextField loan_id;

    @FXML
    private DatePicker payment_date;

    @FXML
    private TextField payment_amount;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    // Icon
    @FXML
    private FontIcon lastname_icon;
    @FXML
    private FontIcon loanerID_icon;
    @FXML
    private FontIcon loanId_icon;
    @FXML
    private FontIcon paymentDate_icon;
    @FXML
    private FontIcon paymentAmount_icon;

    // TOOLTIPS
    @FXML
    private Tooltip lastnameTT;
    @FXML
    private Tooltip loanerIdTT;
    @FXML
    private Tooltip loanIdTT;
    @FXML
    private Tooltip paymentDateTT;
    @FXML
    private Tooltip paymentAmountTT;


    private App app;

    @FXML
    private void handle_cancel(){
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save(){
        
    }

    public void load(App app){
        this.app = app;
        load_bindings();
    }

    private void load_bindings(){

    }
}
