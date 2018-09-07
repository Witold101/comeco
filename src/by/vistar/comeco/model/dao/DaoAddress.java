package by.vistar.comeco.model.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Address;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.model.db.DbConstants.*;

public class DaoAddress extends DaoSupport implements DAO<Long, Address> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoAddress INSTANCE = null;

    private DaoAddress() {
    }

    public static DaoAddress getInstance() {
        DaoAddress daoAddress = INSTANCE;
        if (daoAddress == null) {
            synchronized (DaoAddress.class) {
                daoAddress = INSTANCE;
                if (daoAddress == null) {
                    INSTANCE = daoAddress = new DaoAddress();
                }
            }
        }
        return daoAddress;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        super.setConnection(connection);
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addAddress", connection.prepareStatement(MYSQL_ADD_ADDRESS, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getAddress", connection.prepareStatement(MYSQL_GET_ADDRESS));
            mysqlPrepareStatement.put("editAddress", connection.prepareStatement(MYSQL_EDIT_ADDRESS));
            mysqlPrepareStatement.put("dellAddress", connection.prepareStatement(MYSQL_DELL_ADDRESS));
        }
    }

    @Override
    public Address add(Address address) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addAddress");
        pst.setLong(1, address.getCountry_id());
        pst.setString(2, address.getPostCode());
        pst.setString(3, address.getRegion());
        pst.setString(4, address.getTown());
        pst.setString(5, address.getStreet());
        pst.setString(6, address.getBuilding());
        pst.setLong(7, address.getPartnerId());
        if (address.getType() > 0){
            typeReset(DbConstants.TABLE_NAME_ADDRESSES);
        }
        pst.setInt(8, address.getType());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            address.setId(rs.getLong(1));
        }
        rs.close();
        return address;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellAddress");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Address edit(Address address) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editAddress");
        pst.setLong(1, address.getCountry_id());
        pst.setString(2, address.getPostCode());
        pst.setString(3, address.getRegion());
        pst.setString(4, address.getTown());
        pst.setString(5, address.getStreet());
        pst.setString(6, address.getBuilding());
        pst.setLong(7, address.getPartnerId());
        pst.setInt(8, address.getType());
        pst.setLong(9,address.getId());
        if (pst.executeUpdate() > 0) {
            return address;
        } else {
            return null;
        }
    }

    @Override
    public Address get(Long id) throws SQLException {
        Address address = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getAddress");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            address = new Address();
            address.setId(rs.getLong("id"));
            address.setCountry_id(rs.getLong("country_id"));
            address.setPostCode(rs.getString("post_code"));
            address.setRegion(rs.getString("region"));
            address.setTown(rs.getString("town"));
            address.setStreet(rs.getString("street"));
            address.setBuilding(rs.getString("building"));
            address.setPartnerId(rs.getLong("partner_id"));
            address.setType(rs.getInt("type"));
        }
        rs.close();
        return address;
    }
}
