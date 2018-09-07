package by.vistar.comeco.model.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.model.entity.Phone;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.model.db.DbConstants.*;

public class DaoPhone extends DaoSupport implements DAO<Long, Phone> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoPhone INSTANCE = null;

    private DaoPhone(){

    }

    public static DaoPhone getInstance() {
        DaoPhone daoPhone = INSTANCE;
        if (daoPhone == null) {
            synchronized (DaoPhone.class) {
                daoPhone = INSTANCE;
                if (daoPhone == null) {
                    INSTANCE = daoPhone = new DaoPhone();
                }
            }
        }
        return daoPhone;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        super.setConnection(connection);
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addPhone", connection.prepareStatement(MYSQL_ADD_PHONE, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getPhone", connection.prepareStatement(MYSQL_GET_PHONE));
            mysqlPrepareStatement.put("editPhone", connection.prepareStatement(MYSQL_EDIT_PHONE));
            mysqlPrepareStatement.put("dellPhone", connection.prepareStatement(MYSQL_DELL_PHONE));
        }
    }

    @Override
    public Phone add(Phone phone) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addPhone");
        pst.setString(1, phone.getNumber());
        if (phone.getType()>0){
            typeReset(TABLE_NAME_PHONES);
        }
        pst.setInt(2, phone.getType());
        pst.setLong(3, phone.getIdPartner());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            phone.setId(rs.getLong(1));
        }
        rs.close();
        return phone;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellPhone");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Phone edit(Phone phone) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editPhone");
        pst.setString(1, phone.getNumber());
        if (phone.getType()>0){
            typeReset(TABLE_NAME_PHONES);
        }
        pst.setInt(2, phone.getType());
        pst.setLong(3, phone.getIdPartner());
        pst.setLong(4, phone.getId());
        if (pst.executeUpdate() > 0) {
            return phone;
        } else {
            return null;
        }
    }

    @Override
    public Phone get(Long id) throws SQLException {
        Phone phone = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getPhone");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            phone = new Phone();
            phone.setId(rs.getLong("id"));
            phone.setNumber(rs.getString("number"));
            phone.setType(rs.getInt("type"));
            phone.setIdPartner(rs.getLong("partner_id"));
        }
        rs.close();
        return phone;
    }


}
