package e2p.icotp.layout.modal;

import e2p.icotp.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class CollateralController {
    @FXML
    private TextField loanerId;
    @FXML
    private TextField loanId;
    @FXML
    private TextField collateral;

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

    public void load(App app) {
        this.app = app;
        init_bindings();
    }

    private void init_bindings() {
        loanerIdLabel.visibleProperty().bind(loanerId.textProperty().isEmpty());
        loanIdLabel.visibleProperty().bind(loanId.textProperty().isEmpty());
        collateralLabel.visibleProperty().bind(collateral.textProperty().isEmpty());

        save.disableProperty().bind(loanerIdLabel.visibleProperty().or(loanIdLabel.visibleProperty())
                .or(collateralLabel.visibleProperty()));
    }
}
