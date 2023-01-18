package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.Loan;
import e2p.icotp.service.server.core.SQLCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanDAO {
    public static ObservableList<Loan> getMasterlist() throws SQLException{
        ObservableList<Loan> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs  = SQLCommand.selectAll("loans");

        while(crs.next()){
            masterlist.add(loanerData(crs));
        }
        return masterlist;
    }

    public static Loan loanerData(CachedRowSet crs) throws SQLException{
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

        return new Loan(loan_id, loaner_id, release_date, term, maturity_date, principal, interest, penalty, due, paid, balance, status);
    }
}
