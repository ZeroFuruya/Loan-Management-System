package e2p.icotp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import e2p.icotp.layout.accounts.Login;
import e2p.icotp.layout.accounts.SignUp;
import e2p.icotp.model.Collateral;
import e2p.icotp.service.server.dao.*;
import e2p.icotp.util.FileUtil;
import e2p.icotp.model.Loan;
import e2p.icotp.model.LoanPlan;
import e2p.icotp.model.LoanType;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.service.loader.AppLoader;

/**
 * JavaFX App
 */
public class App extends Application {
    private StackPane mainScreen;
    private Stage mainStage;

    private ObservableList<Collateral> collateralCache;
    private ObservableList<Loan> loanCache;
    private ObservableList<Loaner> loanerCache;
    private ObservableList<Payment> paymentCache;
    private ObservableList<LoanType> loan_typeCache;
    private ObservableList<LoanPlan> loan_planCache;

    // Accounts
    private ObservableList<SignUp> signUpList = FXCollections.observableArrayList();
    private ObservableList<Login> loginList = FXCollections.observableArrayList();
    // STASH UPDATE COMMENT

    // Boolean

    @Override
    public void start(Stage stage) throws Exception {
        this.mainStage = stage;
        initialize_signUp();
        initialize_login();
        FileUtil.init_appdata();
        load_cache();
        initializa_main();
    }

    private void load_cache() throws SQLException {
        loanCache = LoanDAO.getMasterlist();
        loanerCache = LoanerDAO.getMasterlist();
        paymentCache = PaymentDAO.getMasterlist();
        collateralCache = CollateralDAO.getMasterlist();
        loan_typeCache = LoanTypeDAO.getMasterlist();
        loan_planCache = LoanPlanDAO.getMasterlist();

        System.out.println("======================================");
        loan_planCache.forEach(loan -> {
            System.out.println(loan.getIdProperty().get());
            System.out.println(loan.getTypeProperty().get().getName().get());
        });
    }
    private void initialize_signUp(){
        signUpList.add(new SignUp("123", "123", "123"));
    }

    private void initialize_login(){
        loginList.add(new Login("aloe", "123"));
    }

    public void initializa_main() throws IOException {
        AppLoader.load_main(this, mainStage);
    }

    public void setMainScreen(StackPane screen) {
        mainScreen = screen;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public StackPane getMainScreen() {
        return this.mainScreen;
    }

    // MASTERLIST

    public ObservableList<Collateral> collateralMasterlist() {
        return collateralCache;
    }

    public ObservableList<Loan> loanMasterList() {
        return loanCache;
    }

    public ObservableList<Loaner> loanerMasterlist() {
        return loanerCache;
    }

    public ObservableList<Payment> paymentMasterlist() {
        return paymentCache;
    }

    public ObservableList<LoanType> loanTypeMasterlist() {
        return loan_typeCache;
    }

    public ObservableList<LoanPlan> loanPlanMasterlist() {
        return loan_planCache;
    }

    // Accounts
    public ObservableList<SignUp> getSignUpList(){
        return this.signUpList;
    }
    public ObservableList<Login> getLoginList(){
        return this.loginList;
    }

    
    public static void main(String[] args) {
        launch();
    }

}