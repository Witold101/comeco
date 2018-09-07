package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.model.dao.DaoPartner;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Partner;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicePartner extends ServiceTablesInitDrop implements ServiceSetup<Partner>, DaoService<Long,Partner> {

    private DaoPartner daoPartner;
    Connection connection;

    public ServicePartner(Connection connection) {
        super(connection);
        this.connection = connection;
        daoPartner = DaoPartner.getInstance();
        try {
            this.daoPartner.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement and (or) initTable in DaoPartner ");
            e.printStackTrace();
        }
    }

    @Override
    public Partner modificationLength(Partner partner) {
        if (partner != null) {
            if (partner.getName().trim().length() > DbConstants.MAX_PARTNER_NAME) {
                partner.setName(partner.getName().trim().substring(0, DbConstants.MAX_PARTNER_NAME));
            } else {
                partner.setName(partner.getName().trim());
            }
            if (partner.getFullName().trim().length() > DbConstants.MAX_PARTNER_FULL_NAME) {
                partner.setFullName(partner.getFullName().trim().substring(0, DbConstants.MAX_PARTNER_FULL_NAME));
            } else {
                partner.setFullName(partner.getFullName().trim());
            }
            if (partner.getVatNumber().trim().length() > DbConstants.MAX_PARTNER_VAT_NUMBER) {
                partner.setVatNumber(partner.getVatNumber().trim().substring(0, DbConstants.MAX_PARTNER_VAT_NUMBER));
            } else {
                partner.setVatNumber(partner.getVatNumber().trim());
            }
            if (partner.getPersonalNamber().trim().length() > DbConstants.MAX_PARTNER_PERSONAL_NUMBER) {
                partner.setPersonalNamber(partner.getPersonalNamber().trim().substring(0, DbConstants.MAX_PARTNER_PERSONAL_NUMBER));
            } else {
                partner.setPersonalNamber(partner.getPersonalNamber().trim());
            }
            if (partner.getEmail().trim().length() > DbConstants.MAX_PARTNER_EMAIL) {
                partner.setEmail(partner.getEmail().trim().substring(0, DbConstants.MAX_PARTNER_EMAIL));
            } else {
                partner.setEmail(partner.getEmail().trim());
            }
            if (partner.getInfo().trim().length() > DbConstants.MAX_PARTNER_INFO) {
                partner.setInfo(partner.getInfo().trim().substring(0, DbConstants.MAX_PARTNER_INFO));
            } else {
                partner.setInfo(partner.getInfo().trim());
            }
        }
        return partner;
    }

    public Partner add(Partner partner) {
        if (modificationLength(partner) != null) {
            startTransaction();
            try {
                daoPartner.add(partner);
            } catch (SQLException e) {
                System.out.println("Error add PARTNER in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return partner;
    }

    public Partner edit(Partner partner) {
        if (modificationLength(partner) != null) {
            startTransaction();
            try {
                daoPartner.edit(partner);
            } catch (SQLException e) {
                System.out.println("Error edit PARTNER in DB");
                e.printStackTrace();
            }
            commit();
        }
        return partner;
    }

    public Partner get(Long id) {
        Partner partner = null;
        if (id != null) {
            startTransaction();
            try {
                partner = daoPartner.get(id);
            } catch (SQLException e) {
                System.out.println("Error get PARTNER from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
        return partner;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoPartner.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell PARTNER from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    public void closePrepareStatement(){
        try {
            daoPartner.closePrepareStatement();
        } catch (SQLException e) {
            System.out.println("Error CLOSE PREPARE STATEMENT");
            e.printStackTrace();
        }
    }

}
