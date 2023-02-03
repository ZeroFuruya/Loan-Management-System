package e2p.icotp.layout.modal;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.Loan;
import e2p.icotp.model.Enums.LoanStatus;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private ComboBox<LoanStatus> status;
    @FXML
    private ComboBox<LoanStatus> loan_type;
    @FXML
    private ComboBox<LoanStatus> loan_plan;

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
    private Tooltip termTT;
    @FXML
    private Tooltip releaseDateTT;
    @FXML
    private Tooltip maturityDateTT;
    @FXML
    private Tooltip principalTT;
    @FXML
    private Tooltip interestTT;
    @FXML
    private Tooltip penaltyTT;
    @FXML
    private Tooltip dueTT;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private App app;
    private Loan og_loan;
    private Loan loan;
    private boolean isEdit;
    private MainController mc;

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() {

    }

    public void load(App app, Loan loan, boolean isEdit, MainController mc) {
        this.app = app;
        this.og_loan = loan;
        this.loan = loan;
        this.isEdit = isEdit;
        this.mc = mc;
        load_bindings();
    }

    private void load_bindings() {
        term_icon.visibleProperty().bind(term.textProperty().isEmpty());
        releaseDate_icon.visibleProperty().bind(release_date.valueProperty().isNull());
        maturityDate_icon.visibleProperty().bind(maturity_date.valueProperty().isNull());
        principal_icon.visibleProperty().bind(principal.textProperty().isEmpty());
        interest_icon.visibleProperty().bind(interest.textProperty().isEmpty());
        penalty_icon.visibleProperty().bind(penalty.textProperty().isEmpty());
        due_icon.visibleProperty().bind(due.textProperty().isEmpty());
    }
}
