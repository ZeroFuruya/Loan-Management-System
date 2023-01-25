package e2p.icotp.layout.modal;


import org.kordamp.ikonli.javafx.FontIcon;

import e2p.icotp.App;
import e2p.icotp.model.Enums.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private FontIcon loanId_icon; 
    @FXML
    private FontIcon loanerId_icon;
    @FXML
    private FontIcon term_icon;
    @FXML
    private FontIcon releaseDate_icon;
    @FXML
    private FontIcon maturityDate_icon;
    @FXML
    private FontIcon principal_icon;
    @FXML
    private FontIcon interest_icon;
    @FXML
    private FontIcon penalty_icon;
    @FXML
    private FontIcon due_icon;
    @FXML
    private FontIcon paid_icon;
    @FXML
    private FontIcon balance_icon;

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


    public void load(App app){
        this.app = app;
    }
}
