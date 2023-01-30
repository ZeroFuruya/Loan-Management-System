package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.LoanType;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanTypeDAO {
    // LoanType Masterlist
    public static ObservableList<LoanType> getMasterlist() throws SQLException {
        ObservableList<LoanType> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("loan_types");
        while (crs.next()) {
            masterlist.add(loan_typeData(crs));
        }
        return masterlist;
    }

    // LoanType By ID
    public static LoanType getLoanTypeByID(int id) throws SQLException {
        SQLParam idParam = new SQLParam(Types.INTEGER, "type_id", id);
        CachedRowSet crs = SQLCommand.selectByID("loan_types", idParam);
        return crs.next() ? loan_typeData(crs) : null;
    }

    // LoanType DATA
    public static LoanType loan_typeData(CachedRowSet crs) throws SQLException {

        long type_id = crs.getLong("type_id");
        String type_name = crs.getString("type_name");
        String type_desc = crs.getString("type_desc");

        return new LoanType(type_id, type_name, type_desc);
    }

    // Insert
    public static void insert(LoanType loan_type) {
        ArrayList<SQLParam> params = parameters(loan_type);
        SQLCommand.insert("loan_types", params);
    }

    // Update
    public static void update(LoanType loan_type) {
        updateById(loan_type, loan_type.getId().get());
    }

    public static void updateById(LoanType loan_type, long target_id) {
        SQLParam idParam = new SQLParam(Types.BIGINT, "type_id", target_id);
        ArrayList<SQLParam> params = parameters(loan_type);
        SQLCommand.updateById("loan_types", params, idParam);
    }

    // Remove
    public static void remove(int type_id) {
        SQLParam idParam = new SQLParam(Types.BIGINT, "type_id", type_id);
        SQLCommand.deleteById("loan_types", idParam);
    }

    // LoanType SQL
    private static ArrayList<SQLParam> parameters(LoanType loan_type) {
        ArrayList<SQLParam> params = new ArrayList<>();

        // int type_id
        params.add(new SQLParam(Types.BIGINT, "type_id", loan_type.getId().get()));
        // varchar type_name
        params.add(new SQLParam(Types.VARCHAR, "type_name", loan_type.getName().get()));
        // varchar type_desc
        params.add(new SQLParam(Types.VARCHAR, "address", loan_type.getDesc().get()));

        return params;
    }
}
