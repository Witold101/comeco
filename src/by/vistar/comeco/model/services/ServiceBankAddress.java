package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.model.dao.DaoBankAddress;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.BankAddress;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceBankAddress extends ServiceTablesInitDrop implements ServiceSetup<BankAddress>, DaoService<Long, BankAddress> {

    private DaoBankAddress daoBankAddress;
    Connection connection;

    public ServiceBankAddress(Connection connection) {
        super(connection);
        this.connection = connection;
        daoBankAddress = DaoBankAddress.getInstance();
        try {
            this.daoBankAddress.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public BankAddress modificationLength(BankAddress bankAddress) {
        if (bankAddress != null) {
            if (bankAddress.getPostCode().trim().length() > DbConstants.MAX_ADDRESS_POST) {
                bankAddress.setPostCode(bankAddress.getPostCode().trim().substring(0, DbConstants.MAX_ADDRESS_POST));
            } else {
                bankAddress.setPostCode(bankAddress.getPostCode().trim());
            }
            if (bankAddress.getRegion().trim().length() > DbConstants.MAX_ADDRESS_REGION) {
                bankAddress.setRegion(bankAddress.getRegion().trim().substring(0, DbConstants.MAX_ADDRESS_REGION));
            } else {
                bankAddress.setRegion(bankAddress.getRegion().trim());
            }
            if (bankAddress.getTown().trim().length() > DbConstants.MAX_ADDRESS_TOWN) {
                bankAddress.setTown(bankAddress.getTown().trim().substring(0, DbConstants.MAX_ADDRESS_TOWN));
            } else {
                bankAddress.setTown(bankAddress.getTown().trim());
            }
            if (bankAddress.getStreet().trim().length() > DbConstants.MAX_ADDRESS_STREET) {
                bankAddress.setStreet(bankAddress.getStreet().trim().substring(0, DbConstants.MAX_ADDRESS_STREET));
            } else {
                bankAddress.setStreet(bankAddress.getStreet().trim());
            }if (bankAddress.getBuilding().trim().length() > DbConstants.MAX_ADDRESS_BUILDING) {
                bankAddress.setBuilding(bankAddress.getBuilding().trim().substring(0, DbConstants.MAX_ADDRESS_BUILDING));
            } else {
                bankAddress.setBuilding(bankAddress.getBuilding().trim());
            }
        }
        return bankAddress;
    }

    public BankAddress add(BankAddress bankAddress){
        if (bankAddress != null) {
            modificationLength(bankAddress);
            startTransaction();
            try {
                daoBankAddress.add(bankAddress);
            } catch (SQLException e) {
                System.out.println("Error add BANK_ADDRESS in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return bankAddress;
    }

    public BankAddress get(Long id) {
        BankAddress bankAddress = null;
        if (id != null) {
            startTransaction();
            try {
                bankAddress = daoBankAddress.get(id);
            } catch (SQLException e) {
                System.out.println("Error get BANK_ADDRESS in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return bankAddress;
    }

    public BankAddress edit(BankAddress bankAddress) {
        if (bankAddress != null) {
            modificationLength(bankAddress);
            startTransaction();
            try {
                daoBankAddress.edit(bankAddress);
            } catch (SQLException e) {
                System.out.println("Error edit BANK_ADDRESS in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return bankAddress;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoBankAddress.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell BANK_ADDRESS from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }
}
