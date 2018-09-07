package by.vistar.comeco.model.services;

import by.vistar.comeco.model.entity.Partner;
import by.vistar.comeco.model.entity.Phone;
import org.junit.*;


import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServicePhoneTest {

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
        Phone phone = new Phone();
        phone.setNumber("  +375 29 701-30-82  ");
        phone.setType(1);
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        phone.setIdPartner(partner.getId());
        new ServicePhone(connection).add(phone);
        Phone phoneTest = new ServicePhone(connection).get(phone.getId());
        assertEquals(phone.getId(),phoneTest.getId());
        assertEquals(phone.getNumber(),phoneTest.getNumber());
        assertEquals(phone.getType(),phoneTest.getType());
        assertEquals(phone.getIdPartner(),phoneTest.getIdPartner());
    }

    @Test
    public void get(){
        Phone phone = new Phone();
        phone.setNumber("  +375 29 701-30-82  ");
        phone.setType(2);
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        phone.setIdPartner(partner.getId());
        new ServicePhone(connection).add(phone);
        Phone phoneTest = new ServicePhone(connection).get(phone.getId());
        assertEquals(phone.getId(),phoneTest.getId());
        assertEquals(phone.getNumber(),phoneTest.getNumber());
        assertEquals(phone.getType(),phoneTest.getType());
        assertEquals(phone.getIdPartner(),phoneTest.getIdPartner());
    }

    @Test
    public void dell(){
        Phone phone = new Phone();
        phone.setNumber("  +375 29 701-30-82  ");
        phone.setType(2);
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        phone.setIdPartner(partner.getId());
        new ServicePhone(connection).add(phone);
        new ServicePhone(connection).dell(phone.getId());
        assertNull(new ServicePhone(connection).get(phone.getId()));
    }

    @Test
    public void edit(){
        Phone phone = new Phone();
        phone.setNumber("  +375 29 701-30-82  ");
        phone.setType(0);
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        phone.setIdPartner(partner.getId());
        new ServicePhone(connection).add(phone);
        Phone phoneTest = new Phone();
        phoneTest.setNumber("  +375 29 701-30-82 Test ");
        phoneTest.setType(1);
        phoneTest.setId(phone.getId());
        phoneTest.setIdPartner(phone.getIdPartner());
        new ServicePhone(connection).edit(phoneTest);
        phone = new ServicePhone(connection).get(phoneTest.getId());
        assertEquals(phone.getId(),phoneTest.getId());
        assertNotEquals(phone.getNumber(),"  +375 29 701-30-82 Test ");
        assertEquals(phone.getNumber(),phoneTest.getNumber());
        assertEquals(phone.getType(),phoneTest.getType());
        assertEquals(phone.getIdPartner(),phoneTest.getIdPartner());


    }
}