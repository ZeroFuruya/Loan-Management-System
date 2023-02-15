package e2p.icotp.layout.modal;

import java.sql.SQLException;
import java.time.LocalDate;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.Loan;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.PaymentDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.util.custom.formatters.DoubleTextFieldFormatter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class PaymentController {
    @FXML
    private TextField loaner_id;

    @FXML

    private TextField loan_id;
    @FXML
    private TextField payment_id;

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
    private HBox paymentDate_icon;
    @FXML
    private HBox paymentAmount_icon;

    // TOOLTIPS
    @FXML
    private Tooltip paymentDateTT;
    @FXML
    private Tooltip paymentAmountTT;

    private App app;
    private Loan loan;
    BooleanProperty isEdit = new SimpleBooleanProperty(false);
    private MainController mc;
    private Loaner loaner;
    private Payment payment;
    private Payment og_payment;

    TextFormatter<Number> payment_formatter;

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() throws SQLException {
        modify_payment_listener();
        if (isEdit.get()) {
            PaymentDAO.update(payment);
            app.paymentMasterlist().remove(og_payment);
            app.paymentMasterlist().add(payment);
        } else {
            PaymentDAO.insert(payment);
            app.paymentMasterlist().add(payment);
        }
        notify_changes();
        mc.load_loan_table();
        mc.refresh_loan_list();
        ModalLoader.modal_close(app);
    }

    public void load(App app, Loan loan, boolean isEdit, MainController mc, Loaner loaner, Payment payment) {
        this.app = app;
        this.loan = loan;
        this.isEdit.set(isEdit);
        this.mc = mc;
        this.loaner = loaner;
        this.payment = payment;
        this.og_payment = payment;

        payment_formatter = new DoubleTextFieldFormatter();
        payment_amount.setTextFormatter(payment_formatter);

        load_bindings();
        init_listeners();
        if (isEdit) {
            init_insert_listeners();
        } else {
            init_add_listeners();
        }
    }

    private void init_listeners() {
        loaner_id.textProperty().set(loan.getLoaner_id().getLoaner_id() + "");
        loan_id.textProperty().set(loan.getLoanID_Property().get() + "");
    }

    private void load_bindings() {
        paymentDate_icon.visibleProperty().bind(payment_date.promptTextProperty().isEmpty());
        paymentAmount_icon.visibleProperty().bind(payment_amount.textProperty().isEmpty());

        save.disableProperty().bind(paymentAmount_icon.visibleProperty().or(paymentAmount_icon.visibleProperty()));
    }

    private void init_insert_listeners() {
        payment_id.textProperty().set(payment.getPayment_id() + "");
        payment_date.valueProperty().set(payment.getPaymentDate());
        payment_amount.textProperty().set(payment.getPayment_amount() + "");
    }

    private void init_add_listeners() {
        generate_id();
        payment_date.valueProperty().set(LocalDate.now());
        payment_amount.textProperty().set(mc.getTotalDueAmount()); // TODO FIX FORMAT
    }

    private void modify_payment_listener() {
        payment.setLoaner_id(loan.getLoaner_id());
        payment.setLoan_id(loan);
        payment.setPayment_id(Long.parseLong(payment_id.textProperty().get()));
        payment.setPayment_date(payment_date.valueProperty().get());
        payment.setPayment_amount(Double.parseDouble(payment_amount.textProperty().get()));
    }

    long final_num = 0;

    private void generate_id() {
        String temp_val;
        String string_val = "";
        for (int i = 0; i < 10; i++) {
            int initial_num = RandomIDGenerator.getRandomNumber();
            temp_val = Integer.toString(initial_num);
            string_val = temp_val + string_val;
        }
        final_num = Long.parseLong(string_val);

        app.loanPlanMasterlist().forEach(loan_plan -> {
            if (loan_plan.getId().get() == final_num) {
                generate_id();
            } else {
                payment_id.textProperty().set(final_num + "");
            }
        });
    }

    private void notify_changes() throws SQLException {
        app.paymentMasterlist().setAll(PaymentDAO.getMasterlist());
    }
}
