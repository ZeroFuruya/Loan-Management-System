package e2p.icotp.layout.modal;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.LoanType;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LoanTypesController {
    @FXML
    private TextField loanName;
    @FXML
    private TextArea description;

    @FXML
    private HBox loanName_icon;
    @FXML
    private HBox description_icon;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private App app;
    LoanType og_loan_type;
    LoanType loan_type;

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() {

    }

    public void load(App app, LoanType loan_type, boolean isEdit, MainController mc) {
        this.app = app;
        this.og_loan_type = loan_type;
        this.loan_type = loan_type;
        load_bindings();
        load_fields();
    }

    private void load_bindings() {
        loanName_icon.visibleProperty().bind(loanName.textProperty().isEmpty());
        description_icon.visibleProperty().bind(description.textProperty().isEmpty());

        save.disableProperty().bind(loanName_icon.visibleProperty().or(description_icon.visibleProperty()));
    }

    private void load_fields() {
        loanName.setText(og_loan_type.getName().get());
        description.setText(og_loan_type.getDesc().get());
    }

}
