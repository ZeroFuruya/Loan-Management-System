package e2p.icotp.layout;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import e2p.icotp.App;
import e2p.icotp.layout.factory.TypesFactory;
import e2p.icotp.model.Collateral;
import e2p.icotp.model.Loan;
import e2p.icotp.model.LoanPlan;
import e2p.icotp.model.LoanType;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.model.Enums.LoanStatus;
import e2p.icotp.model.Enums.PaymentFrequency;
import e2p.icotp.service.loader.LogInLoader;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanDAO;
import e2p.icotp.service.server.dao.LoanPlanDAO;
import e2p.icotp.service.server.dao.LoanerDAO;
import e2p.icotp.service.server.dao.PaymentDAO;
import e2p.icotp.service.server.dao.RemovedPaymentDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.util.custom.cbox.LoanTypeListCell;
import e2p.icotp.util.custom.cbox.LoanTypeStringConverter;
import e2p.icotp.util.custom.date.DateUtil;
import e2p.icotp.util.custom.formatters.DoubleTextFieldFormatter;
import e2p.icotp.util.custom.formatters.IDTextFieldFormatter;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainController {

    // CONSTANTS
    // private static final int fit_width = 200;
    // private static final int fit_height = 50;
    @FXML
    Button refresh_button;

    // STACKPANE-----------------------------------------------------
    @FXML
    StackPane motherPane;

    // VBOX (Child of Mother
    // StackPane)-----------------------------------------------------
    @FXML
    VBox home_box;
    @FXML
    VBox loaners_box;
    @FXML
    VBox loans_box;
    @FXML
    VBox payments_box;
    @FXML
    VBox collateral_box;
    @FXML
    VBox types_box;
    @FXML
    VBox plans_box;

    // VBOX-----------------------------------------------------
    @FXML
    VBox toggle_btn_container;
    @FXML
    VBox loan_information_container;
    @FXML
    VBox payment_information_container;
    @FXML
    VBox collateral_information_container;

    // HBOX-----------------------------------------------------
    @FXML
    HBox loaner_information_container;
    @FXML
    HBox loaner_name_container;

    // TOGGLE BUTTONS-----------------------------------------------------
    @FXML
    ToggleButton home_button;
    @FXML
    ToggleButton loaners_button;
    @FXML
    Tab loans_button;
    @FXML
    Tab payments_button;
    @FXML
    ToggleButton types_button;
    @FXML
    ToggleButton plans_button;

    // TABLEVIEWS-----------------------------------------------------
    @FXML
    TableView<Loaner> loanerTable;
    @FXML
    TableView<Loan> loanTable;
    @FXML
    TableView<Payment> paymentTable;
    @FXML
    TableView<LoanPlan> loanPlanTable;
    @FXML
    TableView<Collateral> collateralTable;

    // TABLE COLS - Loaner-----------------------------------------------------
    @FXML
    TableColumn<Loaner, Long> loaner_id;
    @FXML
    TableColumn<Loaner, String> loaner_name;

    @FXML
    Label loaner_name_label;
    @FXML
    Label loaner_id_label;
    @FXML
    Label loaner_address_label;
    @FXML
    Label loaner_birthdate_label;
    @FXML
    Label loaner_phone_label;
    @FXML
    Label loaner_social_label;
    @FXML
    Label loaner_place_birth_label;
    @FXML
    Label loaner_email_label;
    @FXML
    Label loaner_citizen_label;
    @FXML
    Label loaner_civil_label;

    @FXML
    Button loaner_edit_button;
    @FXML
    Button loaner_add_button;
    @FXML
    Button loaner_remove_button;

    @FXML
    TextField loaner_search;

    // TABLE COLS - Loan-----------------------------------------------------
    @FXML
    TableColumn<Loan, Integer> loan_id;
    @FXML
    TableColumn<Loan, String> loan_loan_type;

    @FXML
    Label loan_id_label;
    @FXML
    Label loan_plan_label;
    @FXML
    Label loan_release_date_label;
    @FXML
    Label loan_term_label;
    @FXML
    Label loan_maturity_date_label;
    @FXML
    Label loan_principal_label;
    @FXML
    Label loan_interest_label;
    @FXML
    Label loan_penalty_label;
    @FXML
    Label loan_due_label;
    @FXML
    Label loan_paid_label;
    @FXML
    Label loan_balance_label;
    @FXML
    Label loan_next_due_label;
    @FXML
    Label loan_next_amount_label;
    @FXML
    Label loan_overdue_label;
    @FXML
    Label loan_penalty_amount_label;
    @FXML
    Label loan_total_unpaid_label;

    @FXML
    Button loan_edit_button;
    @FXML
    Button loan_add_button;
    @FXML
    Button loan_remove_button;

    @FXML
    Label next_due_err;
    @FXML
    Label next_amount_err;

    @FXML
    TextField loan_search;

    @FXML
    ImageView status_image;

    @FXML
    HBox loan_next_box;

    // NEXT LOAN
    ObjectProperty<LocalDate> next_due = new SimpleObjectProperty<>(LocalDate.now());

    // TABLE COLS - Payment-----------------------------------------------------
    @FXML
    TableColumn<Payment, Long> payment_id;
    @FXML
    TableColumn<Payment, Integer> payment_loan_id;
    @FXML
    TableColumn<Payment, String> payment_loaner_name;
    @FXML
    TableColumn<Payment, String> payment_due_dates;
    @FXML
    TableColumn<Payment, String> payment_payment_date;

    @FXML
    Label payment_loan_id_label;
    @FXML
    Label payment_id_label;
    @FXML
    Label payment_date_label;
    @FXML
    Label payment_amount_label;

    @FXML
    Button payment_edit_button;
    @FXML
    Button payment_add_button;
    @FXML
    Button payment_remove_button;

    @FXML
    TextField payment_search;

    // TABLE COLS - Collateral-----------------------------------------------------
    @FXML
    TableColumn<Collateral, Integer> collateral_id;
    @FXML
    TableColumn<Collateral, String> collateral_status;
    @FXML
    TableColumn<Collateral, String> collateral_loan_type;

    @FXML
    Label collateral_id_label;
    @FXML
    Label collateral_loan_id_label;
    @FXML
    Label collateral_type_label;
    @FXML
    Label collateral_label;
    @FXML
    Label collateral_status_label;

    @FXML
    ImageView collateralImage;

    @FXML
    TextField collateral_search;

    @FXML
    Button collateral_modify_button;
    @FXML
    Button collateral_add_button;
    @FXML
    Button collateral_remove_button;

    // TABLE COLS - Plan-----------------------------------------------------
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
    @FXML
    TableColumn<LoanPlan, String> plan_payment_mode;

    @FXML
    Button plan_add_button;
    @FXML
    Button plan_modify_button;
    @FXML
    Button plan_remove_button;

    // @FXML
    // ToggleButton edit_toggle;
    // BooleanProperty plan_isEdit = new SimpleBooleanProperty(false);

    // @FXML
    // TextField plan_id_tf;
    // @FXML
    // TextField plan_term_tf;
    // @FXML
    // TextField plan_interest_tf;
    // @FXML
    // TextField plan_penalty_tf;
    @FXML
    TextField plan_search;
    // @FXML
    // ComboBox<LoanType> plan_type_cbox;
    // @FXML
    // ComboBox<String> payment_mode_cbox;

    // @FXML
    // HBox plan_type_err;
    // @FXML
    // HBox plan_term_err;
    // @FXML
    // HBox plan_interest_err;
    // @FXML
    // HBox plan_penalty_err;
    // @FXML
    // HBox payment_mode_err;

    // SCROLLPANE-----------------------------------------------------
    @FXML
    ScrollPane types_scroll_pane;
    @FXML
    VBox types_container;

    // APP
    // ------------------------------------------------------------------------------------------

    App app;

    // LISTS
    // ----------------------------------------------------------------------------------------

    FilteredList<Loaner> loanerList;
    FilteredList<Loan> loanList;
    FilteredList<Payment> paymentList;
    FilteredList<Collateral> collateralList;
    FilteredList<LoanType> loanTypeList;
    FilteredList<LoanPlan> loanPlanList;

    // OBSERVABLE LIST
    ObservableList<Loan> loanObservableList;
    ObservableList<Payment> paymentObservableList;
    ObservableList<Collateral> collateralObservableList;

    // MODELS
    // ---------------------------------------------------------------------------------------
    Loaner og_loaner = new Loaner();
    Loaner loaner = new Loaner();
    Loan og_loan = new Loan();
    Loan loan = new Loan();
    Payment og_payment = new Payment();
    Payment payment = new Payment();
    Collateral og_collateral = new Collateral();
    Collateral collateral = new Collateral();
    LoanType og_loan_type = new LoanType();
    LoanType loan_type = new LoanType();
    LoanPlan og_loan_plan = new LoanPlan();
    LoanPlan loan_plan = new LoanPlan();

    BooleanProperty isEdit = new SimpleBooleanProperty(false);

    NumberFormat format = NumberFormat.getInstance();

    @FXML
    private void handle_login() throws IOException {
        LogInLoader.load_log_in(app);
    }

    @FXML
    private void handle_signup() throws IOException {
        LogInLoader.load_sign_up(app);
    }

    // LOANER BUTTON HANDLES
    // --------------------------------------------------------------------------------
    @FXML
    private void handle_loaner_edit() throws IOException {
        isEdit.set(true);
        ModalLoader.load_loaner_update(app, loaner, isEdit.get(), this);
    }

    @FXML
    private void handle_loaner_add() throws IOException {
        isEdit.set(false);
        ModalLoader.load_loaner_update(app, new Loaner(), isEdit.get(), this);
    }

    @FXML
    private void handle_loaner_remove() {
        // TODO REMOVE ALL PAYMENT IF LOANER IS DELETED
        LoanerDAO.remove(og_loaner);
        app.loanerMasterlist().remove(og_loaner);
    }

    // LOAN BUTTON HANDLES
    // ----------------------------------------------------------------------------------
    BooleanProperty doRefresh = new SimpleBooleanProperty(false);

    public BooleanProperty getDoRefresh() {
        return this.doRefresh;
    }

    @FXML
    private void handle_loan_edit() throws IOException {
        ModalLoader.load_loan_update(app, loan, true, this, loaner);
    }

    @FXML
    private void handle_loan_add() throws IOException {
        ModalLoader.load_loan_update(app, new Loan(), false, this, loaner);
    }

    @FXML
    private void handle_loan_remove() {
        // TODO REMOVE ALL PAYMENT IF LOAN IS DELETED
        // PaymentDAO.removeByLoanId(, og_loan.getLoan_id());
        // app.paymentMasterlist().removeAll(paymentList);
        LoanDAO.remove(og_loan);
        app.loanMasterList().remove(og_loan);
        load_loan_table();
        refresh_loan_list();
    }

    // PAYMENT BUTTON HANDLES
    // ----------------------------------------------------------------------------------
    BooleanProperty doPaymentRefresh = new SimpleBooleanProperty(false);

    public BooleanProperty doPaymentRefresh() {
        return this.doRefresh;
    }

    @FXML
    private void handle_payment_edit() throws IOException {
        ModalLoader.load_payment_update(app, loan, true, this, loaner, payment);
    }

    @FXML
    private void handle_payment_add() throws IOException {
        ModalLoader.load_payment_update(app, loan, false, this, loaner, payment);
    }

    @FXML
    private void handle_payment_remove() {
        // TODO FIX REMOVED PAYMENTS
        if (loan == null)
            return;
        loan.setNextDueDate(loan.getNextDueDate().minusMonths(1));
        loan.getTotalUnpaidProperty().set(loan.getTotalUnpaidProperty().get() + payment.getPayment_amount());
        loan.getBalanceProperty().set(loan.getBalanceProperty().get() + payment.getPayment_amount());
        RemovedPaymentDAO.insert(og_payment);
        app.removedPaymentMasterlist().add(og_payment);
        PaymentDAO.remove(og_payment);
        app.paymentMasterlist().remove(og_payment);
        load_loan_table();
        refresh_loan_list();
    }

    // LOAN PLAN HANDLES
    @FXML
    private void handle_add_loan_plan() throws IOException {
        ModalLoader.load_loan_plan_update(app, new LoanPlan(), false, this, loanTypeList);
    }

    @FXML
    private void handle_modify_loan_plan() throws IOException {
        ModalLoader.load_loan_plan_update(app, loan_plan, true, this, loanTypeList);
    }

    @FXML
    private void handle_remove_plan() {
        LoanPlanDAO.remove(og_loan_plan.getId().get());
        app.loanPlanMasterlist().remove(og_loan_plan);
    }
    // START HERE ------------------------------------------------------------------

    public void load(App app) {
        this.app = app;
        format.setGroupingUsed(true);

        // SETS HOME AS DEFAULT
        home_button.setSelected(true);

        loaner_information_container.setVisible(false);
        loaner_name_container.setVisible(false);
        payment_information_container.setVisible(false);
        payments_box.setVisible(false);
        collateral_box.setVisible(false);

        loanTypeList = new FilteredList<>(app.loanTypeMasterlist(), p -> true);

        init_tables();
        init_bindings();
        init_togbutton_listeners();
        init_table_listeners();
        init_plans();
        _init_types();
        init_anims();
    }

    private void init_tables() {
        this.loanerList = new FilteredList<>(app.loanerMasterlist(), p -> true);
        this.loanPlanList = new FilteredList<>(app.loanPlanMasterlist(), p -> true);

        this.loanObservableList = FXCollections.observableArrayList();
        this.paymentObservableList = FXCollections.observableArrayList();
        this.collateralObservableList = FXCollections.observableArrayList();

        // LOANER TABLE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        loaner_id.setCellValueFactory(loaner -> {
            return loaner.getValue().getLoaner_idProperty().asObject();
        });
        loaner_name.setCellValueFactory(loaner -> {
            return loaner.getValue().getNameProperty();
        });
        loanerTable.setItems(loanerList);

        // LOAN TABLE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        loan_id.setCellValueFactory(loan -> {
            return loan.getValue().getLoanID_Property().asObject();
        });
        loan_loan_type.setCellValueFactory(loan -> {
            return loan.getValue().getLoanType().getName();
        });

        // PAYMENT TABLE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        payment_id.setCellValueFactory(payment -> {
            return payment.getValue().getPayment_id_Property().asObject();
        });
        payment_loan_id.setCellValueFactory(payment -> {
            return payment.getValue().getLoan_id_Property().get().getLoanID_Property().asObject();
        });
        payment_loaner_name.setCellValueFactory(payment -> {
            return payment.getValue().getLoan_id_Property().get().getLoanerID_Property().get().getNameProperty();
        });
        payment_due_dates.setCellValueFactory(payment -> {
            return DateUtil.localizeDateProperty(payment.getValue().getPayment_date_Property().get());
        });
        payment_payment_date.setCellValueFactory(payment -> {
            return DateUtil.localizeDateProperty(payment.getValue().getDatePaymentProperty().get());
        });

        // COLLATERAL TABLE
        // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        collateral_id.setCellValueFactory(collateral -> {
            return collateral.getValue().getCollateralId_property().asObject();
        });
        collateral_status.setCellValueFactory(collateral -> {
            return collateral.getValue().getStatusProperty();
        });
        collateral_loan_type.setCellValueFactory(collateral -> {
            return collateral.getValue().getPlan_id().getType().get().getName();
        });

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
        plan_payment_mode.setCellValueFactory(plan -> {
            return plan.getValue().getPaymentFrequencyProperty();
        });
        loanPlanTable.setItems(loanPlanList);
    }

    private void set__filtered_tables(int val) {
        switch (val) {
            case 1:
                this.loanList = new FilteredList<>(loanObservableList, p -> true);
                break;
            case 2:
                this.paymentList = new FilteredList<>(paymentObservableList, p -> true);
                break;
            case 3:
                this.collateralList = new FilteredList<>(collateralObservableList, p -> true);
                break;
        }
    }

    private void init_bindings() {

        // TABLE WIDTHS
        loaner_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.75));
        loan_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        loan_loan_type.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.75));
        payment_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        payment_loan_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        payment_loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.750));
        collateral_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.3));
        collateral_status.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        collateral_loan_type.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.700));

        // HOME
        home_box.visibleProperty().bind(home_button.selectedProperty());
        home_button.disableProperty().bind(home_button.selectedProperty());
        home_button.prefHeightProperty().bind(toggle_btn_container.heightProperty().multiply(0.125));
        // LOANERS
        loaners_box.visibleProperty().bind(loaners_button.selectedProperty());
        loaners_button.disableProperty().bind(loaners_button.selectedProperty());
        loaners_button.prefHeightProperty().bind(toggle_btn_container.heightProperty().multiply(0.125));
        // TYPES
        types_box.visibleProperty().bind(types_button.selectedProperty());
        types_button.disableProperty().bind(types_button.selectedProperty());
        types_button.prefHeightProperty().bind(toggle_btn_container.heightProperty().multiply(0.125));
        // PLANS
        plans_box.visibleProperty().bind(plans_button.selectedProperty());
        plans_button.disableProperty().bind(plans_button.selectedProperty());
        plans_button.prefHeightProperty().bind(toggle_btn_container.heightProperty().multiply(0.125));

        loan_edit_button.disableProperty().bind(loanTable.getSelectionModel().selectedItemProperty().isNull());
        loan_remove_button.disableProperty().bind(loanTable.getSelectionModel().selectedItemProperty().isNull());

        payment_edit_button.disableProperty().bind(paymentTable.getSelectionModel().selectedItemProperty().isNull());
        payment_remove_button.disableProperty().bind(paymentTable.getSelectionModel().selectedItemProperty().isNull());

        BooleanProperty planIs = new SimpleBooleanProperty(false);
        BooleanBinding planIsUsed = Bindings.createBooleanBinding(() -> {
            app.loanMasterList().forEach(loan -> {
                app.loanPlanMasterlist().forEach(plan -> {
                    if (loan.getLoanPlan().getId().get() == plan.getId().get()) {
                        planIs.set(true);
                        return;
                    }
                });
            });
            return planIs.get();
        }, app.loanMasterList());

        plan_modify_button.disableProperty()
                .bind(loanPlanTable.getSelectionModel().selectedItemProperty().isNull().or(planIsUsed));
        plan_remove_button.disableProperty()
                .bind(loanPlanTable.getSelectionModel().selectedItemProperty().isNull().or(planIsUsed));
    }

    private void init_togbutton_listeners() {
        // HOME
        home_button.selectedProperty().addListener((o, ov, nv) -> {
            if (home_button.isSelected()) {
                loaners_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // LOANER
        loaners_button.selectedProperty().addListener((o, ov, nv) -> {
            if (loaners_button.isSelected()) {
                home_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // TYPE
        types_button.selectedProperty().addListener((o, ov, nv) -> {
            if (types_button.isSelected()) {
                home_button.selectedProperty().set(false);
                loaners_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // PLAN
        plans_button.selectedProperty().addListener((o, ov, nv) -> {
            if (plans_button.isSelected()) {
                home_button.selectedProperty().set(false);
                loaners_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
            }
        });
    }

    private void init_table_listeners() {

        loan_add_button.disableProperty().bind(loanerTable.getSelectionModel().selectedItemProperty().isNull());
        loan_edit_button.disableProperty().bind(loanerTable.getSelectionModel().selectedItemProperty().isNull()
                .or(loanTable.getSelectionModel().selectedItemProperty().isNull()));
        loan_remove_button.disableProperty().bind(loanerTable.getSelectionModel().selectedItemProperty().isNull()
                .or(loanTable.getSelectionModel().selectedItemProperty().isNull()));

        // LOANER ------------------------------------------
        loanerTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                loaner_information_container.setVisible(true);
                loaner_name_container.setVisible(true);
                og_loaner = nv;
                loaner = og_loaner;
                load_loan_table();
                refresh_loan_list();
                clear_payment_table();
                _init_loaner_bindings();
                loan_information_container.setVisible(true);
                payment_information_container.setVisible(false);
                payments_box.setVisible(false);
                collateral_box.setVisible(false);
            } else {
                load_empty_loan_table();
                refresh_loan_list();
                clear_payment_table();
                loaner_information_container.setVisible(false);
                loaner_name_container.setVisible(false);
                loan_information_container.setVisible(false);
                payment_information_container.setVisible(false);
                collateral_information_container.setVisible(false);
                payments_box.setVisible(false);
                collateral_box.setVisible(false);
            }
        });
        loaner_search.textProperty().addListener((o, ov, nv) -> {
            loanerList.setPredicate(p -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }

                if (Long.toString(p.getLoaner_id()).contains(nv.toLowerCase())) {
                    return true;
                }

                return p.getName().toLowerCase().contains(nv.toLowerCase());
            });
        });

        // LOAN --------------------------------------------
        loanTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                og_loan = nv;
                loan = og_loan;
                load_payment_table();
                refresh_payment_list();
                load_collateral_table();
                refresh_collateral_list();
                _init_loan_bindings();
                loan_information_container.setVisible(true);
                payments_box.setVisible(true);
                collateral_box.setVisible(true);
            } else {
                payment_information_container.setVisible(false);
                collateral_information_container.setVisible(false);
                payments_box.setVisible(false);
                collateral_box.setVisible(false);
            }

        });
        loan_search.textProperty().addListener((o, ov, nv) -> {
            loanList.setPredicate(p -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }

                if (Integer.toString(p.getLoan_id()).contains(nv.toLowerCase())) {
                    return true;
                }

                return p.getLoanerID_Property().get().getName().toLowerCase().contains(nv.toLowerCase());
            });
        });

        // PAYMENT --------------------------------------------
        paymentTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                payment_information_container.setVisible(true);
                og_payment = nv;
                payment = og_payment;
                _init_payment_bindings();
            } else {
                payment_information_container.setVisible(false);
            }
        });
        payment_search.textProperty().addListener((o, ov, nv) -> {
            paymentList.setPredicate(p -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }

                if (Integer.toString(p.getLoan_id_Property().get().getLoan_id()).contains(nv.toLowerCase())) {
                    return true;
                }

                return p.getLoaner_id_Property().get().getName().toLowerCase().contains(nv.toLowerCase());
            });
        });

        // COLLATERAL --------------------------------------------
        collateralTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                collateral_information_container.setVisible(true);
                og_collateral = nv;
                collateral = og_collateral;
                _init_collateral_bindings();
            } else {
                collateral_information_container.setVisible(false);
            }
        });
        collateral_search.textProperty().addListener((o, ov, nv) -> {
            collateralList.setPredicate(p -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }

                if (Integer.toString(p.getLoan_id().getLoan_id()).toLowerCase().contains(nv.toLowerCase())) {
                    return true;
                }

                return p.getPlan_id().getType().get().getName().get().toLowerCase().contains(nv.toLowerCase());
            });
        });
    }

    private void clear_payment_table() {
        paymentObservableList.removeAll(paymentObservableList);
        set__filtered_tables(2);
        paymentTable.setItems(paymentList);
    }

    public void clear_loan_list() {
        loanObservableList.removeAll(loanObservableList);
        set__filtered_tables(1);
        loanTable.setItems(loanList);
    }

    public void refresh_loan_list() {
        set__filtered_tables(1);
        loanTable.setItems(loanList);
    }

    private void refresh_payment_list() {
        set__filtered_tables(2);
        paymentTable.setItems(paymentList);
    }

    private void refresh_collateral_list() {
        set__filtered_tables(3);
        collateralTable.setItems(collateralList);
    }

    public void load_loan_table() {
        loanObservableList.removeAll(loanObservableList);
        app.loanMasterList().forEach(loan -> {
            if (loaner.getLoaner_id() == loan.getLoanerID_Property().get().getLoaner_id()) {
                loanObservableList.add(loan);
            }
        });
    }

    private void load_payment_table() {
        paymentObservableList.removeAll(paymentObservableList);
        app.paymentMasterlist().forEach(payment -> {
            if (loan.getLoan_id() == payment.getLoan_id().getLoan_id()) {
                paymentObservableList.add(payment);
            }
        });
    }

    private void load_collateral_table() {
        collateralObservableList.removeAll(collateralObservableList);
        app.collateralMasterlist().forEach(collateral -> {
            if (loan.getLoan_id() == collateral.getLoan_id().getLoan_id()) {
                collateralObservableList.add(collateral);
            }
        });
    }

    private void load_empty_loan_table() {
        loanObservableList.removeAll(loanObservableList);
        app.loanMasterList().forEach(loan -> {
            if (0 >= loan.getLoanerID_Property().get().getLoaner_id()) {
                loanObservableList.add(loan);
            }
        });
    }

    private void init_anims() {
        home_button.selectedProperty().addListener((o, ov, nv) -> {
            if (nv) {
                transitions(home_box);
            }
        });
        loaners_button.selectedProperty().addListener((o, ov, nv) -> {
            if (nv) {
                transitions(loaners_box);
            }
        });
        types_button.selectedProperty().addListener((o, ov, nv) -> {
            if (nv) {
                transitions(types_box);
            }
        });
        plans_button.selectedProperty().addListener((o, ov, nv) -> {
            if (nv) {
                transitions(plans_box);
            }
        });

    }

    private void transitions(Node node) {
        FadeTransition translate = new FadeTransition();
        translate.setNode(node);
        translate.setDuration(Duration.millis(400));
        translate.setInterpolator(Interpolator.EASE_IN);
        translate.setFromValue(0);
        translate.setToValue(1);
        translate.play();
    }

    @FXML
    private void loan_tab_transitions() {
        FadeTransition translate = new FadeTransition();
        translate.setNode(loan_information_container);
        translate.setDuration(Duration.millis(400));
        translate.setInterpolator(Interpolator.EASE_IN);
        translate.setFromValue(0);
        translate.setToValue(1);
        translate.play();
    }

    @FXML
    private void payment_tab_transitions() {
        FadeTransition translate = new FadeTransition();
        translate.setNode(payments_box);
        translate.setDuration(Duration.millis(400));
        translate.setInterpolator(Interpolator.EASE_IN);
        translate.setFromValue(0);
        translate.setToValue(1);
        translate.play();
    }

    @FXML
    private void collateral_tab_transitions() {
        FadeTransition translate = new FadeTransition();
        translate.setNode(collateral_box);
        translate.setDuration(Duration.millis(400));
        translate.setInterpolator(Interpolator.EASE_IN);
        translate.setFromValue(0);
        translate.setToValue(1);
        translate.play();
    }

    // INDIVIDUAL FUNCTIONS ---------------------------------------------------
    private void _init_loaner_bindings() {
        loaner_name_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", loaner.getName());
        }, loaner.getNameProperty()));
        loaner_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", loaner.getLoaner_id());
        }, loaner.getLoaner_idProperty()));
        loaner_address_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", loaner.getAddress());
        }, loaner.getAddressProperty()));
        loaner_place_birth_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", loaner.getPlaceOfBirth());
        }, loaner.getPlaceOfBirthProperty()));
        loaner_email_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", loaner.getEmail());
        }, loaner.getEmailProperty()));
        loaner_citizen_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", loaner.getCitizenship());
        }, loaner.getCitizenshipProperty()));
        loaner_civil_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", loaner.getCivilStatus());
        }, loaner.getCivilStatusProperty()));
        loaner_phone_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", loaner.getPhone());
        }, loaner.getPhoneProperty()));
        loaner_birthdate_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", DateUtil.format(loaner.getBirthdate()));
        }, loaner.getBirthdateProperty()));
        loaner_social_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", loaner.getSocial_security());
        }, loaner.getSocial_securityProperty()));

        loaner_edit_button.disableProperty().bind(loanerTable.getSelectionModel().selectedItemProperty().isNull());
        loaner_remove_button.disableProperty().bind(loanerTable.getSelectionModel().selectedItemProperty().isNull());
    }

    private void _init_loan_bindings() {
        if (loan == null)
            return;
        payment_add_button.disableProperty().bind(loan.getStatusProperty().isEqualTo(LoanStatus.APPLICATION));

        loan_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", loan.getLoan_id());
        }, loan.getLoanID_Property()));
        loan_plan_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", loan.getLoanPlan().getId().get());
        }, loan.getLoanPlan().getId()));
        loan_release_date_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", DateUtil.localizeDate(loan.getRelease_date()));
        }, loan.getRelease_date_Property()));
        loan_term_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", loan.getTerm());
        }, loan.getTermProperty()));
        loan_maturity_date_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", DateUtil.localizeDate(loan.getMaturity_date()));
        }, loan.getMaturity_date_Property()));
        loan_principal_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("$%s", format.format(loan.getPrincipal()));
        }, loan.getPrincipalProperty()));
        loan_interest_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%.2f" + "%%", loan.getInterest());
        }, loan.getInterestProperty()));
        loan_penalty_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%.2f" + "%%", loan.getPenalty());
        }, loan.getPenaltyProperty()));
        loan_due_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Day %d of every month", loan.getDue());
        }, loan.getDueProperty()));
        loan_paid_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("$%s", format.format(loan.getPaid()));
        }, loan.getPaidProperty()));

        loan_next_logic_monthly();

        loan_balance_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("$%s", format.format(loan.getBalance()));
        }, loan.getBalanceProperty()));

        status_image_setter(loan.getStatus());
        loan_next_logic_monthly();
        if (loan.getStatus().equals(LoanStatus.OPEN)) {
            // TODO add if else for Mothly or Daily or Annually
            // TODO GET PAYMENT FREQUENCY FROM LOAN OBJECT
            loan_next_box.setVisible(true);
        } else {
            loan_next_box.setVisible(false);
        }

        loan_edit_button.disableProperty().bind(
                loanTable.getSelectionModel().selectedItemProperty().isNull().or(Bindings.createBooleanBinding(() -> {
                    return paymentList.isEmpty() ? false : true;
                }, paymentList)));

    }

    private void _init_payment_bindings() {
        payment_loan_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", payment.getLoan_id_Property().get().getLoan_id());
        }, payment.getLoan_id_Property().get().getLoanID_Property()));
        payment_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", payment.getPayment_id());
        }, payment.getPayment_id_Property()));
        payment_date_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", DateUtil.localizeDate(payment.getPaymentDate()));
        }, payment.getPayment_date_Property()));
        payment_amount_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("$%s", format.format(payment.getPayment_amount()));
        }, payment.getPayment_amount_Property()));
    }

    private void _init_collateral_bindings() {
        collateral_status_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", collateral.getLoan_id().getLoan_id());
        }, collateral.getLoan_id().getLoanID_Property()));
        collateral_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%d", collateral.getCollateral_id());
        }, collateral.getCollateralId_property()));
        collateral_type_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", collateral.getPlan_id().getType().get().getName().get());
        }, collateral.getPlan_id().getType().get().getName()));
        collateral_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", collateral.getCollateral());
        }, collateral.getCollateral_property()));
    }

    // CUSTOMS
    private void status_image_setter(String status) {
        switch (status) {
            case LoanStatus.APPLICATION:
                status_image.setImage(new Image(App.class.getResourceAsStream("assets/images/app_logo.png")));
                break;
            case LoanStatus.OPEN:
                status_image
                        .setImage(new Image(App.class.getResourceAsStream("assets/images/open2-removebg-preview.png")));
                break;
            case LoanStatus.PAID:
                status_image
                        .setImage(new Image(App.class.getResourceAsStream("assets/images/paid1-removebg-preview.png")));
                break;
        }
    }

    // LOAN TYPES ---------------------------------------------------------
    // LOAN TYPES ---------------------------------------------------------
    // LOAN TYPES ---------------------------------------------------------
    private void _init_types() {
        types_scroll_pane.setStyle("-fx-background: #bb161e; -fx-border-color: #bb161e;");
        types_container.prefWidthProperty().bind(types_scroll_pane.widthProperty().subtract(18));
        types_container.setSpacing(10);
        loanTypeList.forEach(type -> {
            Label label1 = TypesFactory.createLabel(type.getId().get() + "", 17);
            HBox val1 = TypesFactory.createLabelContainer(label1, types_container, 0.04, Pos.CENTER, 1);
            Label label2 = TypesFactory.createLabel(type.getName().get(), 17);
            HBox val2 = TypesFactory.createLabelContainer(label2, types_container, 0.115, Pos.CENTER_LEFT, 1);
            Label label3 = TypesFactory.createLabel(type.getDesc().get(), 14);
            HBox val3 = TypesFactory.createLabelContainer(label3, types_container, 0.8, Pos.CENTER_LEFT, 0);

            types_container.getChildren().add(TypesFactory.createHBox(val1, val2, val3,
                    TypesFactory.createButton("Modify", Color.RED, type, this, app)));
        });
    }

    // LOAN PLAN ---------------------------------------------------------
    // LOAN PLAN ---------------------------------------------------------
    // LOAN PLAN ---------------------------------------------------------
    // TODO POPUP MODAL
    private void init_plans() {
        loanPlanTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            og_loan_plan = nv;
            loan_plan = og_loan_plan;
        });
        plan_search.textProperty().addListener((o, ov, nv) -> {
            loanPlanList.setPredicate(p -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }

                if (Integer.toString(p.getId().get()).toLowerCase().contains(nv.toLowerCase())) {
                    return true;
                }
                if (p.getPaymentFrequencyProperty().get().toLowerCase().contains(nv.toLowerCase())) {
                    return true;
                }

                return p.getType().get().getName().get().toLowerCase().contains(nv.toLowerCase());
            });
        });
    }

    private void save_loan() {
        if (isEdit.get()) {
            app.loanMasterList().remove(og_loan);
            app.loanMasterList().add(loan);
        } else {
            app.loanMasterList().add(loan);
        }
    }

    LocalDate next_due_date = LocalDate.now();
    long days_skipped = 0;
    long total_days = 0;
    long total_months = 0;
    long total_months_paid = 0;

    long months_skipped = 0;
    long removed_payments_months = 0;
    double total_unpaid_penalty = 0.0d;

    double monthly_payment_getter = 0;

    // TODO payment frequency, do all logics here, add payment extension
    // TODO collect collateral if loan not paid past maturity date
    // TODO change loan_plan : interest and penalty to percentage
    // TODO add loan_plan : payment frequency : daily, monthly, annually
    // TODO make an invoice for each payment done, change calculator included
    // TODO add security more by putting confirmations every after tasks
    // TODO make statistics
    // TODO add forget password
    // TODO think of more features to add after the important bits are done

    private void loan_next_logic_monthly() {
        total_months = 0;

        LocalDate first_due_date = LocalDate.of(loan.getRelease_date().getYear(),
                loan.getRelease_date().getMonthValue(), loan.getDue());
        first_due_date = first_due_date.plusMonths(1);
        next_due_date = LocalDate.of(loan.getRelease_date().getYear(),
                loan.getRelease_date().getMonthValue(), loan.getDue());
        next_due_date = next_due_date.plusMonths(1);

        total_months = ChronoUnit.MONTHS.between(first_due_date, loan.getMaturity_date());

        double interest_val = (loan.getPrincipal() / total_months) * loan.getInterest();
        double penalty_val = (loan.getPrincipal() / total_months) * loan.getPenalty();
        double monthly_payment = loan.getPrincipal() / total_months + interest_val;
        double penalty_payment = loan.getPrincipal() / total_months + penalty_val;

        monthly_payment_getter = monthly_payment;

        days_skipped = ChronoUnit.DAYS.between(loan.getNextDueDate(), LocalDate.now());
        total_days = ChronoUnit.DAYS.between(loan.getNextDueDate(), loan.getMaturity_date());
        months_skipped = ChronoUnit.MONTHS.between(loan.getNextDueDate(), LocalDate.now());

        boolean payment_exist = !paymentList.isEmpty();

        if (months_skipped <= 0) {
            loan.getTotalUnpaidProperty().set(penalty_payment * 0);
        } else {
            loan.getTotalUnpaidProperty().set(penalty_payment * months_skipped);
        }

        YearMonth yearMonth_next_due = YearMonth.of(next_due_date.getYear(), next_due_date.getMonthValue());

        loan.setBalance(loan.getBalance() - loan.getPaid());

        loan_total_unpaid_label.setText(format.format(loan.getTotalUnpaidProperty().get()));

        if (YearMonth.of(loan.getNextDueDate().getYear(), loan.getNextDueDate().getMonthValue()).compareTo(
                YearMonth.of(loan.getMaturity_date().getYear(), loan.getMaturity_date().getMonthValue())) > 0) {
            loan.setStatus(LoanStatus.PAID);
        }

        if (!payment_exist) {
            if (days_skipped > total_days) {
                loan_next_due_label.setText("Past Maturity Date");
                loan_next_amount_label.setText("Seize any collaterals or Take legal action");
                return;
            }

            if (LocalDate.now().isBefore(loan.getNextDueDate()) || LocalDate.now().isEqual(loan.getNextDueDate())) {
                loan.setNextPayment(monthly_payment);
                loan_next_due_label.setText(DateUtil.localizeDate(loan.getNextDueDate()));
                loan_next_amount_label.setText(format.format(loan.getNextPayment()));
                next_due_err.setVisible(false);
                next_amount_err.setVisible(false);
                return;
            }
            next_due_err.setVisible(true);
            next_amount_err.setVisible(true);
            if (LocalDate.now().isAfter(loan.getNextDueDate())) {
                loan.setNextPayment(penalty_payment);
                loan_next_due_label.setText(DateUtil.localizeDate(loan.getNextDueDate()));
                loan_next_amount_label.setText(format.format(loan.getNextPayment()));
                return;
            }
            return;
        }
        paymentList.forEach(pay -> {
            if (YearMonth.of(pay.getPaymentDate().getYear(), pay.getPaymentDate().getMonthValue())
                    .compareTo(yearMonth_next_due) == 0) {

                if (days_skipped > total_days) {
                    loan_next_due_label.setText("Past Maturity Date");
                    loan_next_amount_label.setText("Seize any collaterals or Take legal action");
                    return;
                }

                if (LocalDate.now().isBefore(loan.getNextDueDate()) || LocalDate.now().isEqual(loan.getNextDueDate())) {
                    loan.setNextPayment(monthly_payment);
                    loan_next_due_label.setText(DateUtil.localizeDate(loan.getNextDueDate()));
                    loan_next_amount_label.setText(format.format(loan.getNextPayment()));
                    next_due_err.setVisible(false);
                    next_amount_err.setVisible(false);
                    return;
                }
                next_due_err.setVisible(true);
                next_amount_err.setVisible(true);
                if (LocalDate.now().isAfter(loan.getNextDueDate())) {
                    loan.setNextPayment(penalty_payment);
                    loan_next_due_label.setText(DateUtil.localizeDate(loan.getNextDueDate()));
                    loan_next_amount_label.setText(format.format(loan.getNextPayment()));
                    return;
                }
            }
        });
    }

    // GETTERS AND SETTERS
    public ObservableList<Loan> getLoanObservableList() {
        return loanObservableList;
    }

    public void setLoan(Loan val) {
        this.loan = val;

        save_loan();
    }

    public String getTotalDueAmount() {
        return String.format("%.2f", loan.getNextPayment());
    }

    public LocalDate getNextDueDate() {
        return next_due_date;
    }

    public LocalDate setNextDueDate(long val) {
        return next_due_date = next_due_date.plusMonths(val);
    }

    public double getMonthlyPayment() {
        return this.monthly_payment_getter;
    }
}
