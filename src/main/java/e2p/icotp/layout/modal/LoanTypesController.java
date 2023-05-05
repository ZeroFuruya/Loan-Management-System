package e2p.icotp.layout.modal;

import e2p.icotp.App;
import e2p.icotp.layout.MainController;
import e2p.icotp.model.LoanType;
import e2p.icotp.service.loader.ModalLoader;
import e2p.icotp.service.server.dao.LoanTypeDAO;
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
    boolean isEdit;
    MainController mc;

    @FXML
    private void handle_cancel() {
        ModalLoader.modal_close(app);
    }

    @FXML
    private void handle_save() {
        modify_fields();
        if (isEdit) {
            LoanTypeDAO.update(loan_type);
            app.loanTypeMasterlist().remove(og_loan_type);
            app.loanTypeMasterlist().add(loan_type);
        } else {
            LoanTypeDAO.insert(loan_type);
            app.loanTypeMasterlist().add(loan_type);
        }
        mc.refresh_types();
        ModalLoader.modal_close(app);
    }

    public void load(App app, LoanType loan_type, boolean isEdit, MainController mc) {
        this.app = app;
        this.og_loan_type = loan_type;
        this.loan_type = loan_type;
        this.isEdit = isEdit;
        this.mc = mc;
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

    private void modify_fields() {
        loan_type.getDesc().set(description.getText());
        loan_type.getName().set(loanName.getText());
        if (isEdit) {
            loan_type.getId().set(loan_type.getId().get());
        } else {
            loan_type.getId().set(app.loanTypeMasterlist().size() + 1);
        }

        System.out.println(loan_type.getDesc());
        System.out.println(loan_type.getId());
        System.out.println(loan_type.getName());
    }

}
