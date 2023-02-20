package e2p.icotp.layout.modal;

import e2p.icotp.App;
import e2p.icotp.model.Collateral;
import e2p.icotp.model.Loan;
import e2p.icotp.model.Loaner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class CollateralController {
    @FXML
    private TextField loanerId;
    @FXML
    private TextField loanId;
    @FXML
    private TextField collateralTF;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    // Label
    @FXML
    private HBox loanerIdLabel;
    @FXML
    private HBox loanIdLabel;
    @FXML
    private HBox collateralLabel;

    // Tooltip
    @FXML
    private Tooltip loanerIdTT;
    @FXML
    private Tooltip loanIdTT;
    @FXML
    private Tooltip collateralTT;

    private App app;
    private Collateral collateral;
    private Loaner loaner;
    private Loan loan;

    public void load(App app) {
        this.app = app;
        init_bindings();
        modify_collateral_listener();
    }

    private void init_bindings() {
        loanerIdLabel.visibleProperty().bind(loanerId.textProperty().isEmpty());
        loanIdLabel.visibleProperty().bind(loanId.textProperty().isEmpty());
        collateralLabel.visibleProperty().bind(collateralTF.textProperty().isEmpty());

        save.disableProperty().bind(loanerIdLabel.visibleProperty().or(loanIdLabel.visibleProperty())
                .or(collateralLabel.visibleProperty()));
    }

    private void modify_collateral_listener(){
        collateral.setCollateral_id(Integer.parseInt(collateralTF.textProperty().get()));
        loan.setLoan_id(Integer.parseInt(loanId.textProperty().get()));
        loaner.setLoaner_id(Long.parseLong(loanerId.textProperty().get()));
    }

    // private Long long_parser(String val) {
    //     return Long.parseLong(val);
    // }


}
