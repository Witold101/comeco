package by.vistar.comeco.model.dao;

import by.vistar.comeco.model.db.DbConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoFirstInit {

    public static boolean firstInit = false;

    public static void initTables(Connection connection) throws SQLException {
        if (!firstInit) {
            PreparedStatement pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_PARTNERS);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_PHONES);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_COUNTRY);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_ADDRESSES);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_BANK_ADDRESS);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_BANKS);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_ACCOUNTS);
            pst.execute();

            firstInit = true;
        }
    }

    public static void dropTables(Connection connection) throws SQLException {
        String sql1 = "DROP TABLE `" + DbConstants.TABLE_NAME_PARTNERS + "`;";
        String sql2 = "DROP TABLE `" + DbConstants.TABLE_NAME_PHONES + "`;";
        String sql3 = "DROP TABLE `" + DbConstants.TABLE_NAME_ADDRESSES + "`;";
        String sql4 = "DROP TABLE `" + DbConstants.TABLE_NAME_BANKS + "`;";
        String sql5 = "DROP TABLE `" + DbConstants.TABLE_NAME_ACCOUNTS + "`;";
        String sql6 = "DROP TABLE `" + DbConstants.TABLE_NAME_COUNTRY + "`;";
        String sql7 = "DROP TABLE `" + DbConstants.TABLE_NAME_BANK_ADDRESS + "`;";

        PreparedStatement pst = connection.prepareStatement(sql5);
        pst.execute();
        pst = connection.prepareStatement(sql4);
        pst.execute();
        pst = connection.prepareStatement(sql3);
        pst.execute();
        pst = connection.prepareStatement(sql7);
        pst.execute();
        pst = connection.prepareStatement(sql6);
        pst.execute();
        pst = connection.prepareStatement(sql2);
        pst.execute();
        pst = connection.prepareStatement(sql1);
        pst.execute();

        firstInit = false;
    }
}
