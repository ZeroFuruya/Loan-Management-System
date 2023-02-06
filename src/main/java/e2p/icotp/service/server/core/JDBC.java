package e2p.icotp.service.server.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import e2p.icotp.util.constants.DB;
import e2p.icotp.util.custom.date.DateUtil;

public class JDBC {
    protected static Connection connect() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(DB.URL);
        } catch (SQLException e) {
            System.out.print("Could not connect to MySQL Database.");
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    protected static CachedRowSet executeQuery(String sql, String table, ArrayList<SQLParam> params) {
        AtomicInteger dataCount = new AtomicInteger(1);

        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            params.forEach(param -> {
                try {
                    switch (param.getType()) {
                        case Types.VARCHAR:
                            statement.setString(dataCount.getAndIncrement(), String.valueOf(param.getData()));
                            break;
                        case Types.DECIMAL:
                        case Types.REAL:
                            statement.setDouble(dataCount.getAndIncrement(), (double) param.getData());
                            break;
                        case Types.TINYINT:
                        case Types.INTEGER:
                            statement.setInt(dataCount.getAndIncrement(), (int) param.getData());
                            break;
                        case Types.DATE:
                            statement.setDate(dataCount.getAndIncrement(),
                                    Date.valueOf(DateUtil.parse((String) param.getData())));
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            ResultSet results = statement.executeQuery();
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet cachedResults = factory.createCachedRowSet();
            cachedResults.populate(results);

            results.close();
            statement.close();
            conn.close();

            return cachedResults;

        } catch (SQLException e) {
            System.out.println("Could not fetch data from " + table);
            e.printStackTrace();
        }

        return null;
    }

    protected static void executeUpdate(String sql, String table, ArrayList<SQLParam> params) {
        AtomicInteger dataCount = new AtomicInteger(1);

        try (Connection conn = connect()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            params.forEach(param -> {
                try {
                    switch (param.getType()) {
                        case Types.VARCHAR:
                            statement.setString(dataCount.getAndIncrement(), String.valueOf(param.getData()));
                            break;
                        case Types.DECIMAL:
                        case Types.REAL:
                            statement.setDouble(dataCount.getAndIncrement(), (double) param.getData());
                            break;
                        case Types.INTEGER:
                            statement.setInt(dataCount.getAndIncrement(), (int) param.getData());
                            break;
                        case Types.DATE:
                            statement.setDate(dataCount.getAndIncrement(),
                                    Date.valueOf(param.getData() + ""));
                            break;
                        case Types.BIGINT:
                            statement.setLong(dataCount.getAndIncrement(), (long) param.getData());
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            statement.executeUpdate();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
