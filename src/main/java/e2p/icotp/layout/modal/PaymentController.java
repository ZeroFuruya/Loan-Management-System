package e2p.icotp.layout.modal;

import e2p.icotp.App;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private Label lastname_icon;
    @FXML
    private Label loanerID_icon;
    @FXML
    private Label loanId_icon;
    @FXML
    private Label paymentDate_icon;
    @FXML
    private Label paymentAmount_icon;

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
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() {

    }

    public void load(App app) {
        this.app = app;
        load_bindings();
    }

    private void load_bindings() {
        lastname_icon.visibleProperty().bind(lastname.textProperty().isEmpty());
        loanerID_icon.visibleProperty().bind(loaner_id.textProperty().isEmpty());
        loanId_icon.visibleProperty().bind(loan_id.textProperty().isEmpty());
        paymentDate_icon.visibleProperty().bind(payment_date.promptTextProperty().isEmpty());
        paymentAmount_icon.visibleProperty().bind(payment_amount.textProperty().isEmpty());

        save.disableProperty().bind(
                lastname_icon.visibleProperty().or(loanerID_icon.visibleProperty()).or(loanId_icon.visibleProperty())
                        .or(paymentAmount_icon.visibleProperty()).or(paymentAmount_icon.visibleProperty()));
    }
}
