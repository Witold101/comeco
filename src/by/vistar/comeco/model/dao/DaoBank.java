package by.vistar.comeco.model.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.model.entity.Bank;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.model.db.DbConstants.*;

public class DaoBank implements DAO<Long, Bank> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoBank INSTANCE = null;

    private DaoBank() {
    }

    public static DaoBank getInstance() {
        DaoBank daoBank = INSTANCE;
        if (daoBank == null) {
            synchronized (DaoBank.class) {
                daoBank = INSTANCE;
                if (daoBank == null) {
                    INSTANCE = daoBank = new DaoBank();
                }
            }
        }
        return daoBank;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addBank", connection.prepareStatement(MYSQL_ADD_BANK, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getBank", connection.prepareStatement(MYSQL_GET_BANK));
            mysqlPrepareStatement.put("editBank", connection.prepareStatement(MYSQL_EDIT_BANK));
            mysqlPrepareStatement.put("dellBank", connection.prepareStatement(MYSQL_DELL_BANK));
        }
    }

    @Override
    public Bank add(Bank bank) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addBank");
        pst.setString(1, bank.getName());
        pst.setString(2, bank.getSwift());
        pst.setString(3, bank.getRegionNumber());
        if (bank.getAddressId() != null && bank.getAddressId() > 1 ) {
            pst.setLong(4, bank.getAddressId());
        } else {
            pst.setNull(4, Types.INTEGER);
        }
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            bank.setId(rs.getLong(1));
        }
        rs.close();
        return bank;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellBank");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Bank edit(Bank bank) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editBank");
        pst.setString(1, bank.getName());
        pst.setString(2, bank.getSwift());
        pst.setString(3, bank.getRegionNumber());
        if (bank.getAddressId() != null && bank.getAddressId() > 0) {
            pst.setLong(4, bank.getAddressId());
        } else {
            pst.setNull(4, Types.INTEGER);
        }
        pst.setLong(5, bank.getId());
        if (pst.executeUpdate() > 0) {
            return bank;
        } else {
            return null;
        }
    }

    @Override
    public Bank get(Long id) throws SQLException {
        Bank bank = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getBank");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            bank = new Bank();
            bank.setId(rs.getLong("id"));
            bank.setName(rs.getString("name"));
            bank.setSwift(rs.getString("swift"));
            bank.setRegionNumber(rs.getString("region_number"));
            bank.setAddressId(rs.getLong("address_id"));
        }
        rs.close();
        return bank;
    }
}
