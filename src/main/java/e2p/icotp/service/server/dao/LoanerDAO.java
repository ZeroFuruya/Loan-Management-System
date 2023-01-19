package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.Loaner;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanerDAO {
    // Loaner Masterlist
    public static ObservableList<Loaner> getMasterlist() throws SQLException {
        ObservableList<Loaner> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("loaners");
        while (crs.next()) {
            masterlist.add(loanerData(crs));
        }
        return masterlist;
    }

    // Loaner By ID
    public static Loaner getLoanerByID(int id) throws SQLException {
        SQLParam idParam = new SQLParam(Types.INTEGER, "loaner_id", id);
        CachedRowSet crs = SQLCommand.selectByID("loaners", idParam);
        return crs.next() ? loanerData(crs) : null;
    }

    // Loaner DATA
    private static Loaner loanerData(CachedRowSet crs) throws SQLException {

        int loaner_id = crs.getInt("loaner_id");
        String name = crs.getString("name");
        String address = crs.getString("address");
        String phone = crs.getString("phone");
        LocalDate birthdate = crs.getDate("birthdate").toLocalDate();
        int social_security = crs.getInt("social_security");

        return new Loaner(loaner_id, name, address, phone, birthdate, social_security);
    }

    // Insert
    public static void insert(Loaner loaner) {
        ArrayList<SQLParam> params = parameters(loaner);
        SQLCommand.insert("loaners", params);
    }

    // Update
    public static void updateByID(Loaner loaner, int id) {
        SQLParam idParam = new SQLParam(Types.INTEGER, "loaner_id", id);
        ArrayList<SQLParam> params = parameters(loaner);
        SQLCommand.updateById("loaners", params, idParam);
    }

    public static void update(Loaner loaner) {
        updateByID(loaner, loaner.getLoaner_id());
    }

    // Remove
    public static void remove(int loaner_id) {
        SQLParam idParam = new SQLParam(Types.BIGINT, "loaner_id", loaner_id);
        SQLCommand.deleteById("loaners", idParam);
    }

    // Loaner SQL
    private static ArrayList<SQLParam> parameters(Loaner loaner) {
        ArrayList<SQLParam> params = new ArrayList<>();

        // int loaner_id
        params.add(new SQLParam(Types.BIGINT, "loaner_id", loaner.getLoaner_id()));
        // varchar name
        params.add(new SQLParam(Types.VARCHAR, "name", loaner.getName()));
        // varchar address
        params.add(new SQLParam(Types.VARCHAR, "address", loaner.getAddress()));
        // int phone
        params.add(new SQLParam(Types.BIGINT, "phone", loaner.getPhone()));
        // date birthdate
        params.add(new SQLParam(Types.DATE, "birthdate", loaner.getBirthdate()));
        // int social_security
        params.add(new SQLParam(Types.BIGINT, "social_security", loaner.getSocial_security()));

        return params;
    }

}
