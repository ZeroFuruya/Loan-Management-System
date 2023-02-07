package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.Collateral;
import e2p.icotp.model.Loan;
import e2p.icotp.model.LoanPlan;
import e2p.icotp.model.Loaner;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollateralDAO {
    // Collateral Masterlist
    public static ObservableList<Collateral> getMasterlist() throws SQLException {
        ObservableList<Collateral> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("collateral");
        while (crs.next()) {
            masterlist.add(collateralData(crs));
        }
        return masterlist;
    }

    // Collateral By ID
    public static Collateral getCollateralByID(int id) throws SQLException {
        SQLParam idParam = new SQLParam(Types.INTEGER, "loaner_id", id);
        CachedRowSet crs = SQLCommand.selectByID("collateral", idParam);
        return crs.next() ? collateralData(crs) : null;
    }

    // Collateral DATA
    private static Collateral collateralData(CachedRowSet crs) throws SQLException {

        int loaner_id = crs.getInt("loaner_id");
        Loaner loaner;
        if (loaner_id <= 0) {
            loaner = new Loaner();
        } else {
            loaner = LoanerDAO.getLoanerByID(loaner_id);
        }
        int loan_id = crs.getInt("loan_id");
        Loan loan;
        if (loan_id <= 0) {
            loan = new Loan();
        } else {
            loan = PaymentDAO.getLoanById(loan_id);
        }
        int collateral_id = crs.getInt("collateral_id");
        int plan_id = crs.getInt("plan_id");
        LoanPlan loan_plan;
        if (plan_id <= 0) {
            loan_plan = new LoanPlan();
        } else {
            loan_plan = LoanPlanDAO.getLoanPlanByID(plan_id);
        }
        String collateral = crs.getString("collateral");
        String status = crs.getString("status");

        return new Collateral(loaner, loan, collateral_id, loan_plan, collateral, status);
    }

    // INSERT
    public static void insert(Collateral collateral) {
        ArrayList<SQLParam> params = parameters(collateral);
        SQLCommand.insert("collateral", params);
    }

    // UPDATE
    public static void updateByID(Collateral collateral, int id) {
        SQLParam idParam = new SQLParam(Types.INTEGER, "collateral_id", id);
        ArrayList<SQLParam> params = parameters(collateral);
        SQLCommand.updateById("collateral", params, idParam);
    }

    public static void update(Collateral collateral) {
        updateByID(collateral, collateral.getCollateralId_property().get());
    }

    // REMOVE
    public static void remove(int loaner_id) {
        SQLParam idParam = new SQLParam(Types.INTEGER, "collateral_id", loaner_id);
        SQLCommand.deleteById("collateral", idParam);
    }

    // Collateral SQL
    private static ArrayList<SQLParam> parameters(Collateral collateral) {
        ArrayList<SQLParam> params = new ArrayList<>();

        // int loaner_id
        params.add(new SQLParam(Types.BIGINT, "loaner_id", collateral.getLoaner_id().getLoaner_id()));
        // int loan_id
        params.add(new SQLParam(Types.INTEGER, "loan_id", collateral.getLoan_id().getLoan_id()));
        // int loan_id
        params.add(new SQLParam(Types.INTEGER, "collateral_id", collateral.getCollateral_id()));
        // int loan_id
        params.add(new SQLParam(Types.INTEGER, "plan_id", collateral.getPlan_id().getIdProperty().get()));
        // String collateral
        params.add(new SQLParam(Types.VARCHAR, "collateral", collateral.getCollateral()));
        // String collateral
        params.add(new SQLParam(Types.VARCHAR, "status", collateral.getStatusProperty().get()));

        return params;
    }
}
