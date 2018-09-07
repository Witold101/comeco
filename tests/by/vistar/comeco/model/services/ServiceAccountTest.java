package by.vistar.comeco.model.services;

import by.vistar.comeco.model.entity.Account;
import by.vistar.comeco.model.entity.Bank;
import by.vistar.comeco.model.entity.Partner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceAccountTest {

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
        Bank bank = new Bank();
        bank.setName(" Закрытое акционерное общество РРБ Банк  ");
        bank.setSwift(" RRTTOOKKHH  ");
        bank.setRegionNumber(" 1235456780  ");
        bank.setAddressId(0l);
        new ServiceBank(connection).add(bank);

        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);

        Account account = new Account();
        account.setTypeText("   USD ");
        account.setAcc("    22008200009WQ8  ");
        account.setType(1);
        account.setBankId(bank.getId());
        account.setPartnerId(partner.getId());
        new ServiceAccount(connection).add(account);
        Account accountTest = new ServiceAccount(connection).get(account.getId());

        assertEquals(account.getTypeText(),accountTest.getTypeText());
        assertEquals(account.getAcc(),accountTest.getAcc());
        assertEquals(account.getType(),accountTest.getType());
        assertEquals(account.getBankId(),accountTest.getBankId());
        assertEquals(account.getPartnerId(),accountTest.getPartnerId());
    }

    @Test
    public void get() {
        add();
    }

    @Test
    public void edit() {
        Bank bank = new Bank();
        bank.setName(" Закрытое акционерное общество РРБ Банк  ");
        bank.setSwift(" RRTTOOKKHH  ");
        bank.setRegionNumber(" 1235456780  ");
        bank.setAddressId(0l);
        new ServiceBank(connection).add(bank);

        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);

        Account account = new Account();
        account.setTypeText("   USD ");
        account.setAcc("    22008200009WQ8  ");
        account.setType(1);
        account.setBankId(bank.getId());
        account.setPartnerId(partner.getId());
        new ServiceAccount(connection).add(account);

        Account accountTest = new Account();
        accountTest.setTypeText(" RU ");
        accountTest.setAcc("    22008200009WQ8288  ");
        accountTest.setType(0);
        accountTest.setBankId(bank.getId());
        accountTest.setPartnerId(partner.getId());
        accountTest.setId(account.getId());
        account = new ServiceAccount(connection).edit(accountTest);

        assertEquals(account.getTypeText(),accountTest.getTypeText());
        assertEquals(account.getAcc(),accountTest.getAcc());
        assertEquals(account.getType(),accountTest.getType());
        assertEquals(account.getBankId(),accountTest.getBankId());
        assertEquals(account.getPartnerId(),accountTest.getPartnerId());
    }

    @Test
    public void dell() {
        Bank bank = new Bank();
        bank.setName(" Закрытое акционерное общество РРБ Банк  ");
        bank.setSwift(" RRTTOOKKHH  ");
        bank.setRegionNumber(" 1235456780  ");
        bank.setAddressId(0l);
        new ServiceBank(connection).add(bank);

        Partner partner = new Partner();
        partner.setName(" Vistal Ltd.");
        partner.setFullName("  Частное торговое унитарное предприятие 'Вистал' ");
        partner.setVatNumber(" 12312312390");
        partner.setPersonalNamber("65768798 ");
        partner.setEmail("vistal@hotmail.com  ");
        partner.setInfo(" Фирма занимается продажей материалов для производства обуви. ");
        new ServicePartner(connection).add(partner);

        Account account = new Account();
        account.setTypeText("   USD ");
        account.setAcc("    22008200009WQ8  ");
        account.setType(1);
        account.setBankId(bank.getId());
        account.setPartnerId(partner.getId());
        new ServiceAccount(connection).add(account);
        new ServiceAccount(connection).dell(account.getId());
        assertNull(new ServiceAddress(connection).get(account.getId()));
    }
}