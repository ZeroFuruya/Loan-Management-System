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
    public static Loaner loanerData(CachedRowSet crs) throws SQLException {

        long loaner_id = crs.getLong("loaner_id");
        String name = crs.getString("name");
        String address = crs.getString("address");
        long phone = crs.getLong("phone");
        String email = crs.getString("email");
        LocalDate birthdate = crs.getDate("birthdate").toLocalDate();
        long social_security = crs.getLong("social_security");
        String civil_status = crs.getString("civil_status");
        String citizenship = crs.getString("citizenship");
        String place_of_birth = crs.getString("place_of_birth");

        return new Loaner(loaner_id, name, address, phone, email, birthdate, social_security, citizenship,
                place_of_birth,
                civil_status);
    }

    // Insert
    public static void insert(Loaner loaner) {
        ArrayList<SQLParam> params = parameters(loaner);
        SQLCommand.insert("loaners", params);
    }

    // Update
    public static void update(Loaner loaner) {
        updateById(loaner, loaner.getLoaner_id());
    }

    public static void updateById(Loaner loaner, long target_id) {
        SQLParam idParam = new SQLParam(Types.BIGINT, "loaner_id", target_id);
        ArrayList<SQLParam> params = parameters(loaner);
        SQLCommand.updateById("loaners", params, idParam);
    }

    // Remove
    public static void remove(Loaner loaner_id) {
        SQLParam idParam = new SQLParam(Types.BIGINT, "loaner_id", loaner_id.getLoaner_id());
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
        // varchar email
        params.add(new SQLParam(Types.VARCHAR, "email", loaner.getEmail()));
        // date birthdate
        params.add(new SQLParam(Types.DATE, "birthdate", loaner.getBirthdate()));
        // int social_security
        params.add(new SQLParam(Types.BIGINT, "social_security", loaner.getSocial_security()));
        // varchar civil status
        params.add(new SQLParam(Types.VARCHAR, "civil_status", loaner.getCivilStatus()));
        // varchar citizenship
        params.add(new SQLParam(Types.VARCHAR, "citizenship", loaner.getCitizenship()));
        // varchar place of birth
        params.add(new SQLParam(Types.VARCHAR, "place_of_birth", loaner.getPlaceOfBirth()));

        return params;
    }

}
