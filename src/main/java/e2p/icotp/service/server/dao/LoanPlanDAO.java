package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.LoanPlan;
import e2p.icotp.model.LoanType;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanPlanDAO {
    // LoanPlan Masterlist
    public static ObservableList<LoanPlan> getMasterlist() throws SQLException {
        ObservableList<LoanPlan> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("loan_plans");
        while (crs.next()) {
            masterlist.add(loan_planData(crs));
        }
        return masterlist;
    }

    public static LoanType getLoanTypeById(int id) throws SQLException {
        SQLParam idParam = new SQLParam(Types.INTEGER, "type_id", id);
        CachedRowSet crs = SQLCommand.selectByID("loan_types", idParam);
        return crs.next() ? LoanTypeDAO.loan_typeData(crs) : null;
    }

    // LoanPlan By ID
    public static LoanPlan getLoanPlanByID(int id) throws SQLException {
        SQLParam idParam = new SQLParam(Types.INTEGER, "plan_id", id);
        CachedRowSet crs = SQLCommand.selectByID("loan_plans", idParam);
        return crs.next() ? loan_planData(crs) : null;
    }

    // LoanPlan DATA
    public static LoanPlan loan_planData(CachedRowSet crs) throws SQLException {

        int plan_id = crs.getInt("plan_id");
        int type_id = crs.getInt("type_id");
        LoanType loan_type;
        if (type_id <= 0) {
            loan_type = new LoanType();
        } else {
            loan_type = getLoanTypeById(type_id);
        }
        long term = crs.getLong("term");
        double interest = crs.getDouble("interest");
        double penalty = crs.getDouble("penalty");

        return new LoanPlan(plan_id, loan_type, term, interest, penalty);
    }

    // Insert
    public static void insert(LoanPlan loan_plan) {
        ArrayList<SQLParam> params = parameters(loan_plan);
        SQLCommand.insert("loan_plans", params);
    }

    // Update
    public static void update(LoanPlan loan_plan) {
        updateById(loan_plan, loan_plan.getId().get());
    }

    public static void updateById(LoanPlan loan_plan, int target_id) {
        SQLParam idParam = new SQLParam(Types.INTEGER, "plan_id", target_id);
        ArrayList<SQLParam> params = parameters(loan_plan);
        SQLCommand.updateById("loan_plans", params, idParam);
    }

    // Remove
    public static void remove(int plan_id) {
        SQLParam idParam = new SQLParam(Types.INTEGER, "plan_id", plan_id);
        SQLCommand.deleteById("loan_plans", idParam);
    }

    // LoanPlan SQL
    private static ArrayList<SQLParam> parameters(LoanPlan loan_plan) {
        ArrayList<SQLParam> params = new ArrayList<>();

        // int plan_id
        params.add(new SQLParam(Types.INTEGER, "plan_id", loan_plan.getId().get()));
        // int plan_id
        params.add(new SQLParam(Types.INTEGER, "type_id", loan_plan.getType().get().getId().get()));
        // int plan_id
        params.add(new SQLParam(Types.BIGINT, "term", loan_plan.getTerm().get()));
        // int plan_id
        params.add(new SQLParam(Types.DECIMAL, "interest", loan_plan.getInterest().get()));
        // int plan_id
        params.add(new SQLParam(Types.DECIMAL, "penalty", loan_plan.getPenalty().get()));

        return params;
    }
}
