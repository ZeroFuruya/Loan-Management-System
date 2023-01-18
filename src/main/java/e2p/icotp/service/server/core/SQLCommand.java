package e2p.icotp.service.server.core;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.rowset.CachedRowSet;

public class SQLCommand {
    public static CachedRowSet selectAll(String table) {
        return select(table, new ArrayList<>());
    }

    public static CachedRowSet selectByID(String table, SQLParam id) {
        return select(table, new ArrayList<>(Arrays.asList(id)));
    }

    // SELECT * FROM employee WHERE id = ? AND name = ? AND
    // to
    // SELECT * FROM employee WHERE id = ? AND name = ?;
    public static CachedRowSet select(String table, ArrayList<SQLParam> params) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM " + "`" + table + "`");
        if (!params.isEmpty()) {
            query.append(" WHERE ");
            params.forEach(param -> {
                query.append("`");
                query.append(param.getField());
                query.append("`");
                query.append(" = ? AND ");
            });
            query.delete(query.length() - 5, query.length());
        }
        query.append(";");

        return JDBC.executeQuery(query.toString(), table, params);
    }

    // INSERT INTO table (id, name, department) VALUES (?, ?, ?)
    public static void insert(String table, ArrayList<SQLParam> params) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO " + "`" + table + "`");
        query.append("(");
        params.forEach(param -> {
            query.append("`");
            query.append(param.getField());
            query.append("`");
            query.append(", ");
        });
        query.delete(query.length() - 2, query.length());
        query.append(") VALUES (");
        params.forEach(param -> {
            query.append("?, ");
        });
        query.delete(query.length() - 2, query.length());
        query.append(");");

        JDBC.executeUpdate(query.toString(), table, params);
    }

    // conds = conditions

    //
    public static void updateById(String table, ArrayList<SQLParam> params, SQLParam id) {
        update(table, params, new ArrayList<>(Arrays.asList(id)));
    }

    // UPDATE `employees` SET `emp_id` = ? WHERE `emp_id` = ?;
    public static void update(String table, ArrayList<SQLParam> params, ArrayList<SQLParam> conds) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE " + "`" + table + "`");
        query.append(" SET ");
        params.forEach(param -> {
            query.append("`");
            query.append(param.getField());
            query.append("`");
            query.append(" = ?, ");
        });
        query.delete(query.length() - 2, query.length());
        if (!conds.isEmpty()) {
            query.append(" WHERE ");
            conds.forEach(cond -> {
                query.append("`");
                query.append(cond.getField());
                query.append("`");

                query.append(" = ? AND ");
            });
            query.delete(query.length() - 5, query.length());
            params.addAll(conds);
        }
        query.append(";");

        JDBC.executeUpdate(query.toString(), table, params);
    }

    //

    public static void deleteById(String table, SQLParam id) {
        delete(table, new ArrayList<>(Arrays.asList(id)));
    }

    public static void deleteAll(String table) {
        delete(table, new ArrayList<>());
    }

    public static void delete(String table, ArrayList<SQLParam> params) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM " + "`" + table + "`");
        if (!params.isEmpty()) {
            query.append(" WHERE ");
            params.forEach(param -> {
                query.append("`");
                query.append(param.getField());
                query.append("`");
                query.append(" = ? AND ");
            });
            query.delete(query.length() - 5, query.length());
        }
        query.append(";");

        JDBC.executeUpdate(query.toString(), table, params);
    }
}
