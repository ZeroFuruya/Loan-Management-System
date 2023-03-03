package e2p.icotp.layout.modal;

import java.sql.SQLException;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.Collateral;
import e2p.icotp.model.Loan;
import e2p.icotp.model.Enums.CollateralStatus;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanDAO;
import e2p.icotp.service.server.dao.PaymentDAO;
import e2p.icotp.util.custom.RandomIDGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CollateralController {
    @FXML
    private TextField collateral_id_tf;
    @FXML
    private ComboBox<String> status_cbox;
    @FXML
    private TextArea collateral_detail_ta;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    // Label
    @FXML
    private HBox status_err;
    @FXML
    private HBox detail_err;

    private App app;
    private Collateral collateral;
    private Collateral og_collateral;
    private Loan loan;
    private boolean isEdit;
    private MainController mc;

    @FXML
    void handle_save() {
        modify_collateral_listener();
    }

    @FXML
    void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    public void load(App app, Loan loan, boolean isEdit, MainController mc, Collateral collateral) {
        this.app = app;
        this.loan = loan;
        this.isEdit = isEdit;
        this.mc = mc;
        this.collateral = collateral;
        this.og_collateral = collateral;
        init_bindings();
        init_cbox();
        init_fields();
    }

    private void init_bindings() {
        status_err.visibleProperty().bind(status_cbox.getSelectionModel().selectedItemProperty().isNull());
        detail_err.visibleProperty().bind(collateral_detail_ta.textProperty().isEmpty());

        save.disableProperty().bind(status_err.visibleProperty().or(detail_err.visibleProperty()));
    }

    void init_cbox() {
        status_cbox.getItems().add(CollateralStatus.NEUTRAL);
        status_cbox.getItems().add(CollateralStatus.SAFE);
        status_cbox.getItems().add(CollateralStatus.SEIZED);
        status_cbox.getItems().add(CollateralStatus.WARNING);
        if (isEdit) {
            status_cbox.getSelectionModel().select(collateral.getStatusProperty().get());
            return;
        }
        status_cbox.promptTextProperty().set("Select Status");
    }

    void init_fields() {
        if (isEdit) {
            collateral_id_tf.setText(collateral.getCollateral_id() + "");
            collateral_detail_ta.setText(collateral.getCollateral());
            status_cbox.getSelectionModel().select(collateral.getPlan_id().getType().getName());
            return;
        }
        generate_id();
        collateral_detail_ta.setText(loan.getLoanPlan().getType().get().getName().get());
    }

    private void modify_collateral_listener() {
    }

    long final_num = 0;

    private void generate_id() {
        String temp_val;
        String string_val = "";
        for (int i = 0; i < 10; i++) {
            int initial_num = RandomIDGenerator.getRandomNumber();
            temp_val = Integer.toString(initial_num);
            string_val = temp_val + string_val;
        }
        final_num = Long.parseLong(string_val);

        app.loanPlanMasterlist().forEach(loan_plan -> {
            if (loan_plan.getId().get() == final_num) {
                generate_id();
            } else {
                collateral_id_tf.textProperty().set(final_num + "");
            }
        });
    }

    private void notify_changes() throws SQLException {
        app.paymentMasterlist().setAll(PaymentDAO.getMasterlist());
        app.loanMasterList().setAll(LoanDAO.getMasterlist());
    }
}
