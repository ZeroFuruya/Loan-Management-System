package e2p.icotp.layout.modal;



import e2p.icotp.App;
import e2p.icotp.model.Enums.Status;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class LoanController {
    @FXML
    private TextField loan_id;

    @FXML
    private TextField loaner_id;

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
    private TextField paid;

    @FXML
    private TextField balance;

    @FXML
    private ComboBox<Status> status;

    // Icons

    @FXML
    private Label loanId_icon; 
    @FXML
    private Label loanerId_icon;
    @FXML
    private Label term_icon;
    @FXML
    private Label releaseDate_icon;
    @FXML
    private Label maturityDate_icon;
    @FXML
    private Label principal_icon;
    @FXML
    private Label interest_icon;
    @FXML
    private Label penalty_icon;
    @FXML
    private Label due_icon;
    @FXML
    private Label paid_icon;
    @FXML
    private Label balance_icon;

    // ToolTips

    @FXML
    private Tooltip loanIdTT;
    @FXML
    private Tooltip loanerIdTT;
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
    private Tooltip paidTT;
    @FXML
    private Tooltip balanceTT;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private App app;

    @FXML
    private void handle_cancel(){
        ModalLoader.modal_close(app);
    }
    @FXML
    private void handle_save(){
        
    }


    public void load(App app){
        this.app = app;
        load_bindings();
    }

    private void load_bindings(){
        loanerId_icon.visibleProperty().bind(loaner_id.textProperty().isEmpty());
        loanId_icon.visibleProperty().bind(loan_id.textProperty().isEmpty());
        term_icon.visibleProperty().bind(term.textProperty().isEmpty());
        releaseDate_icon.visibleProperty().bind(release_date.promptTextProperty().isEmpty());
        maturityDate_icon.visibleProperty().bind(maturity_date.promptTextProperty().isEmpty());
        principal_icon.visibleProperty().bind(principal.textProperty().isEmpty());
        interest_icon.visibleProperty().bind(interest.textProperty().isEmpty());
        penalty_icon.visibleProperty().bind(penalty.textProperty().isEmpty());
        due_icon.visibleProperty().bind(due.textProperty().isEmpty());
        paid_icon.visibleProperty().bind(paid.textProperty().isEmpty());
        balance_icon.visibleProperty().bind(balance.textProperty().isEmpty());
    }
}
