package e2p.icotp.layout;

import java.time.LocalDate;

import e2p.icotp.App;
import e2p.icotp.model.Loan;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    HBox toggle_btn_container;

    // TOGGLE BUTTONS
    @FXML
    ToggleButton home_button;
    @FXML
    ToggleButton loaners_button;
    @FXML
    ToggleButton loans_button;
    @FXML
    ToggleButton payments_button;
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

    // APP -------------------------------------

    App app;

    // LISTS -----------------------------------

    FilteredList<Loaner> loanerList;

    public void load(App app) {
        this.app = app;

        // SETS HOME AS DEFAULT
        home_button.setSelected(true);

        init_tables();
        init_bindings();
        init_listeners();
        init_anims();
    }

    private void init_tables() {
        this.loanerList = new FilteredList<>(app.loanerMasterlist(), p -> true);

        // LOANER TABLE =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        loaner_id.setCellValueFactory(loaner -> {
            return loaner.getValue().getLoaner_idProperty().asObject();
        });
        loaner_name.setCellValueFactory(loaner -> {
            return loaner.getValue().getNameProperty();
        });
        loanerTable.setItems(loanerList);
    }

    private void init_listeners() {
        // HOME
        home_button.selectedProperty().addListener((o, ov, nv) -> {
            if (home_button.isSelected()) {
                loaners_button.selectedProperty().set(false);
                loans_button.selectedProperty().set(false);
                payments_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // LOANER
        loaners_button.selectedProperty().addListener((o, ov, nv) -> {
            if (loaners_button.isSelected()) {
                home_button.selectedProperty().set(false);
                loans_button.selectedProperty().set(false);
                payments_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // LOAN
        loans_button.selectedProperty().addListener((o, ov, nv) -> {
            if (loans_button.isSelected()) {
                home_button.selectedProperty().set(false);
                loaners_button.selectedProperty().set(false);
                payments_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // PAYMENT
        payments_button.selectedProperty().addListener((o, ov, nv) -> {
            if (payments_button.isSelected()) {
                home_button.selectedProperty().set(false);
                loaners_button.selectedProperty().set(false);
                loans_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // TYPE
        types_button.selectedProperty().addListener((o, ov, nv) -> {
            if (types_button.isSelected()) {
                home_button.selectedProperty().set(false);
                loaners_button.selectedProperty().set(false);
                loans_button.selectedProperty().set(false);
                payments_button.selectedProperty().set(false);
                plans_button.selectedProperty().set(false);
            }
        });
        // PLAN
        plans_button.selectedProperty().addListener((o, ov, nv) -> {
            if (plans_button.isSelected()) {
                home_button.selectedProperty().set(false);
                loaners_button.selectedProperty().set(false);
                loans_button.selectedProperty().set(false);
                payments_button.selectedProperty().set(false);
                types_button.selectedProperty().set(false);
            }
        });
    }

    private void init_bindings() {

        // LOANER TABLE WIDTHS
        loaner_id.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.140));
        loaner_name.prefWidthProperty().bind(loanerTable.widthProperty().multiply(0.20));

        // HOME
        home_box.visibleProperty().bind(home_button.selectedProperty());
        home_button.disableProperty().bind(home_button.selectedProperty());
        // LOANERS
        loaners_box.visibleProperty().bind(loaners_button.selectedProperty());
        loaners_button.disableProperty().bind(loaners_button.selectedProperty());
        // LOANS
        loans_box.visibleProperty().bind(loans_button.selectedProperty());
        loans_button.disableProperty().bind(loans_button.selectedProperty());
        // PAYMENTS
        payments_box.visibleProperty().bind(payments_button.selectedProperty());
        payments_button.disableProperty().bind(payments_button.selectedProperty());
        // TYPES
        types_box.visibleProperty().bind(types_button.selectedProperty());
        types_button.disableProperty().bind(types_button.selectedProperty());
        // PLANS
        plans_box.visibleProperty().bind(plans_button.selectedProperty());
        plans_button.disableProperty().bind(plans_button.selectedProperty());

    }

    private void init_anims() {

    }
}
