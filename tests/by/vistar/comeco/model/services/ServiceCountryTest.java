package by.vistar.comeco.model.services;

import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Country;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceCountryTest {

    static Connection connection;

    @AfterClass
    public static void connectionClose(){
        new ServiceTablesInitDrop(connection).dropTable();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void initTest() {
        connection = ServiceMainTest.getConnection();
        new ServiceTablesInitDrop(connection).initTable();
    }


    @Test
    public void add() {
        Country country = new Country();
        country.setName(" Belarus ");
        country.setFullName("  BELARUS Республика Беларусь ");
        country.setCodLetter(" BY     ");
        country.setPathFlag(" c://main/friuy     ");
        country.setPhoneCode(375);
        new ServiceCountry(connection).add(country);
        Country countryTest = new ServiceCountry(connection).get(country.getId());

        assertEquals(country.getName(),countryTest.getName());
        assertEquals(country.getFullName(),countryTest.getFullName());
        assertEquals(country.getCodLetter(),countryTest.getCodLetter());
        assertEquals(country.getPathFlag(),countryTest.getPathFlag());
        assertEquals(country.getPhoneCode(),countryTest.getPhoneCode());
        assertEquals(country.getId(),country.getId());
    }

    @Test
    public void get() {
        add();
    }

    @Test
    public void edit() {
        Country country = new Country();
        country.setName(" Belarus ");
        country.setFullName("  BELARUS Республика Беларусь ");
        country.setCodLetter(" BY     ");
        country.setPathFlag(" c://main/friuy     ");
        country.setPhoneCode(375);
        new ServiceCountry(connection).add(country);
        Country countryTest = new Country();
        countryTest.setId(country.getId());
        countryTest.setName(" USA ");
        countryTest.setFullName(" Соединенные Штаты Америки ");
        countryTest.setCodLetter(" USA     ");
        countryTest.setPathFlag(" c://main/friuy  Test   /");
        countryTest.setPhoneCode(1);
        new ServiceCountry(connection).edit(countryTest);
        country=new ServiceCountry(connection).get(countryTest.getId());

        assertEquals(country.getName(),countryTest.getName());
        assertEquals(country.getFullName(),countryTest.getFullName());
        assertEquals(country.getCodLetter(),countryTest.getCodLetter());
        assertEquals(country.getPathFlag(),countryTest.getPathFlag());
        assertEquals(country.getPhoneCode(),countryTest.getPhoneCode());
        assertEquals(country.getId(),countryTest.getId());
    }

    @Test
    public void dell() {
        Country country = new Country();
        country.setName(" Belarus ");
        country.setFullName("  BELARUS Республика Беларусь ");
        country.setCodLetter(" BY     ");
        country.setPathFlag(" c://main/friuy     ");
        country.setPhoneCode(375);
        new ServiceCountry(connection).add(country);
        new ServiceCountry(connection).dell(country.getId());
        assertNull(new ServiceCountry(connection).get(country.getId()));
    }

    @Test
    public void modificationLength() {
        Country country = new Country();
        country.setName(" Vistal 1234567890 Вистал 22222222222222222222222222222  44444444444444444444444444444444444444444444444444 ");
        country.setFullName("       Vistal 1234567890 Вистал 1111111111111   11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 2222 ");
        country.setCodLetter(" kdhksdhkjakLKDLJlkjldjlajdslkadjladjlajldajlajldjalkfjlafjlaksjlklaljlkhlkhДljljlkjlkо т  бKj  $$$%^^*(*(*())*_)(_(_++_+)+)(_+++////  // / ");
        country.setPathFlag("  Vistal 1234567890 Вистал pokpok [ ] ][] [ l[l [l[l[w/d\\n7=pwd" +
                "[WQD 77777777777777777777777777777777777 77777777777777777777777777777777 777" +
                "7777777777777777777777777777777777777777777");

        assertTrue(country.getName().trim().length() > DbConstants.MAX_COUNTRY_NAME);
        assertTrue(country.getFullName().trim().length() > DbConstants.MAX_COUNTRY_FULL_NAME);
        assertTrue(country.getCodLetter().trim().length() > DbConstants.MAX_COUNTRY_COD);
        assertTrue(country.getPathFlag().trim().length() > DbConstants.MAX_COUNTRY_PATH_FLAG);

        new ServiceCountry(ServiceMainTest.getConnection()).modificationLength(country);

        assertTrue(country.getName().trim().length() == DbConstants.MAX_COUNTRY_NAME);
        assertTrue(country.getFullName().trim().length() == DbConstants.MAX_COUNTRY_FULL_NAME);
        assertTrue(country.getCodLetter().trim().length() == DbConstants.MAX_COUNTRY_COD);
        assertTrue(country.getPathFlag().trim().length() == DbConstants.MAX_COUNTRY_PATH_FLAG);
    }
}