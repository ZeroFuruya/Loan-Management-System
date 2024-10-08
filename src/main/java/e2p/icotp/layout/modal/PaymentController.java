package e2p.icotp.layout.modal;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.Loan;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.model.Enums.PaymentFrequency;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanDAO;
import e2p.icotp.service.server.dao.PaymentDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.util.custom.ValidateTextField;
import e2p.icotp.util.custom.date.DateUtil;
import e2p.icotp.util.custom.formatters.IDTextFieldFormatter;
import e2p.icotp.util.custom.pdf.InvoiceGenerator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
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
    private Label payment_date_label;
    @FXML
    private Label amount_to_pay;
    @FXML
    private Label change_amount;

    @FXML
    private TextField payment_amount;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    // Icon
    @FXML
    private HBox paymentAmount_icon;

    // TOOLTIPS
    @FXML
    private Label paymentDateTT;
    @FXML
    private Label paymentAmountTT;
    @FXML
    private Label date_paid;

    private App app;
    private Loan loan;
    private Loan og_loan;
    BooleanProperty isEdit = new SimpleBooleanProperty(false);
    private MainController mc;
    private Loaner loaner;
    private Payment payment;
    private Payment og_payment;

    TextFormatter<Long> payment_formatter;

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() throws SQLException {
        modify_payment_listener();

        LocalDate next_due_date = loan.getNextDueDate();

        loan.setPaid(payment.getPayment_amount() + loan.getPaid());
        if (loan.getPaymentFrequencyProperty().get().toLowerCase().contains(PaymentFrequency.MONTHLY.toLowerCase())) {
            next_due_date = next_due_date.plusMonths(1);
            loan.setNextDueDate(next_due_date);
        }
        if (loan.getPaymentFrequencyProperty().get().toLowerCase().contains(PaymentFrequency.DAILY.toLowerCase())) {
            next_due_date = next_due_date.plusDays(1);
            loan.setNextDueDate(next_due_date);
        }
        if (loan.getPaymentFrequencyProperty().get().toLowerCase().contains(PaymentFrequency.YEARLY.toLowerCase())) {
            next_due_date = next_due_date.plusYears(1);
            loan.setNextDueDate(next_due_date);
        }

        System.out.println(loan.getBalance());
        System.out.println(payment.getPayment_amount());
        loan.setBalance(loan.getBalance() - payment.getPayment_amount());
        System.out.println(loan.getBalance());

        if (isEdit.get()) {
            PaymentDAO.update(payment);
            app.paymentMasterlist().remove(og_payment);
            app.paymentMasterlist().add(payment);
        } else {
            PaymentDAO.insert(payment);
            app.paymentMasterlist().add(payment);
        }

        LoanDAO.update(loan);
        app.loanMasterList().remove(og_loan);
        app.loanMasterList().add(loan);

        notify_changes();
        mc.load_loan_table();
        mc.refresh_loan_list();
        mc.selectLoan();
        ModalLoader.modal_close(app);

        try {
            InvoiceGenerator.generate_invoice(app, payment);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void load(App app, Loan loan, boolean isEdit, MainController mc, Loaner loaner, Payment payment)
            throws IOException {
        this.app = app;
        this.loan = loan;
        this.og_loan = loan;
        this.isEdit.set(isEdit);
        this.mc = mc;
        this.loaner = loaner;
        this.payment = payment;
        this.og_payment = payment;

        payment_formatter = new IDTextFieldFormatter();

        payment_amount.setTextFormatter(payment_formatter);

        load_bindings();
        init_listeners();
        if (isEdit) {
            loan.setPaid(loan.getPaid() - payment.getPayment_amount());
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
        paymentAmount_icon.setVisible(false);
        DoubleProperty payment_double = new SimpleDoubleProperty(0.0f);
        BooleanBinding paymentIsZero = Bindings.createBooleanBinding(() -> {
            if (!payment_amount.textProperty().get().isEmpty() || !payment_amount.textProperty().get().isBlank()) {
                payment_double.set(Double.parseDouble(payment_amount.textProperty().get()));
            }
            return payment_double.get() <= 0 ? true : false;
        }, payment_amount.textProperty());

        save.disableProperty()
                .bind(paymentAmount_icon.visibleProperty().or(paymentAmount_icon.visibleProperty()).or(paymentIsZero));
    }

    private void init_insert_listeners() {
        date_paid.setText(DateUtil.localizeDate(payment.getDatePaymentProperty().get()));
        payment_id.textProperty().set(payment.getPayment_id() + "");
        payment_date.valueProperty().set(payment.getPaymentDate());
        payment_date_label.textProperty().set(DateUtil.localizeDate(payment.getPaymentDate()));
        // payment_amount.textProperty().set(payment.getPayment_amount() + "");
        amount_to_pay.textProperty().set("$" + payment.getPayment_amount() + "");

        payment.getDatePaymentProperty().set(payment.getDatePaymentProperty().get());
    }

    private void init_add_listeners() {
        generate_id();
        date_paid.setText(DateUtil.localizeDate(LocalDate.now()));
        payment_date.valueProperty().set(loan.getNextDueDate());
        payment_date_label.textProperty().set(DateUtil.localizeDate(loan.getNextDueDate()));
        // payment_amount.textProperty().set(mc.getTotalDueAmount());
        amount_to_pay.textProperty().set("$" + mc.getTotalDueAmount() + "");
        DoubleProperty payment_amount_int = new SimpleDoubleProperty(0);
        DoubleProperty change_amount_int = new SimpleDoubleProperty(0);

        payment_amount.textProperty().addListener((o, ov, nv) -> {
            if (!nv.isEmpty() || !nv.isBlank()) {
                payment_amount_int.set(Double.parseDouble(nv));

                change_amount_int.set(payment_amount_int.get() - loan.getNextPayment());

            }
        });
        change_amount.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("$%.2f", change_amount_int.get());
        }, change_amount_int));

        payment.getDatePaymentProperty().set(LocalDate.now());
    }

    // TEXTFIELD VALIDATORS
    private static String regex = "-?(([1-9][0-9]{0,15})|0)?(\\.[0-9]{0,2})?";
    Pattern pattern = Pattern.compile(regex);
    public final int DOT = 46;
    public int dot = 46;

    @FXML
    private void _valid_input(KeyEvent k) {
        check_input(k);
    }

    @FXML
    void init_validation(KeyEvent k) {
        ValidateTextField validator = new ValidateTextField();
        validator.validateDigit(payment_amount, k, dot);

        if (payment_amount.textProperty().get().toLowerCase().contains(".")) {
            dot = ValidateTextField.NOT_DOT;
        } else {
            dot = DOT;
        }
    }

    private void check_input(KeyEvent k) {
        if (payment_amount.textProperty().get().isEmpty()) {
            paymentAmount_icon.visibleProperty().set(true);
            paymentAmountTT.textProperty().set("Field must not be Empty");
            return;
        } else {
            paymentAmount_icon.visibleProperty().set(false);
        }

        ValidateTextField validator = new ValidateTextField();
        validator.validateDigit(payment_amount, k, dot);

        if (payment_amount.textProperty().get().toLowerCase().contains(".")) {
            dot = ValidateTextField.NOT_DOT;
        } else {
            dot = DOT;
        }

        if (Double.parseDouble(payment_amount.getText()) < Double.parseDouble(mc.getTotalDueAmount())) {
            paymentAmount_icon.visibleProperty().set(true);
            paymentAmountTT.textProperty().set("Payment should be full");
            return;
        } else {
            paymentAmount_icon.visibleProperty().set(false);
        }

        // if (Double.parseDouble(payment_amount.getText()) > loan.getBalance()) {
        // paymentAmount_icon.visibleProperty().set(true);
        // paymentAmountTT.textProperty().set("Payment should not surpass balance");
        // return;
        // }

        if (!pattern.matcher(payment_amount.getText()).matches()) {
            paymentAmount_icon.visibleProperty().set(true);
            paymentAmountTT.textProperty().set("Invalid Input");
        } else {
            paymentAmount_icon.visibleProperty().set(false);
        }
    }

    private void modify_payment_listener() {
        payment.setLoaner_id(loan.getLoaner_id());
        payment.setLoan_id(loan);
        payment.setPayment_id(Long.parseLong(payment_id.textProperty().get()));
        payment.setPayment_date(payment_date.valueProperty().get());
        payment.setPayment_amount(loan.getNextPayment());
    }

    long final_num = 0;

    private void generate_id() {
        String temp_val;
        String string_val = "";
        string_val = RandomIDGenerator.getRandomNumber() + "";
        for (int i = 0; i < 7; i++) {
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
        app.loanMasterList().setAll(LoanDAO.getMasterlist());
    }
}
