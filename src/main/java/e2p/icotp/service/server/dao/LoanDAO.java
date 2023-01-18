package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.Loan;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanDAO {
    public static ObservableList<Loan> getMasterlist() throws SQLException {
        ObservableList<Loan> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("loans");

        while (crs.next()) {
            masterlist.add(loanData(crs));
        }
        return masterlist;
    }

    public static Loan loanData(CachedRowSet crs) throws SQLException {
        int loan_id = (crs.getInt("loan_id"));
        int loaner_id = (crs.getInt("loaner_id"));
        LocalDate release_date = (crs.getDate("release_date").toLocalDate());
        int term = (crs.getInt("term"));
        LocalDate maturity_date = (crs.getDate("maturity_date").toLocalDate());
        double principal = (crs.getDouble("principal"));
        double interest = (crs.getDouble("interest"));
        double penalty = (crs.getDouble("penalty"));
        int due = (crs.getInt("due"));
        double paid = (crs.getDouble("paid"));
        double balance = (crs.getDouble("balance"));
        String status = (crs.getString("status"));

        return new Loan(loan_id, loaner_id, release_date, term, maturity_date, principal, interest, penalty, due, paid,
                balance, status);
    }

    public static ArrayList<SQLParam> parameters(Loan loan) {
        ArrayList<SQLParam> params = new ArrayList<>();

        // LOAN_ID
        params.add(new SQLParam(Types.BIGINT, "loan_id", loan.getLoan_id()));

        // LOANER ID
        params.add(new SQLParam(Types.BIGINT, "loaner_id", loan.getLoaner_id()));

        // RELEASE DATE
        params.add(new SQLParam(Types.DATE, "release_date", loan.getRelease_date()));

        // TERM
        params.add(new SQLParam(Types.BIGINT, "term", loan.getTerm()));

        // MATURITY
        params.add(new SQLParam(Types.DATE, "maturity_date", loan.getMaturity_date()));

        // PRINCIPAL
        params.add(new SQLParam(Types.DOUBLE, "principal", loan.getPrincipal()));

        // INTEREST
        params.add(new SQLParam(Types.DOUBLE, "interest", loan.getInterest()));

        // PENALTY
        params.add(new SQLParam(Types.DOUBLE, "penalty", loan.getPenalty()));

        // DUE
        params.add(new SQLParam(Types.BIGINT, "due", loan.getDue()));

        // PAID
        params.add(new SQLParam(Types.DOUBLE, "paid", loan.getPaid()));

        // BALANCE
        params.add(new SQLParam(Types.DOUBLE, "balance", loan.getBalance()));

        // STATUS
        params.add(new SQLParam(Types.VARCHAR, "status", loan.getStatus()));

        return params;
    }

    public static void insert(Loan loan) {
        ArrayList<SQLParam> params = parameters(loan);
        SQLCommand.insert("loans", params);
    }

    public static void update(Loan loan) {
        updateById(loan, loan.getLoan_id());
    }

    public static void remove(Loan loan) {
        SQLCommand.deleteById("loans",
                new SQLParam(Types.BIGINT, "degreeID", loan.getLoan_id()));
    }

    public static void updateById(Loan loan, int target_id) {
        SQLParam idParam = new SQLParam(Types.BIGINT, "loan_id", target_id);
        ArrayList<SQLParam> params = parameters(loan);
        SQLCommand.updateById("loans", params, idParam);

    }
}
