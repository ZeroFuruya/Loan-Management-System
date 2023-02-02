package e2p.icotp.layout;

import java.io.IOException;
import java.text.NumberFormat;

import e2p.icotp.App;
import e2p.icotp.layout.factory.TypesFactory;
import e2p.icotp.model.Loan;
import e2p.icotp.model.LoanPlan;
import e2p.icotp.model.LoanType;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.model.Enums.Status;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.util.custom.DateUtil;
import e2p.icotp.util.custom.LoanPlanListCell;
import e2p.icotp.util.custom.LoanTypeListCell;
import e2p.icotp.util.custom.LoanTypeStringConverter;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

    // STACKPANE
    @FXML
    StackPane motherPane;

    // VBOX (Child of Mother StackPane)
    @FXML
    VBox home_box;
    @FXML
    VBox loaners_box;
    @FXML
    VBox loans_box;
    @FXML
    VBox payments_box;
    @FXML
    VBox types_box;
    @FXML
    VBox plans_box;

    // VBOX
    @FXML
    VBox toggle_btn_container;
    @FXML
    VBox loan_information_container;
    @FXML
    VBox payment_information_container;

    // HBOX
    @FXML
    HBox loaner_information_container;
    @FXML
    HBox loaner_name_container;

    // TOGGLE BUTTONS
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

    // TABLEVIEWS
    @FXML
    TableView<Loaner> loanerTable;
    @FXML
    TableView<Loan> loanTable;
    @FXML
    TableView<Payment> paymentTable;
    @FXML
    TableView<LoanPlan> loanPlanTable;

    // TABLE COLS - Loaner
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
    Button loaner_edit_button;
    @FXML
    Button loaner_add_button;
    @FXML
    Button loaner_remove_button;

    @FXML
    TextField loaner_search;

    // TABLE COLS - Loan
    @FXML
    TableColumn<Loan, Integer> loan_id;
    @FXML
    TableColumn<Loan, String> loan_loaner_name;

    @FXML
    Label loan_id_label;
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
    Button loan_edit_button;
    @FXML
    Button loan_add_button;
    @FXML
    Button loan_remove_button;

    @FXML
    TextField loan_search;

    @FXML
    ImageView status_image;

    // TABLE COLS - Payment
    @FXML
    TableColumn<Payment, Long> payment_id;
    @FXML
    TableColumn<Payment, Integer> payment_loan_id;
    @FXML
    TableColumn<Payment, String> payment_loaner_name;

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

    // TABLE COLS - Plan
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
    ToggleButton edit_toggle;
    BooleanProperty plan_isEdit = new SimpleBooleanProperty(false);

    @FXML
    TextField plan_id_tf;
    @FXML
    TextField plan_term_tf;
    @FXML
    TextField plan_interest_tf;
    @FXML
    TextField plan_penalty_tf;
    @FXML
    TextField plan_search;
    @FXML
    ComboBox<LoanType> plan_type_cbox;

    @FXML
    Button plan_save_button;
    @FXML
    Button plan_remove_button;

    @FXML
    HBox plan_type_err;
    @FXML
    HBox plan_term_err;
    @FXML
    HBox plan_interest_err;
    @FXML
    HBox plan_penalty_err;

    // SCROLLPANE
    @FXML
    ScrollPane types_scroll_pane;
    @FXML
    VBox types_container;

    // APP -------------------------------------

    App app;

    // LISTS -----------------------------------

    FilteredList<Loaner> loanerList;
    FilteredList<Loan> loanList;
    FilteredList<Payment> paymentList;
    FilteredList<LoanType> loanTypeList;
    FilteredList<LoanPlan> loanPlanList;

    // OBSERVABLE LIST
    ObservableList<Loan> loanObservableList;
    ObservableList<Payment> paymentObservableList;

    // MODELS ----------------------------------
    Loaner og_loaner = new Loaner();
    Loaner loaner = new Loaner();
    Loan og_loan = new Loan();
    Loan loan = new Loan();
    Payment og_payment = new Payment();
    Payment payment = new Payment();
    LoanType og_loan_type = new LoanType();
    LoanType loan_type = new LoanType();
    LoanPlan og_loan_plan = new LoanPlan();
    LoanPlan loan_plan = new LoanPlan();

    NumberFormat format = NumberFormat.getInstance();

    // LOANER BUTTON HANDLES ---------------------------
    @FXML
    private void handle_loaner_edit() throws IOException {
        ModalLoader.load_loaner_update(app, loaner, true, this);
    }

    @FXML
    private void handle_loaner_add() throws IOException {
        ModalLoader.load_loaner_update(app, new Loaner(), false, this);
    }

    @FXML
    private void handle_loaner_remove() {
        app.loanerMasterlist().remove(og_loaner);
    }

    // LOAN BUTTON HANDLES -----------------------------
    @FXML
    private void handle_loan_edit() throws IOException {
        ModalLoader.load_loan_update(app, loan, true, this);
    }

    @FXML
    private void handle_loan_add() throws IOException {
        ModalLoader.load_loan_update(app, new Loan(), false, this);
    }

    @FXML
    private void handle_loan_remove() {
        app.loanMasterList().remove(og_loan);
    }

    public void load(App app) {
        this.app = app;
        format.setGroupingUsed(true);

        // SETS HOME AS DEFAULT
        home_button.setSelected(true);

        loaner_information_container.setVisible(false);
        loaner_name_container.setVisible(false);
        loan_information_container.setVisible(false);
        payment_information_container.setVisible(false);

        loanTypeList = new FilteredList<>(app.loanTypeMasterlist(), p -> true);

        init_tables();
        init_bindings();
        init_togbutton_listeners();
        init_table_listeners();
        _init_types();
        init_plans();
        init_anims();
    }

    private void init_tables() {
        this.loanerList = new FilteredList<>(app.loanerMasterlist(), p -> true);
        this.loanPlanList = new FilteredList<>(app.loanPlanMasterlist(), p -> true);

        this.loanObservableList = FXCollections.observableArrayList();
        this.paymentObservableList = FXCollections.observableArrayList();

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
        loan_loaner_name.setCellValueFactory(loan -> {
            return loan.getValue().getLoanerID_Property().get().getNameProperty();
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
        }
    }

    private void init_bindings() {

        // TABLE WIDTHS
        loaner_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.75));
        loan_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        loan_loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.75));
        payment_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        payment_loan_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.235));
        payment_loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.700));

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
                loan_information_container.setVisible(false);
                payment_information_container.setVisible(false);
            } else {
                load_empty_loan_table();
                refresh_loan_list();
                clear_payment_table();
                loaner_information_container.setVisible(false);
                loaner_name_container.setVisible(false);
                loan_information_container.setVisible(false);
                payment_information_container.setVisible(false);
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
                loan_information_container.setVisible(true);
                og_loan = nv;
                loan = og_loan;
                load_payment_table();
                refresh_payment_list();
                _init_loan_bindings();
            } else {
                loan_information_container.setVisible(false);
                payment_information_container.setVisible(false);
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
        translate.setNode(loans_box);
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

    // INDIVIDUAL FUNCTIONS ---------------------------------------------------
    private void _init_loaner_bindings() {
        loaner_name_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("%s", loaner.getName());
        }, loaner.getNameProperty()));
        loaner_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Loaner ID: %d", loaner.getLoaner_id());
        }, loaner.getLoaner_idProperty()));
        loaner_address_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Address: %s", loaner.getAddress());
        }, loaner.getAddressProperty()));
        loaner_phone_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Phone: %d", loaner.getPhone());
        }, loaner.getPhoneProperty()));
        loaner_birthdate_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Birthdate: %s", loaner.getBirthdate());
        }, loaner.getBirthdateProperty()));
        loaner_social_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Social Security: %d", loaner.getSocial_security());
        }, loaner.getSocial_securityProperty()));

        loaner_edit_button.disableProperty().bind(loanerTable.getSelectionModel().selectedItemProperty().isNull());
        loaner_remove_button.disableProperty().bind(loanerTable.getSelectionModel().selectedItemProperty().isNull());
    }

    private void _init_loan_bindings() {
        loan_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Loan ID: %d", loan.getLoan_id());
        }, loan.getLoanID_Property()));
        loan_release_date_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Release: %s", DateUtil.localizeDate(loan.getRelease_date()));
        }, loan.getRelease_date_Property()));
        loan_term_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Term: %d", loan.getTerm());
        }, loan.getTermProperty()));
        loan_maturity_date_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Maturity: %s", DateUtil.localizeDate(loan.getMaturity_date()));
        }, loan.getMaturity_date_Property()));
        loan_principal_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Principal: $%s", format.format(loan.getPrincipal()));
        }, loan.getPrincipalProperty()));
        loan_interest_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Interest: %.2f" + "%%", loan.getInterest());
        }, loan.getInterestProperty()));
        loan_penalty_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Penalty: %.2f" + "%%", loan.getPenalty());
        }, loan.getPenaltyProperty()));
        loan_due_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Due: Day %d of every month", loan.getDue());
        }, loan.getDueProperty()));
        loan_paid_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Paid: $%s", format.format(loan.getPaid()));
        }, loan.getPaidProperty()));
        loan_balance_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Balance: $%s", format.format(loan.getBalance()));
        }, loan.getBalanceProperty()));

        status_image_setter(loan.getStatus());

        loan_edit_button.disableProperty().bind(loanTable.getSelectionModel().selectedItemProperty().isNull());
        loan_remove_button.disableProperty().bind(loanTable.getSelectionModel().selectedItemProperty().isNull());
    }

    private void _init_payment_bindings() {
        payment_loan_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Loan ID: %d", payment.getLoan_id_Property().get().getLoan_id());
        }, payment.getLoan_id_Property().get().getLoanID_Property()));
        payment_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Payment ID: %d", payment.getPayment_id());
        }, payment.getPayment_id_Property()));
        payment_date_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Date: %s", DateUtil.localizeDate(payment.getPaymentDate()));
        }, payment.getPayment_date_Property()));
        payment_amount_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Amount: $%s", format.format(payment.getPayment_amount()));
        }, payment.getPayment_amount_Property()));

    }

    // CUSTOMS
    private void status_image_setter(String status) {
        switch (status) {
            case Status.APPLICATION:
                status_image.setImage(new Image(App.class.getResourceAsStream("assets/images/app_logo.png")));
                break;
            case Status.OPEN:
                status_image
                        .setImage(new Image(App.class.getResourceAsStream("assets/images/open2-removebg-preview.png")));
                break;
        }
    }

    private void _init_types() {
        types_scroll_pane.setStyle("-fx-background: #fbde44; -fx-border-color: #fbde44;");
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

    private void init_plans() {
        plan_init_cbox();
        loanPlanTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                og_loan_plan = nv;
                loan_plan = og_loan_plan;
                if (edit_toggle.isSelected()) {
                    plan_load_fields();
                } else {
                    plan_clear_fields();
                }
            } else {
                og_loan_plan = new LoanPlan();
                loan_plan = og_loan_plan;
            }
        });
        plan_search.textProperty().addListener((o, ov, nv) -> {
            loanPlanList.setPredicate(p -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }

                if (Integer.toString(p.getId().get()).toLowerCase().contains(nv.toLowerCase())) {
                    return true;
                }

                return p.getType().get().getName().get().toLowerCase().contains(nv.toLowerCase());
            });
        });
        edit_toggle.selectedProperty().addListener((o, ov, nv) -> {
            if (edit_toggle.isSelected()) {
                plan_load_fields();
                edit_toggle.textProperty().set("Edit");
            } else {
                plan_clear_fields();
                edit_toggle.textProperty().set("Add");
            }
        });
    }

    void plan_init_cbox() {
        plan_type_cbox.setCellFactory(cell -> new LoanTypeListCell());
        plan_type_cbox.setButtonCell(new LoanTypeListCell());
        plan_type_cbox.getItems().addAll(loanTypeList);
        plan_type_cbox.setConverter(new LoanTypeStringConverter());
        plan_type_cbox.setPromptText("Select Loan Type");
    }

    void plan_load_fields() {
        plan_id_tf.setText(loan_plan.getId().get() + "");
        plan_type_cbox.getSelectionModel().select(loan_plan.getType().get());
        plan_term_tf.setText(loan_plan.getTerm().get() + "");
        plan_interest_tf.setText(loan_plan.getInterest().get() + "");
        plan_penalty_tf.setText(loan_plan.getPenalty().get() + "");
    }

    void plan_clear_fields() {
        plan_id_tf.setText("0");
        plan_type_cbox.getSelectionModel().select(new LoanType());
        plan_term_tf.setText("0");
        plan_interest_tf.setText("0.0");
        plan_penalty_tf.setText("0.0");
    }

    // GETTERS AND SETTERS
    public ObservableList<Loan> getLoanObservableList() {
        return loanObservableList;
    }
}
