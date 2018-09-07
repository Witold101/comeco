package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.model.dao.DaoPhone;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Phone;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicePhone extends ServiceTablesInitDrop implements ServiceSetup<Phone>, DaoService<Long,Phone> {

    private DaoPhone daoPhone;
    Connection connection;

    public ServicePhone(Connection connection) {
        super(connection);
        this.connection = connection;
        daoPhone = DaoPhone.getInstance();
        try {
            this.daoPhone.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    public Phone add(Phone phone) {
        if (phone != null) {
            modificationLength(phone);
            startTransaction();
            try {
                daoPhone.add(phone);
            } catch (SQLException e) {
                System.out.println("Error add PHONE in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return phone;
    }

    public Phone get(Long id){
        Phone phone = null;
        if (id != null) {
            startTransaction();
            try {
                phone = daoPhone.get(id);
            } catch (SQLException e) {
                System.out.println("Error get PHONE in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return phone;
    }

    public Phone edit(Phone phone){
        if (phone != null) {
            modificationLength(phone);
            startTransaction();
            try {
                daoPhone.edit(phone);
            } catch (SQLException e) {
                System.out.println("Error edit PHONE in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return phone;
    }

    public void dell(Long id){
        if (id != null) {
            startTransaction();
            try {
                daoPhone.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell PHONE from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    @Override
    public Phone modificationLength(Phone phone) {
        if (phone != null) {
            if (phone.getNumber().trim().length() > DbConstants.MAX_PHONE_NUMBER) {
                phone.setNumber(phone.getNumber().trim().substring(0, DbConstants.MAX_PHONE_NUMBER));
            } else {
                phone.setNumber(phone.getNumber().trim());
            }
        }
        return phone;
    }
}
