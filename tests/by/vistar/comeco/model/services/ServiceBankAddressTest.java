package by.vistar.comeco.model.services;

import by.vistar.comeco.model.entity.BankAddress;
import by.vistar.comeco.model.entity.Country;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceBankAddressTest {

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

        BankAddress bankAddress = new BankAddress();
        bankAddress.setCountry_id(country.getId());
        bankAddress.setPostCode("    220082  ");
        bankAddress.setRegion("Минская область. Смолевичский р-н  ");
        bankAddress.setTown("д. Слобода ");
        bankAddress.setStreet("                    ул. Весенняя");
        bankAddress.setBuilding("6 ");
        new ServiceBankAddress(connection).add(bankAddress);

        BankAddress bankAddressTest = new ServiceBankAddress(connection).get(bankAddress.getId());

        assertEquals(bankAddress.getBuilding(),bankAddressTest.getBuilding());
        assertEquals(bankAddress.getPostCode(),bankAddressTest.getPostCode());
        assertEquals(bankAddress.getRegion(),bankAddressTest.getRegion());
        assertEquals(bankAddress.getStreet(),bankAddressTest.getStreet());
        assertEquals(bankAddress.getTown(),bankAddressTest.getTown());
        assertEquals(bankAddress.getCountry_id(),bankAddressTest.getCountry_id());
        assertEquals(bankAddress.getId(),bankAddressTest.getId());
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

        BankAddress bankAddress = new BankAddress();
        bankAddress.setCountry_id(country.getId());
        bankAddress.setPostCode("    220082  ");
        bankAddress.setRegion("Минская область. Смолевичский р-н  ");
        bankAddress.setTown("д. Слобода ");
        bankAddress.setStreet("                    ул. Весенняя");
        bankAddress.setBuilding("6 ");
        new ServiceBankAddress(connection).add(bankAddress);

        BankAddress bankAddressTest = new BankAddress();
        bankAddressTest.setCountry_id(country.getId());
        bankAddressTest.setPostCode("  220082Test  ");
        bankAddressTest.setRegion("Минская область. Смолевичский р-н Test ");
        bankAddressTest.setTown("д. Слобода Test");
        bankAddressTest.setStreet("                    ул. Весенняя Test");
        bankAddressTest.setBuilding("6 Test");
        bankAddressTest.setId(bankAddress.getId());
        new ServiceBankAddress(connection).edit(bankAddressTest);

        assertEquals("6 Test",bankAddressTest.getBuilding());
        assertEquals("220082Test",bankAddressTest.getPostCode());
        assertEquals("Минская область. Смолевичский р-н Test",bankAddressTest.getRegion());
        assertEquals("ул. Весенняя Test",bankAddressTest.getStreet());
        assertEquals("д. Слобода Test",bankAddressTest.getTown());
        assertEquals(country.getId(),bankAddressTest.getCountry_id());
        assertEquals(bankAddress.getId(),bankAddressTest.getId());
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

        BankAddress bankAddress = new BankAddress();
        bankAddress.setCountry_id(country.getId());
        bankAddress.setPostCode("    220082  ");
        bankAddress.setRegion("Минская область. Смолевичский р-н  ");
        bankAddress.setTown("д. Слобода ");
        bankAddress.setStreet("                    ул. Весенняя");
        bankAddress.setBuilding("6 ");
        new ServiceBankAddress(connection).add(bankAddress);
        new ServiceBankAddress(connection).dell(bankAddress.getId());

        assertNull(new ServiceBankAddress(connection).get(bankAddress.getId()));
    }
}