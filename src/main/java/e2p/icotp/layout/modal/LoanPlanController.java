package e2p.icotp.layout.modal;

import com.itextpdf.text.pdf.TextField;

import e2p.icotp.model.Enums.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class LoanPlanController {
    @FXML
    private TextField installment;

    @FXML
    private ComboBox<Status> loanStatus;

    @FXML
    private TextField interest;

    @FXML
    private TextField repayment;

    @FXML TextField annuity;

    @FXML
    private Button save;
    @FXML
    private Button cancel;
}
