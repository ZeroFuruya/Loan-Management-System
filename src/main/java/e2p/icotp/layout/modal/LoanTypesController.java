package e2p.icotp.layout.modal;

import org.kordamp.ikonli.javafx.FontIcon;

import e2p.icotp.App;
import e2p.icotp.service.loader.ModalLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class LoanTypesController {
    @FXML
    private TextField loanName;
    @FXML
    private TextArea description;

    @FXML
    private FontIcon loanName_icon;
    @FXML
    private FontIcon description_icon;


    @FXML
    private Tooltip loanNameTT;
    @FXML
    private Tooltip descriptionTT;

    @FXML
    private Button save;
    @FXML
    private Button cancel;

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
    }
}
