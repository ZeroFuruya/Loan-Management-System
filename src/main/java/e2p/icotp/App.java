package e2p.icotp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import e2p.icotp.model.Collateral;
import e2p.icotp.service.server.dao.*;
import e2p.icotp.util.custom.RandomIDGenerator;
import e2p.icotp.model.Loan;
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

    // STASH UPDATE COMMENT

    // Boolean

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        this.mainStage = stage;
        load_cache();
        initializa_main();
    }

    private void load_cache() throws SQLException {
        collateralCache = CollateralDAO.getMasterlist();
        loanCache = LoanDAO.getMasterlist();
        loanerCache = LoanerDAO.getMasterlist();
        paymentCache = PaymentDAO.getMasterlist();

        // loanerCache.forEach(loaner -> {
        // System.out.println(loaner.getBirthdate());
        // });
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

    public static void main(String[] args) {
        launch();
    }

}