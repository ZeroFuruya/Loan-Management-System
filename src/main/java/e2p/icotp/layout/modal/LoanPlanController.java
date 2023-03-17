package e2p.icotp.layout.modal;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.LoanPlan;
import e2p.icotp.model.LoanType;
import e2p.icotp.model.Enums.PaymentFrequency;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanPlanDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.util.custom.cbox.LoanTypeListCell;
import e2p.icotp.util.custom.cbox.LoanTypeStringConverter;
import e2p.icotp.util.custom.formatters.DoubleTextFieldFormatter;
import e2p.icotp.util.custom.formatters.IDTextFieldFormatter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

public class LoanPlanController {
    @FXML
    TextField plan_id_tf;
    @FXML
    TextField plan_term_tf;
    @FXML
    TextField plan_interest_tf;
    @FXML
    TextField plan_penalty_tf;

    @FXML
    ComboBox<LoanType> plan_type_cbox;
    @FXML
    ComboBox<String> payment_mode_cbox;

    @FXML
    Button plan_save_button;
    @FXML
    Button plan_cancel_button;

    @FXML
    HBox plan_type_err;
    @FXML
    HBox plan_term_err;
    @FXML
    HBox plan_interest_err;
    @FXML
    HBox plan_penalty_err;
    @FXML
    HBox payment_mode_err;

    TextFormatter<Number> interest_formatter;
    TextFormatter<Number> penalty_formatter;
    TextFormatter<Long> term_formatter;

    private App app;
    private LoanPlan loan_plan;
    private LoanPlan og_loan_plan;
    BooleanProperty isEdit;
    MainController mc;

    FilteredList<LoanType> loanTypeList;

    public void load(App app, LoanPlan loan_plan, boolean isEdit, MainController mc,
            FilteredList<LoanType> loanTypeList) {
        this.app = app;
        this.og_loan_plan = loan_plan;
        this.loan_plan = new LoanPlan(loan_plan);
        this.isEdit = new SimpleBooleanProperty(isEdit);
        this.mc = mc;
        this.loanTypeList = new FilteredList<>(loanTypeList, p -> true);

        interest_formatter = new DoubleTextFieldFormatter();
        penalty_formatter = new DoubleTextFieldFormatter();
        term_formatter = new IDTextFieldFormatter();

        plan_interest_tf.setTextFormatter(interest_formatter);
        plan_penalty_tf.setTextFormatter(penalty_formatter);
        plan_term_tf.setTextFormatter(term_formatter);

        init_plans();
    }

    private void init_plans() {
        // TODO ADD A POP UP MODAL (IF PLAN TYPES IS EMPTY)
        // TODO IF THE SELECTED LOAN PLAN IS CURRENTLY BEING USED, DISABLE MODIFY
        plan_type_cbox.disableProperty().bind(isEdit);
        plan_term_err.visibleProperty()
                .bind(plan_term_tf.textProperty().isEmpty().or(plan_term_tf.textProperty().isEqualTo("0")));
        plan_interest_err.visibleProperty()
                .bind(plan_interest_tf.textProperty().isEmpty().or(plan_interest_tf.textProperty().isEqualTo("0.0")));
        plan_penalty_err.visibleProperty()
                .bind(plan_penalty_tf.textProperty().isEmpty().or(plan_penalty_tf.textProperty().isEqualTo("0.0")));
        plan_type_err.visibleProperty().bind(plan_type_cbox.getSelectionModel().selectedItemProperty().isNull());
        payment_mode_err.visibleProperty().bind(payment_mode_cbox.getSelectionModel().selectedItemProperty().isNull());

        plan_save_button.disableProperty().bind(plan_type_err.visibleProperty().or(plan_term_err.visibleProperty())
                .or(plan_interest_err.visibleProperty()).or(plan_penalty_err.visibleProperty())
                .or(payment_mode_err.visibleProperty()));
        plan_init_cbox();
        plan_load_fields();
    }

    void plan_init_cbox() {
        plan_type_cbox.setCellFactory(cell -> new LoanTypeListCell());
        plan_type_cbox.setButtonCell(new LoanTypeListCell());
        plan_type_cbox.getItems().addAll(loanTypeList);
        plan_type_cbox.setConverter(new LoanTypeStringConverter());
        plan_type_cbox.setPromptText("Loan Type");

        payment_mode_cbox.getItems().add(PaymentFrequency.DAILY);
        payment_mode_cbox.getItems().add(PaymentFrequency.MONTHLY);
        payment_mode_cbox.getItems().add(PaymentFrequency.YEARLY);
        payment_mode_cbox.setPromptText("Payment Mode");
    }

    void plan_load_fields() {
        payment_mode_cbox.getSelectionModel().select(loan_plan.getPaymentFrequencyProperty().get());
        plan_term_tf.setText(loan_plan.getTerm().get() + "");
        plan_interest_tf.setText(loan_plan.getInterest().get() + "");
        plan_penalty_tf.setText(loan_plan.getPenalty().get() + "");
        if (isEdit.get()) {
            plan_id_tf.setText(loan_plan.getId().get() + "");
            plan_type_cbox.getSelectionModel().select(loan_plan.getType().get());
            return;
        }
        generate_id();
    }

    @FXML
    void handle_save_plan() {
        plan_modify_listener();

        if (isEdit.get()) {
            LoanPlanDAO.update(loan_plan);
            app.loanPlanMasterlist().remove(og_loan_plan);
            app.loanPlanMasterlist().add(loan_plan);
        } else {
            og_loan_plan = new LoanPlan();
            loan_plan = og_loan_plan;
            plan_modify_listener();
            LoanPlanDAO.insert(loan_plan);
            app.loanPlanMasterlist().add(loan_plan);
        }
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_cancel_plan() {
        ModalLoader.modal_close(app);
    }

    void plan_modify_listener() {
        loan_plan.getId().set(Integer.parseInt(plan_id_tf.textProperty().get()));
        loan_plan.getType().set(plan_type_cbox.getSelectionModel().getSelectedItem());
        loan_plan.getTerm().set(Long.parseLong(plan_term_tf.textProperty().get()));
        loan_plan.getInterest().set(Double.parseDouble(plan_interest_tf.textProperty().get()) / 100);
        loan_plan.getPenalty().set(Double.parseDouble(plan_penalty_tf.textProperty().get()) / 100);
        loan_plan.getPaymentFrequencyProperty().set(payment_mode_cbox.getSelectionModel().getSelectedItem());
    }

    long final_num = 0;

    private void generate_id() {
        String temp_val;
        String string_val = "";
        string_val = RandomIDGenerator.getRandomNumber() + "";
        for (int i = 0; i < 3; i++) {
            int initial_num = RandomIDGenerator.getRandomNumber();
            temp_val = Integer.toString(initial_num);
            string_val = temp_val + string_val;
        }
        final_num = Long.parseLong(string_val);

        if (app.loanMasterList().isEmpty()) {
            plan_id_tf.textProperty().set(final_num + "");
            return;
        }

        app.loanPlanMasterlist().forEach(loan_plan -> {
            if (loan_plan.getId().get() == final_num) {
                generate_id();
            } else {
                plan_id_tf.textProperty().set(final_num + "");
            }
        });
    }

}
