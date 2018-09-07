package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.model.dao.DaoAddress;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Address;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceAddress extends ServiceTablesInitDrop implements ServiceSetup<Address>, DaoService<Long,Address> {

    private DaoAddress daoAddress;
    Connection connection;

    public ServiceAddress(Connection connection) {
        super(connection);
        this.connection = connection;
        daoAddress = DaoAddress.getInstance();
        try {
            this.daoAddress.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public Address modificationLength(Address address) {
        if (address != null) {
            if (address.getPostCode().trim().length() > DbConstants.MAX_ADDRESS_POST) {
                address.setPostCode(address.getPostCode().trim().substring(0, DbConstants.MAX_ADDRESS_POST));
            } else {
                address.setPostCode(address.getPostCode().trim());
            }
            if (address.getRegion().trim().length() > DbConstants.MAX_ADDRESS_REGION) {
                address.setRegion(address.getRegion().trim().substring(0, DbConstants.MAX_ADDRESS_REGION));
            } else {
                address.setRegion(address.getRegion().trim());
            }
            if (address.getTown().trim().length() > DbConstants.MAX_ADDRESS_TOWN) {
                address.setTown(address.getTown().trim().substring(0, DbConstants.MAX_ADDRESS_TOWN));
            } else {
                address.setTown(address.getTown().trim());
            }
            if (address.getStreet().trim().length() > DbConstants.MAX_ADDRESS_STREET) {
                address.setStreet(address.getStreet().trim().substring(0, DbConstants.MAX_ADDRESS_STREET));
            } else {
                address.setStreet(address.getStreet().trim());
            }if (address.getBuilding().trim().length() > DbConstants.MAX_ADDRESS_BUILDING) {
                address.setBuilding(address.getBuilding().trim().substring(0, DbConstants.MAX_ADDRESS_BUILDING));
            } else {
                address.setBuilding(address.getBuilding().trim());
            }
        }
        return address;
    }

    public Address add(Address address){
        if (address != null) {
            modificationLength(address);
            startTransaction();
            try {
                daoAddress.add(address);
            } catch (SQLException e) {
                System.out.println("Error add ADDRESS in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return address;
    }

    public Address get(Long id) {
        Address address = null;
        if (id != null) {
            startTransaction();
            try {
                address =daoAddress.get(id);
            } catch (SQLException e) {
                System.out.println("Error get ADDRESS in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return address;
    }

    public Address edit(Address address) {
        if (address != null) {
            modificationLength(address);
            startTransaction();
            try {
                daoAddress.edit(address);
            } catch (SQLException e) {
                System.out.println("Error edit ADDRESS in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return address;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoAddress.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell ADDRESS from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

}
