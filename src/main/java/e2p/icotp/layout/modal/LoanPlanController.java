package e2p.icotp.layout.modal;

import e2p.icotp.App;
import e2p.icotp.model.Enums.LoanStatus;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class LoanPlanController {
    @FXML
    private TextField installment;

    @FXML
    private ComboBox<LoanStatus> loanStatus;

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
    private HBox installment_icon;
    @FXML
    private HBox interest_icon;
    @FXML
    private HBox repayment_icon;
    @FXML
    private HBox monthlyPenalty_icon;

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
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() {

    }

    public void load(App app) {
        this.app = app;
        load_bindings();
    }

    private void load_bindings() {
        installment_icon.visibleProperty().bind(installment.textProperty().isEmpty());
        interest_icon.visibleProperty().bind(interest.textProperty().isEmpty());
        repayment_icon.visibleProperty().bind(repayment.textProperty().isEmpty());
        monthlyPenalty_icon.visibleProperty().bind(monthlyPenalty.textProperty().isEmpty());

        save.disableProperty().bind(installment_icon.visibleProperty().or(interest_icon.visibleProperty())
                .or(repayment_icon.visibleProperty()).or(monthlyPenalty_icon.visibleProperty()));
    }
}
