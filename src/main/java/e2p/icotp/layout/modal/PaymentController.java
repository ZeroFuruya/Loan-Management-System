package e2p.icotp.layout.modal;

import e2p.icotp.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    private App app;

    public void load(App app){
        this.app = app;
    }
}
