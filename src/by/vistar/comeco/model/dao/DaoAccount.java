package by.vistar.comeco.model.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Account;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.model.db.DbConstants.*;

public class DaoAccount extends DaoSupport implements DAO<Long, Account> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoAccount INSTANCE = null;

    private DaoAccount() {
    }

    public static DaoAccount getInstance() {
        DaoAccount daoAccount = INSTANCE;
        if (daoAccount == null) {
            synchronized (DaoAddress.class) {
                daoAccount = INSTANCE;
                if (daoAccount == null) {
                    INSTANCE = daoAccount = new DaoAccount();
                }
            }
        }
        return daoAccount;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        super.setConnection(connection);
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addAccount", connection.prepareStatement(MYSQL_ADD_ACCOUNT, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getAccount", connection.prepareStatement(MYSQL_GET_ACCOUNT));
            mysqlPrepareStatement.put("editAccount", connection.prepareStatement(MYSQL_EDIT_ACCOUNT));
            mysqlPrepareStatement.put("dellAccount", connection.prepareStatement(MYSQL_DELL_ACCOUNT));
        }
    }

    @Override
    public Account add(Account account) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addAccount");
        pst.setString(1, account.getAcc());
        pst.setLong(2, account.getBankId());
        pst.setLong(3, account.getPartnerId());
        pst.setString(4, account.getTypeText());
        if (account.getType() > 0){
            typeReset(DbConstants.TABLE_NAME_ACCOUNTS);
        }
        pst.setInt(5, account.getType());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            account.setId(rs.getLong(1));
        }
        rs.close();
        return account;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellAccount");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Account edit(Account account) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editAccount");
        pst.setString(1, account.getAcc());
        pst.setLong(2, account.getBankId());
        pst.setLong(3, account.getPartnerId());
        pst.setString(4, account.getTypeText());
        if (account.getType() > 0){
            typeReset(DbConstants.TABLE_NAME_ACCOUNTS);
        }
        pst.setInt(5, account.getType());
        pst.setLong(6, account.getId());
        if (pst.executeUpdate() > 0) {
            return account;
        } else {
            return null;
        }
    }

    @Override
    public Account get(Long id) throws SQLException {
        Account account = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getAccount");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            account = new Account();
            account.setId(rs.getLong("id"));
            account.setBankId(rs.getLong("bank_id"));
            account.setPartnerId(rs.getLong("partners_id"));
            account.setTypeText(rs.getString("type_text"));
            account.setType(rs.getInt("type"));
            account.setAcc(rs.getString("account"));

        }
        rs.close();
        return account;
    }
}
