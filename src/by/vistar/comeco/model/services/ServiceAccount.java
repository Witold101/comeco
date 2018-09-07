package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.model.dao.DaoAccount;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Account;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceAccount extends ServiceTablesInitDrop implements ServiceSetup<Account>, DaoService<Long,Account> {

    private DaoAccount daoAccount;
    Connection connection;

    public ServiceAccount(Connection connection) {
        super(connection);
        this.connection = connection;
        daoAccount = DaoAccount.getInstance();
        try {
            this.daoAccount.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public Account modificationLength(Account account) {
        if (account != null) {
            if (account.getAcc().trim().length() > DbConstants.MAX_ACCOUNT_LENGTH) {
                account.setAcc(account.getAcc().trim().substring(0, DbConstants.MAX_ACCOUNT_LENGTH));
            } else {
                account.setAcc(account.getAcc().trim());
            }
            if (account.getTypeText().trim().length() > DbConstants.MAX_ACCOUNT_TYPE_TEXT) {
                account.setTypeText(account.getTypeText().trim().substring(0, DbConstants.MAX_ACCOUNT_TYPE_TEXT));
            } else {
                account.setTypeText(account.getTypeText().trim());
            }
        }
        return account;
    }

    public Account add(Account account){
        if (account != null) {
            modificationLength(account);
            startTransaction();
            try {
                daoAccount.add(account);
            } catch (SQLException e) {
                System.out.println("Error add ACCOUNT in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return account;
    }

    public Account get(Long id) {
        Account account = null;
        if (id != null) {
            startTransaction();
            try {
                account =daoAccount.get(id);
            } catch (SQLException e) {
                System.out.println("Error get ACCOUNT in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return account;
    }

    public Account edit(Account account) {
        if (account != null) {
            modificationLength(account);
            startTransaction();
            try {
                daoAccount.edit(account);
            } catch (SQLException e) {
                System.out.println("Error edit ACCOUNT in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return account;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoAccount.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell ACCOUNT from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }
}
