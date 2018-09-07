package by.vistar.comeco.model.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.model.entity.Country;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.model.db.DbConstants.*;

public class DaoCountry implements DAO<Long, Country> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoCountry INSTANCE = null;

    private DaoCountry() {

    }

    public static DaoCountry getInstance() {
        DaoCountry daoCountry = INSTANCE;
        if (daoCountry == null) {
            synchronized (DaoCountry.class) {
                daoCountry = INSTANCE;
                if (daoCountry == null) {
                    INSTANCE = daoCountry = new DaoCountry();
                }
            }
        }
        return daoCountry;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addCountry", connection.prepareStatement(MYSQL_ADD_COUNTRY, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getCountry", connection.prepareStatement(MYSQL_GET_COUNTRY));
            mysqlPrepareStatement.put("editCountry", connection.prepareStatement(MYSQL_EDIT_COUNTRY));
            mysqlPrepareStatement.put("dellCountry", connection.prepareStatement(MYSQL_DELL_COUNTRY));
        }
    }

    @Override
    public Country add(Country country) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addCountry");
        pst.setString(1, country.getName());
        pst.setString(2, country.getFullName());
        pst.setString(3, country.getCodLetter());
        pst.setString(4, country.getPathFlag());
        if (country.getPhoneCode() != null) {
            pst.setInt(5, country.getPhoneCode());
        } else {
            pst.setInt(5, 0);
        }
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            country.setId(rs.getLong(1));
        }
        rs.close();
        return country;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellCountry");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Country edit(Country country) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editCountry");
        pst.setString(1, country.getName());
        pst.setString(2, country.getFullName());
        pst.setString(3, country.getCodLetter());
        pst.setString(4, country.getPathFlag());
        pst.setInt(5, country.getPhoneCode());
        pst.setLong(6, country.getId());
        if (pst.executeUpdate() > 0) {
            return country;
        } else {
            return null;
        }
    }

    @Override
    public Country get(Long id) throws SQLException {
        Country country = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getCountry");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            country = new Country();
            country.setId(rs.getLong("id"));
            country.setName(rs.getString("name"));
            country.setFullName(rs.getString("full_name"));
            country.setCodLetter(rs.getString("cod_letters"));
            country.setPathFlag(rs.getString("path_flag"));
            country.setPhoneCode(rs.getInt("phone_code"));
        }
        rs.close();
        return country;
    }
}
