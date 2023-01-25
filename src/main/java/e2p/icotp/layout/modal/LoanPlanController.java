package e2p.icotp.layout.modal;


import org.kordamp.ikonli.javafx.FontIcon;

import e2p.icotp.App;
import e2p.icotp.model.Enums.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private FontIcon installment_icon;
    @FXML
    private FontIcon interest_icon;
    @FXML
    private FontIcon repayment_icon;
    @FXML
    private FontIcon monthlyPenalty_icon;

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

    public void load(App app){
        this.app = app;
    }
}
