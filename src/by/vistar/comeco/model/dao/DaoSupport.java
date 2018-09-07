package by.vistar.comeco.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoSupport {

    private Connection connection = null;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @param tableName
     * @throws SQLException
     * Передаем в метод TableName для установки всех Type в 0
     */
    public void typeReset(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE type=1 limit 1;";
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Long typeId = rs.getLong("id");
            String queryUpd = "UPDATE " + tableName + " SET type=0 WHERE id=" + typeId + ";";
            pst = connection.prepareStatement(queryUpd);
            pst.execute();
        }
        rs.close();
    }
}
