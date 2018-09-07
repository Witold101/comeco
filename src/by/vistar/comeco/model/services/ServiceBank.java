package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.model.dao.DaoBank;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Bank;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceBank extends ServiceTablesInitDrop implements ServiceSetup<Bank>, DaoService<Long,Bank> {

    private DaoBank daoBank;
    Connection connection;

    public ServiceBank(Connection connection) {
        super(connection);
        this.connection = connection;
        daoBank = DaoBank.getInstance();
        try {
            this.daoBank.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public Bank modificationLength(Bank bank) {
        if (bank != null) {
            if (bank.getName().trim().length() > DbConstants.MAX_BANK_NAME) {
                bank.setName(bank.getName().trim().substring(0, DbConstants.MAX_BANK_NAME));
            } else {
                bank.setName(bank.getName().trim());
            }
            if (bank.getSwift().trim().length() > DbConstants.MAX_BANK_SWIFT) {
                bank.setSwift(bank.getSwift().trim().substring(0, DbConstants.MAX_BANK_SWIFT));
            } else {
                bank.setSwift(bank.getSwift().trim());
            }
            if (bank.getRegionNumber().trim().length() > DbConstants.MAX_BANK_NUMBER) {
                bank.setRegionNumber(bank.getRegionNumber().trim().substring(0, DbConstants.MAX_BANK_NUMBER));
            } else {
                bank.setRegionNumber(bank.getRegionNumber().trim());
            }
        }
        return bank;
    }

    public Bank add(Bank bank){
        if (bank != null) {
            modificationLength(bank);
            startTransaction();
            try {
                daoBank.add(bank);
            } catch (SQLException e) {
                System.out.println("Error add BANK in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return bank;
    }

    public Bank get(Long id) {
        Bank bank = null;
        if (id != null) {
            startTransaction();
            try {
                bank =daoBank.get(id);
            } catch (SQLException e) {
                System.out.println("Error get BANK in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return bank;
    }

    public Bank edit(Bank bank) {
        if (bank != null) {
            modificationLength(bank);
            startTransaction();
            try {
                daoBank.edit(bank);
            } catch (SQLException e) {
                System.out.println("Error edit BANK in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return bank;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoBank.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell BANK from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }



}
