package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.layout.accounts.Account;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountDAO {
    public static ObservableList<Account> getMasterlist() throws SQLException {
        ObservableList<Account> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("accounts");

        while (crs.next()) {
            masterlist.add(accountData(crs));
        }
        return masterlist;
    }

    public static Account getAccountById(int id) throws SQLException {
        SQLParam idParam = new SQLParam(Types.INTEGER, "account_id", id);
        CachedRowSet crs = SQLCommand.selectByID("accounts", idParam);
        return crs.next() ? AccountDAO.accountData(crs) : null;
    }

    public static Account accountData(CachedRowSet crs) throws SQLException {

        int account_id = crs.getInt("account_id");
        String username = crs.getString("username");
        String password = crs.getString("password");
        String pass_key = crs.getString("pass_key");
        String email_address = crs.getString("email_address");

        return new Account(account_id, username, password, pass_key, email_address);
    }

    public static ArrayList<SQLParam> parameters(Account account) {
        ArrayList<SQLParam> params = new ArrayList<>();

        params.add(new SQLParam(Types.INTEGER, "account_id", account.getAccountId()));

        params.add(new SQLParam(Types.VARCHAR, "username", account.getUsername()));

        params.add(new SQLParam(Types.VARCHAR, "password", account.getPassword()));

        params.add(new SQLParam(Types.VARCHAR, "pass_key", account.getPassKey()));

        params.add(new SQLParam(Types.VARCHAR, "email_address", account.getEmail()));

        return params;
    }

    public static void insert(Account account) {
        ArrayList<SQLParam> params = parameters(account);
        SQLCommand.insert("accounts", params);
    }

    public static void update(Account account) {
        updateById(account, account.getAccountId());
    }

    public static void remove(Account account) {
        SQLCommand.deleteById("accounts", new SQLParam(Types.INTEGER, "account_id", account.getAccountId()));
    }

    public static void removeByLoanId(Account account) {
        SQLCommand.deleteById("accounts", new SQLParam(Types.INTEGER, "account_id",
                account.getAccountId()));
    }

    public static void updateById(Account account, long target_id) {
        SQLParam idParam = new SQLParam(Types.INTEGER, "account_id", target_id);
        ArrayList<SQLParam> params = parameters(account);
        SQLCommand.updateById("accounts", params, idParam);
    }
}
