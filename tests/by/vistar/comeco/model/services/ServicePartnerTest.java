package by.vistar.comeco.model.services;

import by.vistar.comeco.model.db.DbConstants;
import by.vistar.comeco.model.entity.Partner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServicePartnerTest {

    static Connection connection;


    @Before
    public void initTest() {
        connection = ServiceMainTest.getConnection();
        new ServiceTablesInitDrop(connection).initTable();
    }

    @After
    public void closeTest() {
        new ServiceTablesInitDrop(connection).dropTable();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modificationLength() {
        Partner partner = new Partner();
        partner.setName(" Vistal 1234567890 Вистал 22222222222222222222222222222  44444444444444444444444444444444444444444444444444 ");
        partner.setFullName("       Vistal 1234567890 Вистал 1111111111111   11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 2222 ");
        partner.setVatNumber(" kdhksdhkjakLKDLJlkjldjlajdslkadjladjlajldajlajldjalkfjlafjlaksjlklaljlkhlkhДljljlkjlkо т  бKj  $$$%^^*(*(*())*_)(_(_++_+)+)(_+++////  // / ");
        partner.setPersonalNamber("Vistal 1234567890 Вистал pokpok [ ] ][] [ l[l [l[l[w/d\\n7=pwd[WQD  ");
        partner.setEmail("kjldjldq;qwdk;qdwk;qdk'qwkd;qw;dkqw;dkq;dkq;dkq;'dkqdkq;dlqkd;ldl;qdqkkq;ldmqd.,m  ");
        partner.setInfo(" ddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddd dddddddddddddddddddddddddddddddddd" +
                "ojljlkdwjwdw-09w-opwkwdkmj20ie2pkdwl;xm;ascpwfi,wq.d'./.ck;w,fa'scopw,f.ewck,pwepk.ew.;weddd " +
                "9021111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "21098109uejkdnkdqnid93m983d2u98u9,2908,2dddddddddddddddddddddddddddddddddddddddddddddd dddddd ");

        assertTrue(partner.getName().trim().length() > DbConstants.MAX_PARTNER_NAME);
        assertTrue(partner.getFullName().trim().length() > DbConstants.MAX_PARTNER_FULL_NAME);
        assertTrue(partner.getVatNumber().trim().length() > DbConstants.MAX_PARTNER_VAT_NUMBER);
        assertTrue(partner.getPersonalNamber().trim().length() > DbConstants.MAX_PARTNER_PERSONAL_NUMBER);
        assertTrue(partner.getEmail().trim().length() > DbConstants.MAX_PARTNER_EMAIL);
        assertTrue(partner.getInfo().trim().length() > DbConstants.MAX_PARTNER_INFO);

        new ServicePartner(ServiceMainTest.getConnection()).modificationLength(partner);

        assertTrue(partner.getName().length() == DbConstants.MAX_PARTNER_NAME);
        assertTrue(partner.getFullName().length() == DbConstants.MAX_PARTNER_FULL_NAME);
        assertTrue(partner.getVatNumber().length() == DbConstants.MAX_PARTNER_VAT_NUMBER);
        assertTrue(partner.getPersonalNamber().length() == DbConstants.MAX_PARTNER_PERSONAL_NUMBER);
        assertTrue(partner.getEmail().length() == DbConstants.MAX_PARTNER_EMAIL);
        assertTrue(partner.getInfo().length() == DbConstants.MAX_PARTNER_INFO);

    }

    @Test
    public void add() {
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        Partner partnerTest = new ServicePartner(connection).get(partner.getId());

        assertEquals(partner.getName(),partnerTest.getName());
        assertEquals(partner.getFullName(),partnerTest.getFullName());
        assertEquals(partner.getVatNumber(),partnerTest.getVatNumber());
        assertEquals(partner.getPersonalNamber(),partnerTest.getPersonalNamber());
        assertEquals(partner.getEmail(),partnerTest.getEmail());
        assertEquals(partner.getInfo(),partnerTest.getInfo());
        assertEquals(partner.getId(),partnerTest.getId());

    }

    @Test
    public void edit() {
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        Partner partnerTest = new Partner();
        partnerTest.setName(" Vistal Ltd. Test  ");
        partnerTest.setFullName("  Частное торговое унитарное предприятие 'Вистал'  Test");
        partnerTest.setVatNumber(" 12312312390 Test   ");
        partnerTest.setPersonalNamber("65768798 Test  ");
        partnerTest.setEmail("vistal@hotmail.com  Test");
        partnerTest.setInfo(" Фирма занимается продажей материалов для производства обуви. Test  ");
        partnerTest.setId(partner.getId());
        new ServicePartner(connection).edit(partnerTest);
        assertEquals(partner.getId(),partnerTest.getId());
        assertEquals(partnerTest.getName(),"Vistal Ltd. Test");
        assertEquals(partnerTest.getFullName(),"Частное торговое унитарное предприятие 'Вистал'  Test");
        assertEquals(partnerTest.getVatNumber(),"12312312390 Test");
        assertEquals(partnerTest.getPersonalNamber(),"65768798 Test");
        assertEquals(partnerTest.getEmail(),"vistal@hotmail.com  Test");
        assertEquals(partnerTest.getInfo(),"Фирма занимается продажей материалов для производства обуви. Test");
    }

    @Test
    public void dell() {
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        new ServicePartner(connection).dell(partner.getId());
        assertNull(new ServicePartner(connection).get(partner.getId()));
    }

    @Test
    public void get() {
        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);
        Partner partnerTest = new ServicePartner(connection).get(partner.getId());

        assertEquals(partner.getName(),partnerTest.getName());
        assertEquals(partner.getFullName(),partnerTest.getFullName());
        assertEquals(partner.getVatNumber(),partnerTest.getVatNumber());
        assertEquals(partner.getPersonalNamber(),partnerTest.getPersonalNamber());
        assertEquals(partner.getEmail(),partnerTest.getEmail());
        assertEquals(partner.getInfo(),partnerTest.getInfo());
        assertEquals(partner.getId(),partnerTest.getId());
    }
}