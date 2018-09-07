package by.vistar.comeco.model.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.model.dao.DaoCountry;
import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Country;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceCountry extends ServiceTablesInitDrop implements ServiceSetup<Country>, DaoService<Long,Country> {

    private DaoCountry daoCountry;
    Connection connection;

    public ServiceCountry(Connection connection) {
        super(connection);
        this.connection = connection;
        daoCountry = DaoCountry.getInstance();
        try {
            this.daoCountry.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    public Country add(Country country) {
        if (country != null) {
            modificationLength(country);
            startTransaction();
            try {
                daoCountry.add(country);
            } catch (SQLException e) {
                System.out.println("Error add COUNTRY in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return country;
    }

    public Country get(Long id) {
        Country country = null;
        if (id != null) {
            startTransaction();
            try {
                country = daoCountry.get(id);
            } catch (SQLException e) {
                System.out.println("Error get COUNTRY in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return country;
    }

    public Country edit(Country country) {
        if (country != null) {
            modificationLength(country);
            startTransaction();
            try {
                daoCountry.edit(country);
            } catch (SQLException e) {
                System.out.println("Error edit COUNTRY in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return country;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoCountry.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell COUNTRY from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }


    @Override
    public Country modificationLength(Country country) {
        if (country != null) {
            if (country.getName().trim().length() > DbConstants.MAX_COUNTRY_NAME) {
                country.setName(country.getName().trim().substring(0, DbConstants.MAX_COUNTRY_NAME));
            } else {
                country.setName(country.getName().trim());
            }
            if (country.getFullName().trim().length() > DbConstants.MAX_COUNTRY_FULL_NAME) {
                country.setFullName(country.getFullName().trim().substring(0, DbConstants.MAX_COUNTRY_FULL_NAME));
            } else {
                country.setFullName(country.getFullName().trim());
            }
            if (country.getCodLetter().trim().length() > DbConstants.MAX_COUNTRY_COD) {
                country.setCodLetter(country.getCodLetter().trim().substring(0, DbConstants.MAX_COUNTRY_COD));
            } else {
                country.setCodLetter(country.getCodLetter().trim());
            }
            if (country.getPathFlag().trim().length() > DbConstants.MAX_COUNTRY_PATH_FLAG) {
                country.setPathFlag(country.getPathFlag().trim().substring(0, DbConstants.MAX_COUNTRY_PATH_FLAG));
            } else {
                country.setPathFlag(country.getPathFlag().trim());
            }
        }
        return country;
    }
}
