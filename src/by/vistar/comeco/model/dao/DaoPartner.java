package by.vistar.comeco.model.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.model.entity.Partner;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.model.db.DbConstants.*;

public class DaoPartner implements DAO<Long, Partner> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoPartner INSTANCE = null;

    private DaoPartner(){
    }

    public static DaoPartner getInstance() {
        DaoPartner daoPartner = INSTANCE;
        if (daoPartner == null) {
            synchronized (DaoPartner.class) {
                daoPartner = INSTANCE;
                if (daoPartner == null) {
                    INSTANCE = daoPartner = new DaoPartner();
                }
            }
        }
        return daoPartner;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addPartner", connection.prepareStatement(MYSQL_ADD_PARTNER, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("dellPartner", connection.prepareStatement(MYSQL_DELL_PARTNER));
            mysqlPrepareStatement.put("editPartner", connection.prepareStatement(MYSQL_EDIT_PARTNER));
            mysqlPrepareStatement.put("getPartner", connection.prepareStatement(MYSQL_GET_PARTNER));
        }
    }

    public void closePrepareStatement() throws SQLException {
        for (PreparedStatement ps : mysqlPrepareStatement.values()) {
            ps.close();
        }
    }

    @Override
    public Partner add(Partner partner) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addPartner");
        pst.setString(1, partner.getName());
        pst.setString(2, partner.getFullName());
        pst.setString(3, partner.getVatNumber());
        pst.setString(4, partner.getPersonalNamber());
        pst.setString(5, partner.getEmail());
        pst.setString(6, partner.getInfo());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            partner.setId(rs.getLong(1));
        }
        rs.close();
        return partner;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellPartner");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Partner edit(Partner partner) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editPartner");
        pst.setString(1, partner.getName());
        pst.setString(2, partner.getFullName());
        pst.setString(3, partner.getVatNumber());
        pst.setString(4, partner.getPersonalNamber());
        pst.setString(5, partner.getEmail());
        pst.setString(6, partner.getInfo());
        pst.setLong(7, partner.getId());
        if (pst.executeUpdate() > 0) {
            return partner;
        } else {
            return null;
        }
    }

    @Override
    public Partner get(Long id) throws SQLException {
        Partner partner = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getPartner");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            partner = new Partner();
            partner.setId(rs.getLong("id"));
            partner.setName(rs.getString("name"));
            partner.setFullName(rs.getString("full_name"));
            partner.setVatNumber(rs.getString("vat_number"));
            partner.setPersonalNamber(rs.getString("personal_number"));
            partner.setEmail(rs.getString("email"));
            partner.setInfo(rs.getString("info"));
        }
        rs.close();
        return partner;
    }
}
