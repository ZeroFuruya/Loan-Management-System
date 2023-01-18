package e2p.icotp.service.server.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import e2p.icotp.model.Collateral;
import e2p.icotp.service.server.core.SQLCommand;
import e2p.icotp.service.server.core.SQLParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollateralDAO {
    //Collateral Masterlist
    public static ObservableList<Collateral> getMasterlist() throws SQLException{
        ObservableList<Collateral> masterlist = FXCollections.observableArrayList();
        CachedRowSet crs = SQLCommand.selectAll("collateral");
        while(crs.next()){
            masterlist.add(collateralData(crs));
        }
       return masterlist;
    }
    //Collateral By ID
    public static Collateral getCollateralByID(int id) throws SQLException{
        SQLParam idParam = new SQLParam(Types.INTEGER, "loaner_id", id);
        CachedRowSet crs = SQLCommand.selectByID("collateral", idParam);
        return crs.next() ? collateralData(crs) : null;
    }
    //Collateral DATA
    private static Collateral collateralData(CachedRowSet crs) throws SQLException{

        int loaner_id = crs.getInt("loaner_id");
        int loan_id = crs.getInt("loan_id");
        String collateral = crs.getString("collateral");

        return new Collateral(loan_id,loaner_id,collateral);
    }

    //INSERT
    public static void insert(Collateral collateral){
        ArrayList<SQLParam> params = parameters(collateral);
        SQLCommand.insert("collateral", params);
    }    

    //UPDATE
    public static void updateByID(Collateral collateral, int id){
        SQLParam idParam = new SQLParam(Types.INTEGER, "loaner_id", id);
        ArrayList<SQLParam> params = parameters(collateral);
        SQLCommand.updateById("collateral", params, idParam);
    }

    public static void update(Collateral collateral){
       updateByID(collateral, collateral.getLoaner_id());
    }

    //REMOVE
    public static void remove(int loaner_id){
        SQLParam idParam = new SQLParam(Types.INTEGER, "loaner_id", loaner_id);
        SQLCommand.deleteById("collateral", idParam);
    }

    //Collateral SQL
    private static ArrayList<SQLParam> parameters(Collateral collateral){
        ArrayList<SQLParam> params = new ArrayList<>();

        //int loaner_id
        params.add(new SQLParam(Types.INTEGER, "loaner_id", collateral.getLoaner_id()));
        //int loan_id
        params.add(new SQLParam(Types.INTEGER, "loan_id", collateral.getLoan_id()));
        //String collateral
        params.add(new SQLParam(Types.VARCHAR, "collateral", collateral.getCollateral()));

        return params;
    }
}
