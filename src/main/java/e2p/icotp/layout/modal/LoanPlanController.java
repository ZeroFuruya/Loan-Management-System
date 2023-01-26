package e2p.icotp.layout.modal;



import e2p.icotp.App;
import e2p.icotp.model.Enums.Status;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class LoanPlanController {
    @FXML
    private TextField installment;

    @FXML
    private ComboBox<Status> loanStatus;

    @FXML
    private TextField interest;

    @FXML
    private TextField repayment;
    
    @FXML
    private TextField monthlyPenalty;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    // ICONS

    @FXML
    private Label installment_icon;
    @FXML
    private Label interest_icon;
    @FXML
    private Label repayment_icon;
    @FXML
    private Label monthlyPenalty_icon;

    // Tooltips
    @FXML
    private Tooltip installmentTT;
    @FXML
    private Tooltip interestTT;
    @FXML
    private Tooltip repaymentTT;
    @FXML
    private Tooltip monthlyPenaltyTT;

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
        installment_icon.visibleProperty().bind(installment.textProperty().isEmpty());
        interest_icon.visibleProperty().bind(interest.textProperty().isEmpty());
        repayment_icon.visibleProperty().bind(repayment.textProperty().isEmpty());
        monthlyPenalty_icon.visibleProperty().bind(monthlyPenalty.textProperty().isEmpty());
    }
}
