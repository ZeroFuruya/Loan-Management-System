package e2p.icotp.layout;

import java.io.IOException;
import java.text.NumberFormat;

import e2p.icotp.App;
import e2p.icotp.model.Loan;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.util.custom.DateUtil;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {

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

    // HBOX
    @FXML
    VBox toggle_btn_container;

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

    // APP -------------------------------------

    App app;

    // LISTS -----------------------------------

    FilteredList<Loaner> loanerList;
    FilteredList<Loan> loanList;
    FilteredList<Payment> paymentList;

    // MODELS ----------------------------------
    Loaner og_loaner;
    Loaner loaner;
    Loan og_loan;
    Loan loan;
    Payment og_payment;
    Payment payment;

    NumberFormat format = NumberFormat.getInstance();

    // LOANER BUTTON HANDLES ---------------------------
    @FXML
    private void handle_loaner_edit() throws IOException {
        ModalLoader.load_loaner_update(app);
    }

    @FXML
    private void handle_loaner_add() throws IOException {
        ModalLoader.load_loaner_update(app);
    }

    @FXML
    private void handle_loaner_remove() {
        app.loanerMasterlist().remove(og_loaner);
    }

    public void load(App app) {
        this.app = app;
        format.setGroupingUsed(true);

        // SETS HOME AS DEFAULT
        home_button.setSelected(true);

        init_tables();
        init_bindings();
        init_togbutton_listeners();
        init_table_listeners();
        init_anims();
    }

    private void init_tables() {
        this.loanerList = new FilteredList<>(app.loanerMasterlist(), p -> true);
        this.loanList = new FilteredList<>(app.loanMasterList(), p -> true);
        this.paymentList = new FilteredList<>(app.paymentMasterlist(), p -> true);

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
        loanTable.setItems(loanList);

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
        paymentTable.setItems(paymentList);
    }

    private void init_bindings() {

        // TABLE WIDTHS
        loaner_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.20));
        loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.8));
        loan_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.160));
        loan_loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.84));
        payment_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.300));
        payment_loan_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.230));
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
                og_loaner = nv;
                loaner = og_loaner;
                _init_loaner_bindings();
            } else {
                og_loaner = new Loaner();
                loaner = og_loaner;
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
                _init_loan_bindings();
            } else {
                og_loan = new Loan();
                loan = og_loan;
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
                og_payment = nv;
                payment = og_payment;
                _init_payment_bindings();
            } else {
                og_payment = new Payment();
                payment = og_payment;
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
            return String.format("Birthdate: %s", DateUtil.localizeDate(loaner.getBirthdate()));
        }, loaner.getBirthdateProperty()));
        loaner_social_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Social Security: %d", loaner.getSocial_security());
        }, loaner.getSocial_securityProperty()));

        // FILTERS
        loanList.setPredicate(p -> {
            return Long.toString(p.getLoaner_id().getLoaner_id())
                    .contains(Long.toString(loaner.getLoaner_id()).toLowerCase());
        });

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

        loan_edit_button.disableProperty().bind(loanTable.getSelectionModel().selectedItemProperty().isNull());
        loan_remove_button.disableProperty().bind(loanTable.getSelectionModel().selectedItemProperty().isNull());
    }

    private void _init_payment_bindings() {
        payment_loan_id_label.textProperty().bind(Bindings.createStringBinding(() -> {
            return String.format("Loan ID: %d", payment.getLoan_id().getLoan_id());
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

    private void _init_type_bindings() {
    }

    private void _init_plan_bindings() {
    }
}
