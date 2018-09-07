package by.vistar.comeco.model.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.model.entity.BankAddress;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.model.db.DbConstants.*;

public class DaoBankAddress implements DAO<Long, BankAddress> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoBankAddress INSTANCE = null;

    private DaoBankAddress() {
    }

    public static DaoBankAddress getInstance() {
        DaoBankAddress daoBankAddress = INSTANCE;
        if (daoBankAddress == null) {
            synchronized (DaoBankAddress.class) {
                daoBankAddress = INSTANCE;
                if (daoBankAddress == null) {
                    INSTANCE = daoBankAddress = new DaoBankAddress();
                }
            }
        }
        return daoBankAddress;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addBankAddress", connection.prepareStatement(MYSQL_ADD_BANK_ADDRESS, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getBankAddress", connection.prepareStatement(MYSQL_GET_BANK_ADDRESS));
            mysqlPrepareStatement.put("editBankAddress", connection.prepareStatement(MYSQL_EDIT_BANK_ADDRESS));
            mysqlPrepareStatement.put("dellBankAddress", connection.prepareStatement(MYSQL_DELL_BANK_ADDRESS));
        }
    }

    @Override
    public BankAddress add(BankAddress bankAddress) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addBankAddress");
        pst.setLong(1, bankAddress.getCountry_id());
        pst.setString(2, bankAddress.getPostCode());
        pst.setString(3, bankAddress.getRegion());
        pst.setString(4, bankAddress.getTown());
        pst.setString(5, bankAddress.getStreet());
        pst.setString(6, bankAddress.getBuilding());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            bankAddress.setId(rs.getLong(1));
        }
        rs.close();
        return bankAddress;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellBankAddress");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public BankAddress edit(BankAddress bankAddress) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editBankAddress");
        pst.setLong(1, bankAddress.getCountry_id());
        pst.setString(2, bankAddress.getPostCode());
        pst.setString(3, bankAddress.getRegion());
        pst.setString(4, bankAddress.getTown());
        pst.setString(5, bankAddress.getStreet());
        pst.setString(6, bankAddress.getBuilding());
        pst.setLong(7,bankAddress.getId());
        if (pst.executeUpdate() > 0) {
            return bankAddress;
        } else {
            return null;
        }
    }

    @Override
    public BankAddress get(Long id) throws SQLException {
        BankAddress bankAddress = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getBankAddress");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            bankAddress = new BankAddress();
            bankAddress.setId(rs.getLong("id"));
            bankAddress.setCountry_id(rs.getLong("country_id"));
            bankAddress.setPostCode(rs.getString("post_code"));
            bankAddress.setRegion(rs.getString("region"));
            bankAddress.setTown(rs.getString("town"));
            bankAddress.setStreet(rs.getString("street"));
            bankAddress.setBuilding(rs.getString("building"));
        }
        rs.close();
        return bankAddress;
    }
}
