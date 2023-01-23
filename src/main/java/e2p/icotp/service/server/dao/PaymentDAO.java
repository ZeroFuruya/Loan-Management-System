package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.Loan;
import e2p.icotp.model.Loaner;
import e2p.icotp.model.Payment;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PaymentDAO {
    public static ObservableList<Payment> getMasterlist() throws SQLException {
        ObservableList<Payment> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("payments");

        while (crs.next()) {
            masterlist.add(paymentData(crs));
        }
        return masterlist;
    }

    public static Loan getLoanById(int id) throws SQLException {
        SQLParam idParam = new SQLParam(Types.INTEGER, "loan_id", id);
        CachedRowSet crs = SQLCommand.selectByID("loans", idParam);
        return crs.next() ? LoanDAO.loanData(crs) : null;
    }

    public static Payment paymentData(CachedRowSet crs) throws SQLException {
        int payment_id = (crs.getInt("payment_id"));
        int loaner_id = (crs.getInt("loaner_id"));
        Loaner loaner;
        if (loaner_id <= 0) {
            loaner = new Loaner();
        } else {
            loaner = LoanerDAO.getLoanerByID(loaner_id);
        }
        int loan_id = (crs.getInt("loan_id"));
        Loan loan;
        if (loan_id <= 0) {
            loan = new Loan();
        } else {
            loan = getLoanById(loan_id);
        }
        LocalDate payment_date = (crs.getDate("payment_date").toLocalDate());
        double payment_amount = (crs.getDouble("payment_amount"));

        return new Payment(payment_id, loaner, loan, payment_date, payment_amount);
    }

    public static ArrayList<SQLParam> parameters(Payment payment) {
        ArrayList<SQLParam> params = new ArrayList<>();

        // PAYMENT ID
        params.add(new SQLParam(Types.BIGINT, "payment_id", payment.getPayment_id()));

        // LOANER ID
        params.add(new SQLParam(Types.BIGINT, "loaner_id", payment.getLoaner_id()));

        // LOAN ID
        params.add(new SQLParam(Types.BIGINT, "loan_id", payment.getLoan_id()));

        // PAYMENT DATE
        params.add(new SQLParam(Types.DATE, "payment_date", payment.getPaymentDate()));

        // PAYMENT AMOUNT
        params.add(new SQLParam(Types.DOUBLE, "payment_amount", payment.getPayment_amount()));

        return params;
    }

    public static void insert(Payment payment) {
        ArrayList<SQLParam> params = parameters(payment);
        SQLCommand.insert("payments", params);
    }

    public static void update(Payment payment) {
        updateById(payment, payment.getPayment_id());
    }

    public static void remove(Payment payment) {
        SQLCommand.deleteById("payments", new SQLParam(Types.BIGINT, "payment_id", payment.getPayment_id()));
    }

    public static void updateById(Payment payment, long target_id) {
        SQLParam idParam = new SQLParam(Types.BIGINT, "payment_id", target_id);
        ArrayList<SQLParam> params = parameters(payment);
        SQLCommand.updateById("payment_id", params, idParam);
    }
}
