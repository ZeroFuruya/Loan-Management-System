package e2p.icotp.layout.modal;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.Loan;
import e2p.icotp.model.LoanPlan;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.model.Enums.LoanStatus;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class LoanController {
    @FXML
    private TextField loan_id;
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
    private TextField plan_type_disp;
    @FXML
    private TextField plan_id_disp;
    @FXML
    private TextField plan_search;
    @FXML
    private ComboBox<String> status;

    @FXML
    private TableView<LoanPlan> planTable;
    @FXML
    TableColumn<LoanPlan, Integer> plan_id;
    @FXML
    TableColumn<LoanPlan, String> plan_type_name;
    @FXML
    TableColumn<LoanPlan, Long> plan_term;
    @FXML
    TableColumn<LoanPlan, Double> plan_interest;
    @FXML
    TableColumn<LoanPlan, Double> plan_penalty;

    // Icons
    @FXML
    private HBox term_icon;
    @FXML
    private HBox releaseDate_icon;
    @FXML
    private HBox maturityDate_icon;
    @FXML
    private HBox principal_icon;
    @FXML
    private HBox interest_icon;
    @FXML
    private HBox penalty_icon;
    @FXML
    private HBox due_icon;

    // ToolTips
    @FXML
    private Label principal_tt;
    @FXML
    private Tooltip save_tt;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private App app;
    private Loan og_loan = new Loan();
    private Loan loan = new Loan();
    private Loaner loaner = new Loaner();
    private LoanPlan loan_plan = new LoanPlan();
    private boolean isEdit;

    NumberFormat format = NumberFormat.getInstance();
    private MainController mc;

    private FilteredList<LoanPlan> loanPlanList;
    private ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    // PAID
    private double total_paid = 0.0d;
    private double total_paid_tmp = 0.0d;

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() throws SQLException {
        modify_loan_listener();
        if (isEdit) {
            LoanDAO.update(loan);
            app.loanMasterList().remove(og_loan);
            app.loanMasterList().add(loan);
        } else {
            LoanDAO.insert(loan);
            app.loanMasterList().add(loan);
        }
        notify_changes();
        mc.load_loan_table();
        mc.refresh_loan_list();
        ModalLoader.modal_close(app);
    }

    public void load(App app, Loan loan, boolean isEdit, MainController mc, Loaner loaner) {
        this.mc = mc;
        this.app = app;
        this.og_loan = loan;
        this.loan = loan;
        this.isEdit = isEdit;
        this.loaner = loaner;

        format.setGroupingUsed(true);

        loanPlanList = new FilteredList<>(app.loanPlanMasterlist(), p -> true);

        app.paymentMasterlist().forEach(payment -> {
            if (payment.getLoaner_id().getLoaner_id() == loan.getLoaner_id().getLoaner_id()) {
                if (payment.getLoan_id().getLoan_id() == loan.getLoan_id()) {
                    paymentList.add(payment);
                }
            }
        });

        load_bindings();
    }

    private void load_tables() {
        // LOAN PLAN TABLE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        plan_id.setCellValueFactory(plan -> {
            return plan.getValue().getId().asObject();
        });
        plan_type_name.setCellValueFactory(plan -> {
            return plan.getValue().getType().get().getName();
        });
        plan_term.setCellValueFactory(plan -> {
            return plan.getValue().getTerm().asObject();
        });
        plan_interest.setCellValueFactory(plan -> {
            return plan.getValue().getInterest().asObject();
        });
        plan_penalty.setCellValueFactory(plan -> {
            return plan.getValue().getPenalty().asObject();
        });
        planTable.setItems(loanPlanList);

        planTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                loan_plan = nv;
                init_plan_bindings();
                modify_loan_plan_field_listener();
            } else {
                init_clear();
            }
        });

        plan_search.textProperty().addListener((o, ov, nv) -> {
            loanPlanList.setPredicate(p -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }

                if (Integer.toString(p.getId().get()).contains(nv.toLowerCase())) {
                    return true;
                }

                return p.getType().get().getName().get().toLowerCase().contains(nv.toLowerCase());
            });
        });
    }

    private void init_plan_bindings() {
        plan_id_disp.setText(loan_plan.getId().get() + "");
        plan_type_disp.setText(loan_plan.getType().get().getName().get());
    }

    private void init_clear() {
    }

    private void load_cboxes() {
        // STATUS
        status.getItems().add(LoanStatus.APPLICATION);
        status.getItems().add(LoanStatus.CLOSED);
        status.getItems().add(LoanStatus.OPEN);
        status.getItems().add(LoanStatus.PAID);
        status.getSelectionModel().select(0);
    }

    private void load_bindings() {
        load_tables();
        load_cboxes();

        BooleanProperty minLoan = new SimpleBooleanProperty(false);

        principal.textProperty().addListener((o, ov, nv) -> {
            if (!nv.isEmpty()) {
                if (Double.parseDouble(nv) < 1000.0) {
                    minLoan.set(true);
                } else {
                    minLoan.set(false);
                }
            }
        });

        plan_id.prefWidthProperty().bind(planTable.widthProperty().multiply(0.16));
        plan_type_name.prefWidthProperty().bind(planTable.widthProperty().multiply(0.35));
        plan_term.prefWidthProperty().bind(planTable.widthProperty().multiply(0.17));
        plan_interest.prefWidthProperty().bind(planTable.widthProperty().multiply(0.16));
        plan_penalty.prefWidthProperty().bind(planTable.widthProperty().multiply(0.16));

        term_icon.visibleProperty().bind(term.textProperty().isEmpty());
        releaseDate_icon.visibleProperty().bind(release_date.valueProperty().isNull());
        maturityDate_icon.visibleProperty().bind(maturity_date.valueProperty().isNull());
        principal_icon.visibleProperty().bind(principal.textProperty().isEmpty().or(minLoan));
        interest_icon.visibleProperty().bind(interest.textProperty().isEmpty());
        penalty_icon.visibleProperty().bind(penalty.textProperty().isEmpty());
        due_icon.visibleProperty().bind(due.textProperty().isEmpty());

        principal_tt.textProperty().bind(Bindings.when(minLoan).then("Minimum Loan is 1000").otherwise("Empty Field"));
        save_tt.textProperty().bind(Bindings.when(planTable.getSelectionModel().selectedItemProperty().isNull())
                .then("Select Loan Plan First").otherwise("Fill All Fields First"));

        save.disableProperty()
                .bind(plan_id_disp.textProperty().isEqualTo("0").or(minLoan));

        if (isEdit) {
            loan_id.setText(loan.getLoan_id() + "");
            term.setText(loan.getTerm() + "");
            release_date.setValue(loan.getRelease_date());
            maturity_date.setValue(loan.getMaturity_date());
            principal.setText(String.format("%.2f", loan.getPrincipal()));
            interest.setText(loan.getInterest() + "");
            penalty.setText(loan.getPenalty() + "");
            due.setText(loan.getDue() + "");
            planTable.getSelectionModel().select(loan.getLoanPlan());
            plan_id_disp.setText(loan.getLoanPlan().getId().get() + "");
            plan_type_disp.setText(loan.getLoanPlan().getType().get().getName().get());
            status.getSelectionModel().select(loan.getStatus());
        } else {
            generate_id();
            term.setText(loan.getTerm() + "");
            release_date.setValue(LocalDate.now());
            maturity_date.setValue(LocalDate.now());
            principal.setText("0.0");
            interest.setText("0.0");
            penalty.setText("0.0");
            due.setText(LocalDate.now().getDayOfMonth() + "");
            plan_id_disp.setText("0");
            plan_type_disp.setText("Nothing is selected");
            status.getSelectionModel().select(LoanStatus.APPLICATION);
        }

    }

    // CUSTOMS
    private void loan_logic() {

    }

    private void over_due_listener() {

    }

    private void modify_loan_plan_field_listener() {
        LocalDate tempDate = release_date.getValue().plusDays(loan_plan.getTerm().get());
        LocalDate matureDate = LocalDate.of(tempDate.getYear(), tempDate.getMonthValue(),
                release_date.getValue().getDayOfMonth());

        term.setText(loan_plan.getTerm().get() + "");
        maturity_date.setValue(matureDate);
        interest.setText(loan_plan.getInterest().get() + "");
        penalty.setText(loan_plan.getPenalty().get() + "");
        due.setText(release_date.getValue().getDayOfMonth() + "");
    }

    private void modify_loan_listener() {

        paymentList.forEach(payment -> {
            total_paid_tmp = payment.getPayment_amount();
            total_paid = total_paid + total_paid_tmp;
        });

        loan.setLoan_id(Integer.parseInt(loan_id.getText()));
        loan.setLoaner_id(loaner);
        loan.setLoanType(loan_plan.getType().get());
        loan.setLoanPlan(loan_plan);
        loan.setRelease_date(release_date.getValue());
        loan.setTerm(Integer.parseInt(term.getText()));
        loan.setMaturity_date(maturity_date.getValue());
        loan.setPrincipal(Double.parseDouble(principal.getText()));
        loan.setInterest(Double.parseDouble(interest.getText()));
        loan.setPenalty(Double.parseDouble(penalty.getText()));
        loan.setDue(Integer.parseInt(due.getText()));
        loan.setPaid(total_paid);
        loan.setBalance(Double.parseDouble(principal.getText()) - total_paid);
        loan.setStatus(status.getSelectionModel().getSelectedItem());

    }

    private long final_num = 0;

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
                loan_id.textProperty().set(final_num + "");
            }
        });
    }

    private void notify_changes() throws SQLException {
        app.loanMasterList().setAll(LoanDAO.getMasterlist());
    }
}
