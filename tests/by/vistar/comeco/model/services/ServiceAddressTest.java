package by.vistar.comeco.model.services;

import by.vistar.comeco.model.entity.Address;
import by.vistar.comeco.model.entity.Country;
import by.vistar.comeco.model.entity.Partner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceAddressTest {


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

        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);

        Address address = new Address();
        address.setCountry_id(country.getId());
        address.setPostCode("    220082  ");
        address.setRegion("Минская область. Смолевичский р-н  ");
        address.setTown("д. Слобода ");
        address.setStreet("                    ул. Весенняя");
        address.setBuilding("6 ");
        address.setPartnerId(partner.getId());
        address.setType(1);
        new ServiceAddress(connection).add(address);

        Address addressTest = new ServiceAddress(connection).get(address.getId());

        assertEquals(address.getBuilding(),addressTest.getBuilding());
        assertEquals(address.getPostCode(),addressTest.getPostCode());
        assertEquals(address.getRegion(),addressTest.getRegion());
        assertEquals(address.getStreet(),addressTest.getStreet());
        assertEquals(address.getTown(),addressTest.getTown());
        assertEquals(address.getType(),addressTest.getType());
        assertEquals(address.getCountry_id(),addressTest.getCountry_id());
        assertEquals(address.getPartnerId(),addressTest.getPartnerId());
        assertEquals(address.getId(),addressTest.getId());
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

        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);

        Address address = new Address();
        address.setCountry_id(country.getId());
        address.setPostCode("    220082  ");
        address.setRegion("Минская область. Смолевичский р-н  ");
        address.setTown("д. Слобода ");
        address.setStreet("                    ул. Весенняя");
        address.setBuilding("6 ");
        address.setPartnerId(partner.getId());
        address.setType(1);
        new ServiceAddress(connection).add(address);

        Address addressTest = new Address();
        addressTest.setCountry_id(country.getId());
        addressTest.setPostCode("  220082Test  ");
        addressTest.setRegion("Минская область. Смолевичский р-н Test ");
        addressTest.setTown("д. Слобода Test");
        addressTest.setStreet("                    ул. Весенняя Test");
        addressTest.setBuilding("6 Test");
        addressTest.setPartnerId(partner.getId());
        addressTest.setType(0);
        addressTest.setId(address.getId());
        new ServiceAddress(connection).edit(addressTest);

        assertEquals("6 Test",addressTest.getBuilding());
        assertEquals("220082Test",addressTest.getPostCode());
        assertEquals("Минская область. Смолевичский р-н Test",addressTest.getRegion());
        assertEquals("ул. Весенняя Test",addressTest.getStreet());
        assertEquals("д. Слобода Test",addressTest.getTown());
        assertEquals(new Integer(0),addressTest.getType());
        assertEquals(country.getId(),addressTest.getCountry_id());
        assertEquals(partner.getId(),addressTest.getPartnerId());
        assertEquals(address.getId(),addressTest.getId());
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

        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);

        Address address = new Address();
        address.setCountry_id(country.getId());
        address.setPostCode("    220082  ");
        address.setRegion("Минская область. Смолевичский р-н  ");
        address.setTown("д. Слобода ");
        address.setStreet("                    ул. Весенняя");
        address.setBuilding("6 ");
        address.setPartnerId(partner.getId());
        address.setType(1);
        new ServiceAddress(connection).add(address);

        new ServiceAddress(connection).dell(address.getId());

        assertNull(new ServiceAddress(connection).get(address.getId()));
    }
}