package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.InitDropTables;
import by.vistar.comeco.model.dao.DaoFirstInit;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceTablesInitDrop implements InitDropTables {

    Connection connection;

    public ServiceTablesInitDrop(Connection connection) {
        if (connection!=null) {
            this.connection = connection;
        }else {
            try {
                throw new SQLException();
            } catch (SQLException e) {
                System.out.println("Connection = NULL ");
                e.printStackTrace();
            }
        }
    }

    public void startTransaction() {
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error init setAutocommit = false");
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            System.out.println("Error commit");
            e.printStackTrace();
        }
    }

    public void initTable() {
        startTransaction();
        try {
            DaoFirstInit.initTables(connection);
        } catch (SQLException e) {
            System.out.println("Error initTable from DB");
            e.printStackTrace();
        }
        commit();
    }

    public void dropTable() {
        startTransaction();
        try {
            DaoFirstInit.dropTables(connection);
        } catch (SQLException e) {
            System.out.println("Error DROP Table from DB");
            e.printStackTrace();
        }
        commit();
    }
}
